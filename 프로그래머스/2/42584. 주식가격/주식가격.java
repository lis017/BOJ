import java.io.*;
import java.util.*;

class Solution {
    public int[] solution(int[] prices) {
        Stack<Integer> stack = new Stack<>();
        int n = prices.length;
        int[] answer = new int[n];
        for( int i = 0; i < n; i++){
            while(!stack.isEmpty() && prices[stack.peek()] > prices[i]){
                answer[stack.peek()] = i - stack.peek();
                stack.pop();
            }
            stack.push(i);
        }
        while(!stack.isEmpty()){
            answer[stack.peek()] = n-1 - stack.peek();
            stack.pop();
        }
        return answer;
    }
}