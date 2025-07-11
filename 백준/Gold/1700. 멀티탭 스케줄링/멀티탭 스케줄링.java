import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.StringTokenizer;
 
public class Main {
 
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
 
        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());
 
        int[] order = new int[K];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < K; i++) {
            order[i] = Integer.parseInt(st.nextToken());
        }
        boolean[] use = new boolean[101];
        int put = 0;
        int ans = 0;
        for ( int i = 0; i < K; i++ ) {
            int temp = order[i];
            
            if( !use[temp]){
                if ( put < N) {
                    use[temp] = true;
                    put++;
                } else{
                    ArrayList<Integer> arrList = new ArrayList<>();
                    for ( int j = i; j < K; j++){
                        if ( use[order[j]] && !arrList.contains(order[j]))
                            arrList.add(order[j]);
                    }  
                    
                    if ( arrList.size() != N) {
                        for ( int j = 1; j < use.length; j++){
                            if(use[j] && !arrList.contains(j)){
                                use[j] = false;
                                break;
                            }
                        }
                    }else{
                        int remove = arrList.get(arrList.size()-1);
                        use[remove] = false;
                    }
                    
                    use[temp] = true;
                    ans++;  
                }
            }
        }
        bw.write(ans + "\n");
        bw.flush();
        bw.close();
        br.close();
    }
    
}