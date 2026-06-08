import java.io.*;
import java.util.*;

class Solution {
    public int solution(int[][] board, int[][] skill) {
        int rowCount = board.length;
        int colCount = board[0].length;
        int[][] change = new int[rowCount + 1][colCount + 1];
        
        for(int[] curSkill : skill){
            int type = curSkill[0];
            int startRow = curSkill[1];
            int startCol = curSkill[2];
            int endRow = curSkill[3];
            int endCol = curSkill[4];
            int degree = curSkill[5];
            int value = 0;
            
            if ( type == 1){
                value -= degree;
            }else{
                value += degree;
            }
            change[startRow][startCol] += value;
            change[startRow][endCol + 1] -= value;
            change[endRow + 1][startCol] -= value;
            change[endRow + 1][endCol + 1] += value;
        }
        for(int row = 0; row <= rowCount; row++){
            for(int col = 1; col <= colCount; col++){
                change[row][col] += change[row][col - 1];
            }
        }
        for(int col = 0; col <= colCount; col++){
            for(int row = 1; row <= rowCount; row++){
                change[row][col] += change[row - 1][col];
            }
        }
        int answer = 0;
        for(int row = 0; row < rowCount; row++){
            for(int col = 0; col < colCount; col++){
                if ( board[row][col] + change[row][col] > 0){
                    answer++;
                }
            }
        }
        return answer;
    }
}