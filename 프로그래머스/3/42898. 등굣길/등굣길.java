import java.io.*;
import java.util.*;

class Solution {
    public int solution(int m, int n, int[][] puddles) {
        int[][] map = new int[n + 1][m + 1];
        boolean[][] water = new boolean[n + 1][m + 1];
        int MOD = 1000000007;
        
        for ( int[] puddle : puddles){
            water[ puddle[1] ] [puddle[0] ] = true;
        }
        
        for(int row = 1; row <= n; row++){
            for(int col = 1; col <= m; col++){
                if ( row == 1 && col == 1){
                    map[row][col] = 1;
                }
                else if ( water[row][col] ) {
                    map[row][col] = 0;
                }
                else{
                    map[row][col] = ( (map[row - 1][col] % MOD) + ( map[row][col-1] % MOD) ) % MOD;
                }
            }
        }
            return map[n][m];
    }
}