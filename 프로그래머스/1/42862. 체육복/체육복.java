import java.util.*;

class Solution {
    public int solution(int n, int[] lost, int[] reserve) {
        Arrays.sort(lost);
        Arrays.sort(reserve);
        
        boolean[] lostStudent = new boolean[n + 1];
        boolean[] reserveStudent = new boolean[n+1];
        
        for(int student : lost) {
            lostStudent[student] = true;
        }
        
        for ( int student : reserve) {
            reserveStudent[student] = true;
        }
        
        for ( int i = 1; i <= n; i++) {
            if(lostStudent[i] && reserveStudent[i]) {
                lostStudent[i] = false;
                reserveStudent[i] = false;
            }  
        }
        
        for ( int i = 1; i <= n; i++) {
            if(lostStudent[i]){
                if ( i - 1 >= 1 && reserveStudent[i-1]){
                    lostStudent[i] = false;
                    reserveStudent[i-1] = false;
                }else if ( i + 1 <= n && reserveStudent[i+1]){
                    lostStudent[i] = false;
                    reserveStudent[i+1] = false;
                }
            
            }
        }
        int answer = 0;
        for(int i = 1; i <= n; i++){
            if(!lostStudent[i]) {
                answer++;
            }
        }
        return answer;
    }
}