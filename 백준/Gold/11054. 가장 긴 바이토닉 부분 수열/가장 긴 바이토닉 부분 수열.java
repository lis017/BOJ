import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static int N;
    public static int[] seq;
    public static Integer[] dp;
    public static Integer[] dp2;
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        seq = new int[N];
        dp = new Integer[N];
        dp2 = new Integer[N];
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        
        for (int i = 0; i < N; i++) {
            seq[i] = Integer.parseInt(st.nextToken());
        }
        
        for (int i = 0; i < N; i++) {
            LIS(i);
            Rdc(i);
        }
        
        int maxResult = 0;
        
        for (int i = 0; i < N; i++) {
            maxResult = Math.max(maxResult, dp[i] + dp2[i] - 1);  // dp[i]와 dp2[i]가 합쳐지기 때문에 1을 뺀다.
        }
        
        System.out.println(maxResult);
    }
    
    // 증가수열
    static int LIS(int n) {
        if (dp[n] == null) {
            dp[n] = 1;
            
            for (int i = n - 1; i >= 0; i--) {
                if (seq[i] < seq[n]) {
                    dp[n] = Math.max(dp[n], LIS(i) + 1);
                }
            }
        }
        return dp[n];
    }
    
    // 감소수열
    static int Rdc(int n2) {
        if (dp2[n2] == null) {
            dp2[n2] = 1;
            
            for (int i = n2 + 1; i < N; i++) {
                if (seq[i] < seq[n2]) {  // 감소하는 순서를 찾아야 하므로 조건 변경
                    dp2[n2] = Math.max(dp2[n2], Rdc(i) + 1);
                }
            }
        }
        return dp2[n2];
    }
}