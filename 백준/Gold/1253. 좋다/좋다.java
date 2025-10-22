import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.HashSet;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int n = Integer.parseInt(br.readLine());
        st = new StringTokenizer(br.readLine());

        HashSet<Integer> hash = new HashSet<>();

        int[] arr = new int[n];
        HashMap<Integer, Integer> hashMap = new HashMap<>();
        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
            hashMap.put(arr[i], hashMap.getOrDefault(arr[i], 0) + 1); // 입력되는 각 숫자의 개수를 센다.
        }

        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                int sum = arr[i] + arr[j];

                if (arr[i] == 0 && arr[j] == 0) {
                    if (hashMap.getOrDefault(sum, 0) >= 3) { //둘다 0인 경우는 3개 인상인 경우에만 가능
                        hash.add(sum);
                    }
                } else if (arr[i] == 0 || arr[j] == 0) {
                    if (hashMap.getOrDefault(sum, 0) >= 2) { //둘 중 하나만 0인경우에는 0이 2개 이상인 경우에만 가능
                        hash.add(sum);
                    }

                } else {
                    hash.add(sum); //계산한 합
                }

            }
        }

        int cnt = 0;
        for (int num : arr) {
            if (hash.contains(num)) cnt++; //계산된 합이 숫자 중에 있다면 더한다.
        }
        System.out.println(cnt);


    }
}
