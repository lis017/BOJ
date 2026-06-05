import java.io.*;
import java.util.*;

class Solution {
    public int solution(int[][] triangle) {
        int n = triangle.length;
        int m = triangle[n - 1].length;
        int[][] maxtri = new int[n][m];
        
        for(int row = 0; row < n; row++ ){
            for(int col = 0; col <= row; col++){
                if(row == 0 && col == 0){
                    maxtri[row][col] = triangle[row][col];
                }
                else if ( col == 0 ) {
                    maxtri[row][col] = maxtri[row-1][col] + triangle[row][col];
                }
                else if ( col == row ) {
                    maxtri[row][col] = maxtri[row-1][col-1] + triangle[row][col];
                }
                else{
                    maxtri[row][col] = Math.max(maxtri[row-1][col-1], maxtri[row-1][col] ) + triangle[row][col];
                }
            }
        }
        int answer = 0;
        for(int col = 0; col < m; col++){
            answer = Math.max(answer, maxtri[n-1][col] );
        }
        return answer;
    }
}