import java.io.*;
import java.util.*;

class Solution {
    public int solution(int[][] triangle) {
        int n = triangle.length;
        int m = triangle[n-1].length;
        int[][] dp = new int[n][m];
        
        for( int row = 0; row < n; row++){
            for ( int col = 0; col <= row; col++){
                if ( row == 0 && col == 0){
                    dp[row][col] = triangle[row][col];
                    //System.out.println(dp[row][col]);
                }
                else if ( col == 0){
                    dp[row][col] = triangle[row][col] + dp[row - 1][col];
                    //System.out.println(dp[row][col]);
                }
                else if ( row == col){
                    dp[row][col] = triangle[row][col] + dp[row-1][col - 1];
                    //System.out.println(dp[row][col]);
                }
                else{
                    dp[row][col] = triangle[row][col] + Math.max(dp[row-1][col-1], dp[row-1][col]);
                    //System.out.println(dp[row][col]);
                }
                    
            }
        }
        int answer = 0;
        for( int i = 0; i < n; i++){
            answer = Math.max(answer, dp[n-1][i]);
        }
        return answer;
    }
}