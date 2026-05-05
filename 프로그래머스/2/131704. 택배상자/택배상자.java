import java.io.*;
import java.util.*;

class Solution {
    public int solution(int[] order) {
        int answer = 0;
        Stack<Integer> stack = new Stack<>();
        int cur = 1;
        int n = order.length;
        
        for(int or : order){
            if( !stack.isEmpty() && or == stack.peek()){
                stack.pop();
                answer++;
                continue;
            }
            while( cur <= n && cur != or){
                stack.push(cur);
                cur++;
            }
            if( cur <= n && cur == or){
                answer++;
                cur++;
            }else if(cur > n){
                break;
            }
        }
        return answer;
    }
}