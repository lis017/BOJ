import java.util.*;

class Solution {
    public int[] solution(int[] progresses, int[] speeds) {
        Queue<Integer> q = new LinkedList<>();
        
        for(int i = 0; i < progresses.length; i++){
            int day = (int)Math.ceil((100.0 - progresses[i]) / speeds[i]);
            q.offer(day);
        }
        
        List<Integer> answerList = new ArrayList<>();
        while(!q.isEmpty()){
            int day = q.poll();
            int count = 1;
            
            while(!q.isEmpty() && day >= q.peek()){
                q.poll();
                count++;
            }
            answerList.add(count);
        }
        return answerList.stream().mapToInt(i -> i).toArray();
    }
}