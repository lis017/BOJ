import java.io.*;
import java.util.*;

class Solution {
    int solution(int[][] land) {
        //
        int n = land.length;
        int[][] dp = new int[n][4];

        for( int j = 0; j < 4; j++){
            dp[0][j] = land[0][j];
        }
        
        for( int row = 1; row < n; row++){
            for( int col = 0; col < 4; col++){
                int max = 0;
                for(int k = 0; k < 4; k++){
                    if ( col == k) continue;
                    max = Math.max(max, dp[row-1][k]);
                }
                dp[row][col] = land[row][col] + max;
            }
        }
        
        int answer = 0;
        for(int i = 0; i < 4; i++){
            answer = Math.max(dp[n-1][i], answer);
        }
        return answer;
    }
}