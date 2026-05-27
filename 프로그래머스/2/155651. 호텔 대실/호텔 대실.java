import java.util.*;

class Solution {
    public int solution(String[][] book_time) {
        int[] time = new int[24 * 60 + 10 + 1];

        for (String[] book : book_time) {
            int start = toMinute(book[0]);
            int end = toMinute(book[1]) + 10;

            time[start] += 1; // 방 사용 시작
            time[end] -= 1;   // 청소까지 끝난 뒤 방 반환
        }

        int answer = 0;
        int current = 0;

        for (int i = 0; i < time.length; i++) {
            current += time[i];              // 현재 사용 중인 방 개수
            answer = Math.max(answer, current); // 최댓값 갱신
        }

        return answer;
    }

    private int toMinute(String time) {
        String[] split = time.split(":");

        int hour = Integer.parseInt(split[0]);
        int minute = Integer.parseInt(split[1]);

        return hour * 60 + minute;
    }
}