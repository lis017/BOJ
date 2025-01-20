import java.io.*;
import java.util.Stack;
import java.util.StringTokenizer;

public class Main {
	private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	private static final BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	static StringBuilder sb;
	
	public static void main(String[] args) throws IOException {
		sb = new StringBuilder();
		int n = Integer.parseInt(br.readLine());
		//n은 숫자 갯수
		int arr[] = new int[n+1];
		//입력된 수열 저장
		int dp[] = new int[n+1];
		// LIS의 길이를 저장   계산된 요소까지의 LIS인듯
		//☆dp의 값도 순차적으로 올라가지만은 않음
		int result = 0;
		
		StringTokenizer st = new StringTokenizer(br.readLine());
				
		//dp 배열 초기화
		//이 함수가 메인같은데 좀 더 이해해보기
		for (int i = 1; i <=n; i++) {
			int num = Integer.parseInt(st.nextToken());
			//입력된 배열 값 저장
			arr[i] = num;
			for ( int j = 0; j < i; j++) {
				if ( arr[i] > arr[j]) {
					dp[i] = Math.max(dp[j] + 1, dp[i]);
					result = Math.max(dp[i],  result);
				}
			}
		}
		
		sb.append(result + "\n");
		
		int value = result;
		//1번정답. 최장길이 값
		
		Stack<Integer> stack = new Stack<>();
		
		for ( int i = n; i >= 1; i-- ) {
			if ( value == dp[i]) {
				stack.push(arr[i]);
				value--;
			}
		}
		
		while ( !stack.isEmpty()) {
			sb.append(stack.pop() + " ");
		}
		
		bw.write(sb.toString());
		
		bw.close();
		br.close();
		
		
		
	}

}