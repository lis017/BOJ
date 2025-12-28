import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());


        for (int i = 0; i < n; i++) {
            int matchesCount = Integer.parseInt(br.readLine());

            long[] minDp = new long[101];


            //성냥 한개를 표현하는데 사용하는 개수는 2~7개 사이
            //2개 : 1, 3개 : 7, 4개 : 4, 5개 : 2,3,5, 6개 : 6,9,0, 7개 : 8

		/*
		* mindp[i]는 i개의 성냥개비를 사용하여 만들 수 있는
		* 가장 작은 수를 저장하는 배열, 인덱스 2~8까지는 초기화 필요!
		*
		* 이때 mindp[6]으로 만들 수 있는 숫자는 0,6 두 가지가 있지만
		* 0은 첫번째 자리에 올 수 없으므로 mindp[6]은 6으로 초기화 한다.
		* 다른 숫자 뒤에 올 때는 6보다 작은 0을 사용한다.
		*/


            Arrays.fill(minDp, Long.MAX_VALUE);
            minDp[2] = 1;
            minDp[3] = 7;
            minDp[4] = 4;
            minDp[5] = 2;
            minDp[6] = 6;
            minDp[7] = 8;
            minDp[8] = 10;

            String[] add = {"1", "7", "4", "2", "0", "8"};
            /*
             * 성냥개비 9개로 만들 수 있는 가장 작은수를 구하기 위한 경우의 수에는
             * 2+7, 3+6, 4+5, 5+4, 6+3, 7+2가 있다.
             * 이때 1+8을 고려하지 않는 이유는 성냥개비 1개로 만들 수 있는 수는 없기 때문이다.
             * 이처럼 만들 수 없는 수인 1,0은 고려하지 않아야 한다.
             * 따라서 위에 적은 경우의 수를 모두 비교하여 가장 작은 수를 찾으면 된다.
             * 이때 주의해야할 점은 비교대상은 성냥개비로 만들어진 숫자이다.
             *
             * 점화식은 mindp[n]=(mindp[n-2]+addmin[2], mindp[n-3]+addmin[3], ..., mindp[n-7]+addmin[7])
             */

            for (int j = 9; j <= 100; j++) {
                for (int k = 2; k <= 7; k++) {
                    String line = "" + minDp[j - k] + add[k - 2];
                    minDp[j] = Math.min(Long.parseLong(line), minDp[j]);
                }
            }

	/*
	* 큰 수는 1과 7만을 사용해서 만들 수 있다. 
	* 성냥개비 8개를 사용해서 만들 수 있는 가장 큰 수는 1111
	* 성냥개비 9개를 사용해서 만들 수 있는 가장 큰 수는 711
	* 성냥개비 10개를 사용해서 만들 수 있는 가장 큰 수는 1111
	* 성냥개비 11개를 사용해서 만들 수 있는 가장 큰 수는 71111
	* 
	* 즉, 큰 숫자를 만든다고 큰 수가 되는 것이 아닌 만드는데 성냥개비가 가장 적게드는 1을 사용하여
	* 가장 긴 자릿수를 만드는 것이 큰 수를 만드는 방법이다. 
	* 이때 성냥개비의 갯수가 짝수일 경우는 만들어지는 1의 개수가 딱 떨어지지만 
	* 홀수인 경우에는 성냥개비 1개로 만들 수 있는 수가 없기 때문에 3개로 만들 수 있는 7을 사용해야 한다. 
	* 
	* 따라서 큰 수는 1과 7만을 사용해서 만들 수 있다. 
	*/

            StringBuilder max = new StringBuilder();
            long a = matchesCount / 2;
            long b = matchesCount % 2;
            if (b == 1) {
                max.append("7");
            } else {
                max.append("1");
            }

            for (int j = 1; j < a; j++) {
                max.append("1");
            }
            System.out.println(minDp[matchesCount] + " " + max.toString());

        }
    }
}