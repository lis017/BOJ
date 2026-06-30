import java.io.*;
import java.util.*;

class Solution {
    public String solution(String number, int k) {
        StringBuilder sb = new StringBuilder();
        int n = number.length();
        
        for(int i = 0; i < n; i++){
            while( sb.length() != 0 && k > 0&& sb.charAt(sb.length() - 1) < number.charAt(i)){
                sb.deleteCharAt(sb.length() - 1);
                k--;
            }
            sb.append(number.charAt(i));
        }
        while(k > 0){
            sb.deleteCharAt(sb.length() - 1);
            k--;
        }
        return sb.toString();
    }
}