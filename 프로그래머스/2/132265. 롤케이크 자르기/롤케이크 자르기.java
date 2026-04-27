import java.util.*;
import java.io.*;

class Solution {
    public int solution(int[] topping) {
        Map<Integer, Integer> right = new HashMap<>();
        Set<Integer> left = new HashSet<>();
        int answer = 0;
        
        for(int topp : topping){
            right.put(topp, right.getOrDefault(topp, 0) + 1);
        }
        for(int i = 0; i < topping.length; i++) {
            int top = topping[i];
            left.add(top);
            
            right.put(top, right.get(top) - 1);
            if(right.get(top) == 0){
                right.remove(top);
            }
            if (left.size() == right.size()){
                answer++;
            }
        }
        return answer;
    }
}