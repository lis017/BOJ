import java.io.*;
import java.util.*;

class Solution {
    public int solution(int[][] jobs) {
        Arrays.sort(jobs, (a,b) -> Integer.compare(a[0], b[0]));
        PriorityQueue<int[]> pq = new PriorityQueue<>((a,b) -> Integer.compare(a[1], b[1]));
        int n = jobs.length;
        int index = 0;
        int curtime = 0;
        List<Integer> answer = new ArrayList<>();
        while( answer.size() != n ){
            while (index < n && curtime >= jobs[index][0]){
                pq.offer(jobs[index]);
                index++;
            }
            if ( pq.isEmpty()){
                curtime = jobs[index][0];
            }else{
                int[] curjob = pq.poll();
            curtime += curjob[1];
            answer.add(curtime - curjob[0]);
            }
        }
        int sum = 0;
        for ( int i : answer){
            sum += i;
        }
        return sum / n;
    }
}