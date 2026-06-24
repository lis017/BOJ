import java.io.*;
import java.util.*;

class Solution {
    public int solution(int[][] triangle) {
        int n = triangle.length;
        int maxm = triangle[n-1].length;
        int[][] dp = new int[n][maxm];
        for(int row = 0; row < n; row++){
            int m = triangle[row].length;
            for(int col = 0; col < m; col++){
                if ( row == 0 && col == 0){
                    dp[row][col] = triangle[row][col];
                }
                else if ( col == 0){
                    dp[row][col] = dp[row - 1][col] + triangle[row][col];
                }else if ( col == m-1){
                    dp[row][col] = dp[row - 1][col - 1] + triangle[row][col];
                }
                else{
                    dp[row][col] = Math.max(dp[row - 1][col - 1], dp[row - 1][col]) + triangle[row][col];
                }
            }
        }
        int max = 0;
        //row n-1일때 싹 보고 최댓값을 구해야돼.
        for(int col = 0; col < n; col++){
            max = Math.max(dp[n-1][col], max);
        }
        return max;
    }
}