import java.util.*;
import java.io.*;

public class Main{
    static int N;
    static int line;
    static List<Integer>[] computer;
    static int count = 0;
    static boolean[] check;
    
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        line = Integer.parseInt(br.readLine());
        computer = new List[N+1];
        StringTokenizer st;
        for( int i = 1 ; i < N+1; i++){
            computer[i] = new ArrayList<Integer>();
        } 
        for( int i = 1; i< line + 1; i++){
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            computer[a].add(b);
            computer[b].add(a);
         }
         check = new boolean[N+1];
         dfs(1);
         System.out.println(count-1);
    }
    
    static void dfs(int x){
        if ( check[x] == false){
            check[x] = true;
            count++;
            for ( int i = 0; i< computer[x].size(); i++){
            dfs(computer[x].get(i));
            }
        }
    }
}