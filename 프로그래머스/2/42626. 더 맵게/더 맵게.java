import java.io.*;
import java.util.*;

class Solution {
    public int solution(int[] scoville, int K) {
        PriorityQueue<Integer> priorityQueue = new PriorityQueue<>();
        
        for (int value : scoville) {
            priorityQueue.offer(value);
        }
        
        int count = 0;
        
        while(priorityQueue.size() >= 2 && priorityQueue.peek() < K) {
            int first = priorityQueue.poll();
            int second = priorityQueue.poll();
            
            int mixed = first + (second * 2);
            priorityQueue.offer(mixed);
            count++;
        }
        
        return priorityQueue.peek() >= K ? count: -1;
    }
}