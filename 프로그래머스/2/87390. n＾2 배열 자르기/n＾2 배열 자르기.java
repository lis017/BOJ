class Solution {
    public int[] solution(int n, long left, long right) {
        int size = (int)(right - left + 1);
        int[] answer = new int[size];

        int index = 0;

        for (long i = left; i <= right; i++) {
            long row = i / n;
            long col = i % n;

            answer[index++] = (int)(Math.max(row, col) + 1);
        }

        return answer;
    }
}