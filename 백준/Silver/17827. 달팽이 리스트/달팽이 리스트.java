import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

/*
달팽이 리스트
https://www.acmicpc.net/problem/17827
 */
public class Main {
	static int n,m,v;
	static int[] snailArray;
	public static void main(String[] args) throws IOException {
		InputReader reader = new InputReader();
//		InputReader reader = new InputReader("testcase.txt");
		StringTokenizer st = new StringTokenizer(reader.readLine());
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		v = Integer.parseInt(st.nextToken());

		snailArray = new int[n];
		st = new StringTokenizer(reader.readLine());
		for (int i = 0; i < n; i++) {
			snailArray[i] = Integer.parseInt(st.nextToken());
		}
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < m; i++) {
			int k = reader.readInt();
			sb.append(getKthValue(k)+"\n");
		}
		sb.deleteCharAt(sb.length()-1);
		System.out.println(sb.toString());
	}
	//k번째 요소를 반환한다.
	private static int getKthValue(int k) {
		//k번째 요소가 n보다 작은 경우 바로 반환
		if(k < n) {
			return snailArray[k];
		}
		//k번째 요소가 n보다 크거나 같은 경우동
		int snailLength = n-v+1; // 순환되는 부분 길이
		k -= v-1; // 순환되지 않은 크기만큼 빼준다.
		return snailArray[k%snailLength + v-1]; //snailLength로 나머지 연산 후 v-1만큼 자리이
	}
}
class InputReader {
	private BufferedReader br;

	public InputReader() {
		br = new BufferedReader(new InputStreamReader(System.in));
	}

	public InputReader(String filepath) {
		try {
			br = new BufferedReader(new FileReader(filepath));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}

	public List<Character> readLineIntoCharList() throws IOException {
		List<Character> l = new ArrayList<>();
		while(true) {
			int readVal = br.read();
			if(readVal == '\n' || readVal == -1) break;
			l.add((char)readVal);
		}
		return l;
	}

	public boolean ready() throws IOException {
		return br.ready();
	}

	public String readLine() throws IOException {
		return br.readLine();
	}
	public int readInt() throws IOException {
		return Integer.parseInt(readLine());
	}
	public Long readLong() throws IOException {
		return Long.parseLong(readLine());
	}
}