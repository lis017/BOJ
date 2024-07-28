import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;


public class Main{
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        System.out.println(core(Integer.parseInt(br.readLine())));
    }
    
    public static int core(int num) {
        int result = 99;
        int hun;
        int ten;
        int one;
        
        if (num < 100)
            return num;
        else{
            for ( int i = 100; i < num +1 ; i++){
                hun = i / 100;
                ten = (i / 10) % 10;
                one = i % 10;
                if ( hun- ten == ten - one){
                    result++;
                }
            }
            return result;
            
         }   
    }
    
    
}