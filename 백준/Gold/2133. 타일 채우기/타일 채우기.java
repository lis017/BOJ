import java.io.IOException;
import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {
	public static int N;
	public static int dp[];
	public static void main (String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		//dp integer로 했는데, 홀수탐색도안하는데 nullpointer오류나네 뭐지
		//솔직히 이해안되는데 걍 int 쓰라는거겠지..?;;
		//결과도 잘 나오던데,,,;
		dp = new int[N + 1];	
		//아 여기서 n이 0~1이면 dp[2]에 접근이 불가능해서 오류구나. 서순 문제가 있었네
		//여기 있었던 코드
		//dp[2] = 3; 등
		if ( N % 2 != 0) {
			System.out.println(0);
			return;
		}
		dp[0] = 1;
		dp[2] = 3;
		tile();
		
		System.out.println(dp[N]);
	}
	
	//static함수인 main에서 다른함수 호출하려면 static 붙은것만 가능함
	static void tile() {
		for ( int i = 4; i <= N; i += 2)	{	//조건 추후 수정
			dp[i] = dp[i-2] * 3;
			for ( int j = i - 4; j >= 0; j -= 2) {
				dp[i] += dp[j]*2;
			}
		}
	}
}