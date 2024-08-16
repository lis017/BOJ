import java.util.Scanner;
import java.util.ArrayList;
import java.util.Queue;
import java.util.LinkedList;

public class Main{
	public static int N, M;
	public static ArrayList<CCTV> cctvList;
	//와 이거구나. 이거 대체 왜 최댓값 넣는지 이해 안됐었는데
	//기본값이 0이라, 최솟값이 항상 0 나오기 떄문이구나. 오케이
	//그럼 최솟값 구하는 문제등에선, 기본값을 맥스로 두는게 정배겠구나. 오오
	public static int answer = Integer.MAX_VALUE;	
	public static int[][] map;
	public static int[][] copyMap;
	public static int[] output;
	public static int[] dx = {-1, 0, 1, 0};
	public static int[] dy = {0, 1, 0, -1};
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		M = sc.nextInt();
		map = new int[N][M];
		cctvList = new ArrayList<>();
		
		for ( int i = 0; i < N; i ++) {
			for ( int j = 0; j < M; j++) {
				map[i][j] = sc.nextInt();
				if ( map[i][j] != 0 && map[i][j] != 6) {
					cctvList.add(new CCTV(map[i][j], i, j));
				}
			}
		}
		//주요함수 호출 //2개인가?
		output = new int[cctvList.size()];
		permutation(0, cctvList.size());
		System.out.println(answer);
	}
	
	public static void permutation( int depth, int r) {
		if ( depth == r ) {
			copyMap = new int[N][M];
			for ( int i = 0; i < map.length; i++) {
				System.arraycopy(map[i], 0, copyMap[i], 0, map[i].length);
			}
			
			for ( int i = 0; i < cctvList.size(); i ++) {
				direction ( cctvList.get(i), output[i]);
			}
			
			getBlindSpot();
			
			return;
		}	
		
		for ( int i = 0; i < 4; i++) {
			output[depth] = i;
			permutation( depth + 1, r);
		}
	}
	
	public static void direction ( CCTV cctv, int d) {
		int num = cctv.num;
		if ( num == 1) {
			if ( d == 0) {watch(cctv, 0);}
			else if ( d == 1) {watch(cctv, 1);}
			else if ( d == 2) {watch(cctv, 2);}
			else if ( d == 3) {watch(cctv, 3);}
		}
		else if ( num == 2) {
			if ( d == 0 || d == 2) {watch(cctv, 0); watch(cctv, 2);}
			else if ( d == 1 || d == 3) {watch(cctv, 1); watch(cctv, 3);}
		}
		else if ( num == 3) {
			if ( d == 0) {watch(cctv, 0); watch(cctv, 1);}
			else if ( d == 1) {watch(cctv, 1); watch(cctv, 2);}
			else if ( d == 2) {watch(cctv, 2); watch(cctv, 3);}
			else if ( d == 3) {watch(cctv, 3); watch(cctv, 0);}
		}
		else if ( num == 4) {	//이거 방향 순서 내가 바꾸긴했는데, 절대 오류 안날듯. 혹시 오류나면 체크
			if ( d == 0) {watch(cctv, 0); watch(cctv, 1); watch(cctv, 2); }
			else if ( d == 1) {watch(cctv, 1); watch(cctv, 2); watch(cctv, 3); }
			else if ( d == 2) {watch(cctv, 2); watch(cctv, 3); watch(cctv, 0); }
			else if ( d == 3) {watch(cctv, 3); watch(cctv, 0); watch(cctv, 1); }
		}
		else if ( num == 5) {
			watch(cctv, 0); watch(cctv, 1); watch(cctv, 2); watch(cctv, 3); 
		}
	}
	
	public static void watch(CCTV cctv, int d) {//여기에 들어오는 cctv는 단 하나임.(여러개 cctv 생각 하지마)
		Queue<CCTV> queue = new LinkedList<>();
		queue.add(cctv);
				
		while ( !queue.isEmpty() )	{ //<-- 범위 안이라는 뜻의 조건임.
			
			//이거 조건없는거 맞냐..?;
			int nx = queue.peek().x + dx[d];
			int ny = queue.poll().y + dy[d];
			
			//범위 밖이면 break;
			if ( nx < 0 || nx >= N || ny < 0 || ny >= M || copyMap[nx][ny] == 6 ) {
				break;
			}
			
			if ( copyMap[nx][ny] == 0) {
				copyMap[nx][ny] = -1;
				queue.add(new CCTV(cctv.num, nx, ny));
			}
			else
				queue.add(new CCTV(cctv.num, nx, ny));
		
		}

	}
	

	
	//최솟값 함수
	public static void getBlindSpot() {
		int count = 0;
		for ( int i = 0; i < N; i ++) {
			for ( int j = 0; j < M; j++) {
				if ( copyMap[i][j] == 0 ) {
					count++;
				}
			}
		}
		
		answer = Math.min(answer, count);
	}
}

class CCTV {
	int num;
	int x;
	int y;
	
	CCTV(int num, int x, int y){
		this.num = num;
		this.x = x;
		this.y = y;
	}
}
