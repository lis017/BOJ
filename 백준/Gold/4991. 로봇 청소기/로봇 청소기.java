import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static int[] dy = {-1, 0, 1, 0};
    static int[] dx = {0, 1, 0, -1};
    static int answer = Integer.MAX_VALUE;
    static boolean[] check;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        while(true) {
            answer = Integer.MAX_VALUE;
            StringTokenizer st = new StringTokenizer(br.readLine());
            int C = Integer.parseInt(st.nextToken());
            int R = Integer.parseInt(st.nextToken());
            if (R + C == 0) break;

            char[][] map = new char[R][C];
            Dot[] dusts = new Dot[11];
            int dust_idx = 1;

            /** 배열에 데이터 삽입 **/
            for (int i = 0 ; i < R ; i++) {
                String str = br.readLine();
                for (int j = 0 ; j < C ; j++) {
                    map[i][j] = str.charAt(j);
                    if (map[i][j] == 'o') {
                        dusts[0] = new Dot(j, i);
                    }
                    else if (map[i][j] == '*'){
                        dusts[dust_idx++] = new Dot(j, i);
                    }
                }
            }
            /** 인접 리스트 선언 **/
            ArrayList<Node>[] adj_list = new ArrayList[dust_idx];
            for (int i = 0 ; i < dust_idx ; i++) {
                adj_list[i] = new ArrayList<Node>();
            }

            /** BFS로 모든 최단 경로에 대한 정보를 저장한다. **/
            for (int start = 0 ; start < dust_idx - 1; start++) {
                for (int end = start + 1 ; end < dust_idx ; end++) {
                    int weight = BFS(dusts[start], dusts[end], R, C, map);
                    if (weight == -1) continue;
                    // 양방향 노드
                    adj_list[start].add(new Node(end, weight));
                    adj_list[end].add(new Node(start, weight));
                }
            }
            /** 백트래킹을 이용하여 모든 경로를 탐색하여 최솟값을 출력한다. **/
            check = new boolean[dust_idx];
            check[0] = true;
            Permutation(0, 0, adj_list, 0, dust_idx);
            System.out.println(answer == Integer.MAX_VALUE ? -1 : answer);
        }
    }
    static void Permutation(int start, int depth, ArrayList<Node>[] adj_list, int sum, int dusts) {
        if (depth == dusts - 1) {
            answer = Math.min(answer, sum);
            return;
        }

        for (Node next : adj_list[start]) {
            if (check[next.end]) continue;
            check[next.end] = true;
            Permutation(next.end, depth + 1, adj_list, sum + next.weight, dusts);
            check[next.end] = false;
        }
    }
    static int BFS(Dot start, Dot end, int R, int C, char[][] map) {
        Queue<Dot> q = new LinkedList<>();
        boolean[][] isVisited = new boolean[R][C];
        q.offer(new Dot(start.x, start.y, 0));
        isVisited[start.y][start.x] = true;

        while (!q.isEmpty()) {
            Dot d = q.poll();

            if (d.y == end.y && d.x == end.x) {
                return d.cnt;
            }
            for (int i = 0 ; i < 4 ; i++) {
                int nx = d.x + dx[i];
                int ny = d.y + dy[i];
                if (nx < 0 || nx >= C || ny < 0 || ny >= R || isVisited[ny][nx] || map[ny][nx] == 'x') continue;
                q.offer(new Dot(nx, ny, d.cnt + 1));
                isVisited[ny][nx] = true;
            }
        }
        return -1;
    }
}
class Dot {
    int x;
    int y;
    int cnt;
    Dot(int x, int y) {
        this.x = x;
        this.y = y;
    }
    Dot(int x, int y, int cnt) {
        this.x = x;
        this.y = y;
        this.cnt = cnt;
    }
}
class Node {
    int end;
    int weight;
    Node(int end, int weight) {
        this.end = end;
        this.weight = weight;
    }
}