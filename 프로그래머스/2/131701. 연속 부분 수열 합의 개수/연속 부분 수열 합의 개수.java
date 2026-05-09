/*
유형: set, 슬라이딩윈도우?

1분설계: 반복문에 갯수만큼 더하고, set넣고
    다음으로 left right 둘다 옮겨가며 set에 저장. 끝
*/
import java.io.*;
import java.util.*;

class Solution {
    public int solution(int[] elements) {
        Set<Integer> set = new HashSet<>();
        int n = elements.length;
        
        //반복문
        for(int size = 1; size <= n; size++){
            //? ~ i까지 sum에 더할거야.
            int sum = 0;
            for(int curlast = 0; curlast < size; curlast++){
                sum += elements[curlast];
            }
            set.add(sum);
            
            //반복문
            //원래 첫째 뺴고, 다음거 sum에 더하고 set에 넣고를 1 ~ < n ?
            for(int index = 1; index < n; index++){
                sum -= elements[index];
                sum += elements[(size + index - 1) % n];
                set.add(sum);
            }
        }
        return set.size();
    }
}