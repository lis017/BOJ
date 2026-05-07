import java.io.*;
import java.util.*;

class Solution {
    public int solution(int[] elements) {
        Set<Integer> answer = new HashSet<>();
        int n = elements.length;
        
        for(int length = 1; length <= n; length++){
            int sum = 0;
            for(int index = 0; index < length; index++){
                sum += elements[index];
            }
            answer.add(sum);
            for(int start = 1; start < n; start++){
                sum -= elements[start - 1];
                sum += elements[(start + length - 1) % n];
                answer.add(sum);
            }
        }
        return answer.size();
    }
}