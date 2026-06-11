import java.io.*;
import java.util.*;

class Solution {
    public int solution(String[][] clothes) {
        Map<String, Integer> map = new HashMap<>();
        
        for( String[] clothe : clothes){
            map.put(clothe[1], map.getOrDefault(clothe[1], 0) + 1 );
        }
        int count = 1;
        for(String c : map.keySet() ){
            count *= map.get(c) + 1;
        }
        return count - 1;
    }
}