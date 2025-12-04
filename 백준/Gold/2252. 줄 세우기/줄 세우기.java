import java.io.*;
import java.util.*;

public class Main {

	static int n,m;
	static List<Integer>[] list;
	static int[] rank;
	static StringBuilder sb;
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		
		list = new ArrayList[n+1];
		rank = new int[n+1];
		sb = new StringBuilder();
		for(int i=0; i<n+1; i++) {
			list[i] = new ArrayList<>();
		}
		
		for(int i=0; i<m; i++) {
			st=  new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			
			list[a].add(b);
			rank[b]++;
		}
		
		bfs();
		System.out.println(sb.toString());
	}
	
	static void bfs() {
		Queue<Integer> q = new LinkedList<>();
		
		for(int i=1; i<n+1; i++) {
			if(rank[i] == 0) {
				q.add(i); // 루트노드 
			}
		}
			
		while(!q.isEmpty()) {
			int pos = q.poll();
			
			sb.append(pos+" ");
			for(int next : list[pos]) {
				rank[next]--;
				if(rank[next]==0) {
					q.add(next);
				}
			}
		}
		
		
		
	}
}