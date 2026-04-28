import java.io.*;
import java.util.*;

class Solution {
    public int solution(int k, int[] tangerine) {
        Map<Integer, Integer> sizeCount = new HashMap<>();
        
        for ( int tan : tangerine){
            sizeCount.put(tan, sizeCount.getOrDefault(tan, 0) + 1);
        }
        List<Integer> guelCount = new ArrayList<>(sizeCount.values());
        guelCount.sort((a,b) -> b - a);
        
        int sum = 0;
        int answer = 0;
        for(int i = 0; i < guelCount.size(); i++){
            //6보다 sum이 크거나 같으면 break;
            if ( k <= sum){
                break;
            }
            sum += guelCount.get(i);
            answer++;
        }
        return answer;
    }
}