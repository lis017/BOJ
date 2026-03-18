import java.util.*;
import java.io.*;

class Solution {
    static boolean[][] adj;
    
    public int solution(int n, int[][] wires) {
        int answer = n;
        adj = new boolean[n+1][n+1];
        
        for (int [] wire : wires){
            int v1 = wire[0];
            int v2 = wire[1];
            adj[v1][v2] = true;
            adj[v2][v1] = true;
        }
        
        for (int[] wire : wires){
            int v1 = wire[0];
            int v2 = wire[1];
            
            adj[v1][v2] = false;
            adj[v2][v1] = false;
            
            int count = bfs(v1, n);
            
            int diff = Math.abs(count -  (n-count));
            answer = Math.min(answer, diff);
            
            adj[v1][v2] = true;
            adj[v2][v1] = true;
        }
        
        return answer;
    }
    
    static int bfs(int start, int n){
        boolean[] visited = new boolean[n+1];
        Queue<Integer> q = new LinkedList<>();
        
        q.offer(start);
        visited[start] = true;
        int cnt = 1;
        
        while(!q.isEmpty()){
            int cur = q.poll();
            
            for(int i = 1; i <= n; i++){
                if(visited[i] || !adj[cur][i]){
                    continue;
                }
                visited[i] = true;
                q.offer(i);
                cnt++;
            }
        }
        return cnt;
    }
}