import java.io.*;
import java.util.*;

class Solution {
    public int[] solution(int[] sequence, int k) {
        int left = 0;
        int sum = 0;
        int minLength = Integer.MAX_VALUE;
        int[] answer = new int[2];
        int length = 0;
        
        for(int right = 0; right < sequence.length; right++){
            sum += sequence[right];
            while(sum > k){
                sum -= sequence[left];
                left ++;
            }
            if ( sum == k){
                length = right - left;
                if ( length < minLength){
                    answer[0] = left;
                    answer[1] = right;
                    minLength = length;
                }
            }
        }
        return answer;
    }
}