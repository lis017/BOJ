import java.io.*;
import java.util.*;

class Solution {
    int solution(int[][] land) {
        int n = land.length;
        int m = 4;
        int[][] dp = new int[n][4];
        
        for(int col = 0; col < 4; col++){
            dp[0][col] = land[0][col];
        }
        
        for( int row = 1; row < n; row++){
            for(int col = 0; col < 4; col++){
                int max = 0;
                for(int i = 0; i < 4; i++){
                    if ( col == i){
                        continue;
                    }
                    max = Math.max(max, dp[row -1][i]);
                }
                //System.out.println("max 값이다" + max);
                dp[row][col] = land[row][col] + max;
                //System.out.println("dp 값이다" + dp[row][col]);
            }
        }
        int answer = 0;
        for(int i = 0; i < 4; i++){
            answer = Math.max(answer, dp[n-1][i]);
        }
        return answer;
    }
}