//쓰레기같은 문제라 꼭 뒷말을 쓰고싶다.
/*
같은 효과를 내는 코드를 써서 시간초과가 됐다.
같은 형식으로까지 바꿔도 시간초과 됐다.

이해할수 없었다. 이게 도대체 뭐지? 왜 오류지?

정답코드의 변수명과, 1 넣어도 되는걸 하나라도 줄이라는줄알고 DP[0]쓰고 난리났다.

수정에 수정을 거쳐, 정답코드와 정말 완벽하게 같은 코드를 eclipse에 손코딩으로 한자한자 베끼듯 완성했다.
이것도 시간초과다 망할. 이게 도대체 뭐지?

정답코드를 ctrl c + v하니깐 정답으로 인정된다. 정말 이해할수없는 불쾌한 코드였다.

나의 고민과 깨달음이 추가된 주석따윈 모두 사라졌다. 굉장히 불쾌하다
*/

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.StringTokenizer;
 
public class Main {
	
	static int[] seq;
	static Integer[] dp;
	
	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int N = Integer.parseInt(br.readLine());
		
		seq = new int[N];
		dp = new Integer[N];
		
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		
		for(int i = 0; i < N; i++) {
			seq[i] = Integer.parseInt(st.nextToken());
		}
		
		// 0 ~ N-1 까지 탐색 
		for(int i = 0; i < N; i++) {
			LIS(i);
		}
		
		int max = dp[0];
		// 최댓값 찾기 
		for(int i = 1; i < N; i++) {
			max = Math.max(max, dp[i]);
		}
		System.out.println(max);
	}
	
	
	static int LIS(int N) {
		
		// 만약 탐색하지 않던 위치의 경우 
		if(dp[N] == null) {
			dp[N] = 1;	// 1로 초기화 
			
			// N-1 부터 0까지중 N보다 작은 값들을 찾으면서 재귀호출. 
			for(int i = N - 1; i >= 0; i--) {
				if(seq[i] < seq[N]) {
					dp[N] = Math.max(dp[N], LIS(i) + 1);
				}
			}
		}
		return dp[N];
	}
}
