import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
    //선분 정보 관련 클래스
    static class Road implements Comparable<Road>{
        int s, e;	//s : 시작 위치,  e : 끝나는 위치
        public Road(int s, int e) {
            this.s = s;
            this.e = e;
        }
        @Override
        //끝나는 위치 기준 오름차순, 끝나는 위치가 동일할 때에는 시작위치 오름차순
        public int compareTo(Road o) {
            if(this.e == o.e)
                return this.s - o.s;
            return this.e - o.e;
        }
    }
    public static void main(String[] args) throws IOException {
        //입력값 처리하는 BufferedReader
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        StringTokenizer st;
        List<Road> roads = new ArrayList<>();
        //철로 정보 저장
        for(int i=0;i<N;i++) {
            st = new StringTokenizer(br.readLine()," ");
            int h = Integer.parseInt(st.nextToken());
            int o = Integer.parseInt(st.nextToken());
            if(h <= o)	//집의 위치가 오피스 위치보다 작을 때
                roads.add(new Road(h, o));
            else	//오피스 위치가 집의 위치보다 작을 때
                roads.add(new Road(o, h));

        }
        int L = Integer.parseInt(br.readLine());
        Collections.sort(roads);	//선분 정렬 진행!
        //현재 철로에 포함되는 인원 저장 우선순위 큐
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        int result = 0;
        //각 철로 시작위치에서 탐색
        for(Road road : roads) {
            if(road.e - road.s > L)		//현재 선분의 길이가 D보다 클 때
                continue;	//해당 선분은 절대 포함될 수 없기에 넘기기
            pq.offer(road.s);	//현재 선분 철로 그룹에 포함
            //철로 그룹에 현재 선분에 포함되지 않는 선분들 제거
            while(!pq.isEmpty()) {
                if(road.e - pq.peek() <= L)
                    break;
                pq.poll();
            }
            result = Math.max(result, pq.size());	//최대값 비교
        }	
        System.out.print(result);		//결과 출력

    }
}