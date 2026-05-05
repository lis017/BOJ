import java.io.*;
import java.util.*;

class Solution {
    public int solution(String s) {
        Queue<Character> q = new ArrayDeque<>();
        int answer = 0;
        int n = s.length();
        for(int i = 0; i < n; i++){
            q.offer(s.charAt(i));
        }
        
        for(int i = 0; i < n; i++){
            if(i != 0){
                q.offer(q.poll());
            }
            Stack<Character> stack = new Stack<>();
            boolean valid = true;
            for(char c : q){
                if(c == '(' || c == '{' || c == '['){
                stack.push(c);
                }
                else{
                    if(stack.isEmpty()){
                        valid = false;
                        break;
                    }
                    char top = stack.pop();
                    if(top != '(' && c == ')'){
                        valid = false;
                        break;
                    }else if(top != '{' && c == '}'){
                        valid = false;
                        break;
                    }else if(top != '[' && c == ']'){
                        valid = false;
                        break;
                    }
                }
            }
            if(valid && stack.isEmpty()){ answer++; }
        }
        return answer;
    }
}