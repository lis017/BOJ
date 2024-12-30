import java.util.*;
public class Main {
 
 
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
 
        long[] fac = new long[21];
        fac[0] = 1;
        for(int i = 1; i<=20 ;i++) fac[i] = i*fac[i-1];
 
        int N = sc.nextInt();
        int num = sc.nextInt();
        long k = 0;
        int[] arr = new int[N+1];
        boolean[] visited = new boolean[N+1];
        Arrays.fill(arr,0);
        Arrays.fill(visited,false);
 
        Vector<Integer> ansVec = new Vector<Integer>();
        long ans = 1;
 
        if (num==1){
            k = sc.nextLong();      //Long으로 입력하지 않으면 런타임 에러뜸
            for(int i = 0; i<N; i++){
                for(int j = 1; j<=N; j++){
                    if(visited[j]) continue;    //이미 사용된 숫자는 패스
                    if(k - fac[N-1-i] > 0) {    //0이랑 같으면 1 3 4 2가 오답으로 출력됨 
                        k -= fac[N-1-i];        
                    }
                    else {                         
                        ansVec.add(j);
                        visited[j] = true;
                        break;
                    }
                }
            }
            for(int i = 0; i<ansVec.size(); i++){
                System.out.print(ansVec.elementAt(i) + " ");
            }
 
        }
        else if (num==2){
            for(int i = 0; i<N; i++){
                arr[i] = sc.nextInt();  
            }
            for(int i = 0; i<N; i++){
                for(int j = 1; j<arr[i]; j++){      //arr[i] 보다는 작은 수가 앞에 나올 수 있으므로 
                    if(visited[j] == false){
                        ans += fac[N-1-i];
                    }
 
                }
                visited[arr[i]] = true;
            }
            System.out.println(ans);
 
 
 
        }
    }
}