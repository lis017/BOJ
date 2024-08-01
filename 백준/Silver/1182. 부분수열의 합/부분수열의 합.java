import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main{
    static int N;
    static int target, count;
    static int seq[];
    
    public static void dfs( int index, int sum) {
        if ( index == N) {
            if ( sum == target){
                count++;
            }
            return;
        }

        
        dfs(index + 1, sum + seq[index]);
        dfs(index + 1, sum);
    }
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        //n, s를 입력받고, 
        String fl = br.readLine();
        String sl = br.readLine();
        
        //N, S, seq에 변수할당 완료
        StringTokenizer st = new StringTokenizer(fl);
        N = Integer.parseInt(st.nextToken());
        target = Integer.parseInt(st.nextToken());
        StringTokenizer st2 = new StringTokenizer(sl);
        seq = new int[N];
        for ( int i = 0; i < N; i++) {
            seq[i] = Integer.parseInt(st2.nextToken());
        }
        dfs(0,0);
        
        System.out.println(target == 0 ? count -1 : count);
    }
}