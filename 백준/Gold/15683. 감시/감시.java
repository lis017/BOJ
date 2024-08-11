//DFS, BFS 둘다 사용됨

import java.util.ArrayList;
import java.util.Scanner;
import java.util.Queue;
import java.util.LinkedList;

public class Main {
    public static int N, M;
    public static int[] output;
    public static int[][] map;
    public static int[][] copyMap;
    public static ArrayList<CCTV> cctvList;
    public static int answer = Integer.MAX_VALUE;	//여기서 왜 max 넣는거지..?
    public static int[] dx = {-1, 0, 1, 0};	//오 특이합니다. // x,y가 수학과 다르게 반대 느낌
    public static int[] dy = {0, 1, 0, -1};
    
    
    public static void main (String[] args) {
    	Scanner sc = new Scanner(System.in);
    	N = sc.nextInt();
    	M = sc.nextInt();
    	map = new int[N][M];
    	cctvList = new ArrayList<>();
    	
    	for( int i = 0; i < N; i++) {
    		for( int j = 0; j < M; j++) {
    			map[i][j] = sc.nextInt();
    			
    			if ( map[i][j] != 0 && map[i][j] != 6) {
    				cctvList.add(new CCTV(map[i][j], i, j));
    			}
    		}
    	}
    	output = new int[cctvList.size()];
    	permutation(0, cctvList.size());
    	
    	System.out.println(answer);
    }
    
 // DFS로 상하좌우 4방향 중에서 cctv의 총 개수, r만큼을 순서대로 뽑는 순열을 구함 
    public static void permutation(int depth, int r) {//r == cctv 갯수
    	if ( depth == r) {
    		copyMap = new int [N][M];
    		for( int i = 0; i < map.length; i++) {
    			System.arraycopy(map[i], 0, copyMap[i], 0, map[i].length);
    		}
    		
    		//cctv 하나씩 정해진 방향을 모두 쳐다보게 함.
    		for(int i = 0; i < cctvList.size(); i++) {
    			direction(cctvList.get(i), output[i]);//여기서 output의 i는 cctv순서임, 내용물은 바라보는 방향
    		}
    		
    		//사각 지대 구하기
    		//cctv 하나하나 하나씩의 방향을 정한후 사각지대 최솟값을 answer 에 넣게됨.
    		//이 함수도 4**(cctv개수) 만큼 호출됨
    		getBlindSpot();
    		
    		return;
    		
    	}
    	//depth의 숫자가 cctv 순서임.
    	for ( int i = 0; i < 4; i++) {	//cctv가 6개면 //4**6(4의 6제곱)의 방향 체크가능
    		output [depth] = i;			//cctv가 바라보는 방향
    		permutation(depth + 1, r);	//다음 cctv가 보는 방향 정하게됨
    	}
    }
    
 // 각 cctv 번호와 순열로 뽑혀진 방향에 맞게 감시 
    //왜 static을 붙였을까?
    public static void direction(CCTV cctv, int d) {//d == cctv가 보는 방향
    	int cctvNum = cctv.num;						//cctv는 num,x,y 3개 다 들어있음
    	
    	if ( cctvNum == 1) {
    		if (d == 0) watch(cctv, 0);
    		else if ( d == 1) watch(cctv, 1);
    		else if ( d == 2) watch(cctv, 2);
    		else if ( d == 3) watch(cctv, 3);
    	} else if(cctvNum == 2) {
    		if ( d == 0 || d == 2) {
    			watch(cctv, 0); watch(cctv, 2);
    		} else {
    			watch(cctv, 1); watch(cctv, 3);
    		}
    	} else if(cctvNum == 3) {
    		if(d == 0) {
    			watch(cctv, 0); // 상우 
    			watch(cctv, 1);
    		} else if(d == 1) { 
    			watch(cctv, 1); // 우하 
    			watch(cctv, 2);
    		} else if(d == 2) { 
    			watch(cctv, 2); // 하좌 
    			watch(cctv, 3);
    		} else if(d == 3) { 
    			watch(cctv, 0); // 좌상 
    			watch(cctv, 3);
    		}
    	} else if(cctvNum == 4) {
    		if(d == 0) {
    			watch(cctv, 0); // 좌상우 
    			watch(cctv, 1);
    			watch(cctv, 3);
    		} else if(d == 1) {
    			watch(cctv, 0); // 상우하 
    			watch(cctv, 1);
    			watch(cctv, 2);
    		} else if(d == 2) {
    			watch(cctv, 1); // 좌하우 
    			watch(cctv, 2);
    			watch(cctv, 3);
    		} else if(d == 3) {
    			watch(cctv, 0); // 상좌하 
    			watch(cctv, 2);
    			watch(cctv, 3);
    		}
    	} else if(cctvNum == 5) { // 상우하좌
    		watch(cctv, 0);
    		watch(cctv, 1);
    		watch(cctv, 2);
    		watch(cctv, 3);
    	}
    }
    
    //BFS //방향에 맞게 감시
    public static void watch(CCTV cctv, int d) {//d는 바라보는 4방향
    	Queue<CCTV> queue = new LinkedList<>();
    	//이 코드에선 visited가 필요없지만, 추후 추가 기능에서 유용함.
    	boolean[][] visited = new boolean[N][M];	//visited의 존재이유가 뭐지?
    	
    	queue.add(cctv);	//cctv에는 cctv 숫자가 들어있음 1~5중에
    	visited[cctv.x][cctv.y]= true; 
    	
    	while(!queue.isEmpty()) {
    		//peek는 주고 제거 안함	//그럼 peek는 쓰면안되는거 아님?
    		int nx = queue.peek().x + dx[d];	//peek와 poll을 쓰면, 1칸 이동한 다음 /  다음 queue로 넘어가는겨?
    		//poll은 주고 제거함.
    		int ny = queue.poll().y + dy[d];
    		
    		if ( nx < 0 || nx >= N || ny < 0 || ny >= M || copyMap[nx][ny] == 6) {
    			//범위밖 or 벽 만나면 break;
    			break;
    		}
    		
    		if ( copyMap[nx][ny] == 0) {
    			copyMap[nx][ny] = -1;	//-1은 감시할수있다는 의미이다.
    			queue.add(new CCTV(cctv.num, nx, ny));
    		} else {	//다른 cctv가 있거나, 이미 감시된 칸이라면 
    			queue.add(new CCTV(cctv.num, nx, ny));	//그냥 통과
    		}
    	}
    }
    
    //사각지대 구하기
    public static void getBlindSpot() {
    	int cnt = 0;
    	for ( int i = 0; i < N; i++) {
    		for ( int j = 0; j < M; j++) {
    			if ( copyMap[i][j] == 0) { //보이는곳은 이미 -1이라 그런듯
    				cnt++;
    			}
    		}
    	}
    	answer = Math.min(answer,  cnt);
    }
    

}

class CCTV {
	int num;
	int x;
	int y;

	CCTV(int num, int x, int y) {
		this.num = num;
		this.x = x;
		this.y = y;
	}
}
