import java.util.*;

class Solution {

    public int solution(int alp, int cop, int[][] problems) {
        int maxAlp = 0;
        int maxCop = 0;

        // 모든 문제를 풀기 위해 필요한 목표 능력치
        for (int[] problem : problems) {
            maxAlp = Math.max(maxAlp, problem[0]);
            maxCop = Math.max(maxCop, problem[1]);
        }

        // 이미 목표보다 높다면 목표치까지만 사용
        alp = Math.min(alp, maxAlp);
        cop = Math.min(cop, maxCop);

        /*
         * dp[a][c]
         * = 알고력 a, 코딩력 c에 도달하는 최소 시간
         */
        int[][] dp = new int[maxAlp + 1][maxCop + 1];

        // 직접 공부만 했을 때의 최대 시간보다 1 큰 값
        int inf = (maxAlp - alp) + (maxCop - cop) + 1;

        for (int[] row : dp) {
            Arrays.fill(row, inf);
        }

        dp[alp][cop] = 0;

        for (int currentAlp = alp; currentAlp <= maxAlp; currentAlp++) {
            for (int currentCop = cop; currentCop <= maxCop; currentCop++) {

                // 알고력 공부: 1시간 사용하여 알고력 +1
                if (currentAlp < maxAlp) {
                    dp[currentAlp + 1][currentCop] = Math.min(
                            dp[currentAlp + 1][currentCop],
                            dp[currentAlp][currentCop] + 1
                    );
                }

                // 코딩력 공부: 1시간 사용하여 코딩력 +1
                if (currentCop < maxCop) {
                    dp[currentAlp][currentCop + 1] = Math.min(
                            dp[currentAlp][currentCop + 1],
                            dp[currentAlp][currentCop] + 1
                    );
                }

                // 현재 능력치로 풀 수 있는 모든 문제
                for (int[] problem : problems) {
                    int alpReq = problem[0];
                    int copReq = problem[1];
                    int alpReward = problem[2];
                    int copReward = problem[3];
                    int cost = problem[4];

                    if (currentAlp < alpReq || currentCop < copReq) {
                        continue;
                    }

                    // 목표 능력치를 넘어가는 값은 목표치로 잘라냄
                    int nextAlp = Math.min(
                            maxAlp,
                            currentAlp + alpReward
                    );

                    int nextCop = Math.min(
                            maxCop,
                            currentCop + copReward
                    );

                    dp[nextAlp][nextCop] = Math.min(
                            dp[nextAlp][nextCop],
                            dp[currentAlp][currentCop] + cost
                    );
                }
            }
        }

        return dp[maxAlp][maxCop];
    }
}