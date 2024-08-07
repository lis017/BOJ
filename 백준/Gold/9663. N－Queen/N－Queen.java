import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main{
    static int N;
    static int[] field;
    static int count;
    
    public static void dfs(int depth){
        if ( depth == N) {
            count++;
            return;
        }
        
        for ( int i = 0; i < N; i++){
            field[depth] = i;
            if ( possible(depth) ){
                dfs(depth + 1);
            }
        }
    }
    
    public static boolean possible(int col){
        for ( int i = 0; i < col; i++) {
            if ( field[i] == field[col] ){
                return false;
            }
            else if ( Math.abs(col - i) == Math.abs(field[col] - field[i])){
                return false;
            }
        }
        return true;
        
    }
    
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        field = new int[N];
        //주 함수 호출
        dfs(0);
        System.out.println(count);
    }
}