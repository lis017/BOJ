import java.io.*;
import java.util.*;

class Solution {
    public int solution(int[] queue1, int[] queue2) {
        int n = queue1.length + queue2.length;
        int[] arr = new int[n];
        long sum1 = 0;
        long totalSum = 0;
        
        for(int i = 0; i < queue1.length; i++){
            arr[i] = queue1[i];
            sum1 += queue1[i];
            totalSum += queue1[i];
        }
        for(int i = 0; i < queue2.length; i++){
            arr[i + queue1.length] = queue2[i];
            totalSum += queue2[i];
        }
        
        if(totalSum % 2 != 0)return -1;
        
        long target = totalSum / 2;
        int left = 0;
        int right = queue1.length - 1;
        int count = 0;
        
        int maxCount = n * 2;
        
        while(count <= maxCount){
            if(sum1 == target){
                return count;
            }
            else if(sum1 > target){
                sum1 -= arr[left % n];
                left++;
            }
            else{
                right++;
                sum1 += arr[right % n];
            }
            count++;
        }
        return -1;
    }
}