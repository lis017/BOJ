import java.io.*;

public class Main {
	public static void main(String[]args) throws IOException {
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(bf.readLine());
		
		int len = Integer.toString(N).length();
		int ans = 0;
		
		for(int i=0;i<len;i++) {
			int digit = N%10;
			N = N/10;
			
			if(digit>4)
				ans += (digit-1)*Math.pow(9,i);
			else
				ans += digit*Math.pow(9, i);
		}
		System.out.println(ans);
		//*/
	}
}