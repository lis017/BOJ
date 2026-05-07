/*
bfs
X면 안가고
방문 아직 안했으며 x아니면 bfs호출
bfs도 x면 뭐 안하고. 합칠때마다 bfs별 sum에 저장해둬. 값을
*/

//유형 bfs

/*
1분 뼈대설계.
*/

import java.io.*;
import java.util.*;

class Solution {
    boolean[][] visited;
    int[] dx = {-1, 0, 1, 0};
    int[] dy = {0, 1, 0, -1};
    int row;
    int col;
    public int[] solution(String[] maps) {
        row = maps.length;
        col = maps[0].length();
        visited = new boolean[row][col];
        List<Integer> answer = new ArrayList<>();
        
        for(int i = 0; i < row; i++){
            for(int j = 0; j < col; j++){
                if(!visited[i][j] && maps[i].charAt(j) != 'X'){
                    answer.add(bfs(maps, i, j));
                }
            }
        }
        Collections.sort(answer);
        if(answer.isEmpty()){
            answer.add(-1);
        }
        return answer.stream().mapToInt(Integer::intValue).toArray();
    }
    private int bfs(String[] maps, int startx, int starty){
        Queue<int[]> q = new ArrayDeque<>();
        q.offer(new int[] {startx, starty});
        visited[startx][starty] = true;
        int sum = 0;
        sum += maps[startx].charAt(starty) - '0';
        
        while(!q.isEmpty()){
            int[] cur = q.poll();
            int curx = cur[0];
            int cury = cur[1];
            for(int i= 0; i < 4; i++){
                int nx = curx + dx[i];
                int ny = cury + dy[i];
                if(nx < 0 || ny < 0 | nx >= row || ny >= col || visited[nx][ny] || maps[nx].charAt(ny) == 'X'){
                    continue;
                }
                q.offer(new int[] {nx,ny});
                visited[nx][ny] = true;
                sum += maps[nx].charAt(ny) - '0';
            }
        }
        return sum;
    }
}
