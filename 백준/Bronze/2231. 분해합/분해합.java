import java.util.Scanner;
import java.lang.Math;

public class Main{
    public static void main(String args[]){
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        int N2 = N;
        int sum = 0;
        int cre = 0;
        int a = 0;
        
        for ( int i = 0; i < N2; i++) {
        	
        	sum = 0;
        	cre = i;
        	//sum 값 구하면 나와짐
            while( cre > 0) {
            	sum += cre%10;
            	cre /= 10;
            }
            
            if ( i + sum == N2 ) {
            	System.out.println(i);
            	a = 1;
            	break;
            }
        }
        if ( a == 0)
        	System.out.println(0);

    }
}