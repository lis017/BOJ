//마지막으로 내가 잘 썼는지 다시한번 체크
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main{
	static int rangeX[] = {-1, 0, 1, 0};
	static int rangeY[] = {0, 1, 0, -1};
	static int M,N;
	static int[][] arr, dp;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st = new StringTokenizer(br.readLine());
		//기본 입출력세팅
		
		M = Integer.parseInt(st.nextToken());
		N = Integer.parseInt(st.nextToken());
		//n,m 값 받기
		
		//arr[][]로 입력된 네모 자료 하나씩 대입
		arr = new int [M+1][N+1];
		//보통 경계짓기위해 걍 m+1 n+1 쓰는듯
		for( int i = 1; i <= M; i ++) {
			st = new StringTokenizer(br.readLine());
			for ( int j = 1; j <= N; j++) {
				arr[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		dp = new int[M+1][N+1];
		//dp의 값을 m,n크기로 만들고
		for( int i = 1; i <= M; i ++) {
			for ( int j = 1; j <= N; j++) {
				dp[i][j] = -1;
			}
		}
		
		bw.write(DFS(1,1) + "\n");
		bw.flush();
		bw.close();
		br.close();
		
		
		//내부 값을 -1로 초기화
	}
	
	public static int DFS(int x, int y) {
		
		if (x == M && y == N) {
			return 1;
		}
		
		if ( dp[x][y] != -1)
			return dp[x][y];
		
		
		//와 애초에 -1인데 왜 그냥 더하는거지 했더니 이게 그거구나
		dp[x][y] = 0;
		//dx와 dy를 이용하여,
		//for문 4까지 해서 x,y에 더해봤을때
		//범위 밖으로 나가는지 일단 체크 해보자잉
		for ( int i = 0; i < 4; i++) {
			int dx = x + rangeX[i];
			int dy = y + rangeY[i];
			
			//1보다 dx dy가 작거나, M,N보다 커지면 안되는건데 
			//뭐가 안되는거지?
			if ( dx < 1 || dy < 1 || dx > M || dy > N) {
				continue;
			}
			
			if ( arr[x][y] > arr[dx][dy]) {
				dp[x][y] += DFS(dx, dy);
			}

		}
		
		
		return dp[x][y];
		//이거 빨간거 거슬려서 임시로 넣은겨
	}
	
}
