import java.util.Scanner;

public class Main{                    
    public static void main(String args[]){
        
        Scanner sc = new Scanner(System.in);
        
        int count = 0;                //count
        int arr[] = new int[10000];
        int N = sc.nextInt();    //목표 정수
        int K = sc.nextInt();    //k번째
        
        //반복문.
        for( int i = 1; i <= N; i++){
            if ( N % i == 0 ){
                arr[count + 1] = i;
                count++;
            }
        }
       
        //System.out.println(count);
        //System.out.println(K);
        //System.out.println(arr[K]);
        
        if ( count < K ){
            arr[K] = 0;
        }
        System.out.println(arr[K]);
    }
}