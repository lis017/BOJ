import java.util.*;

class Solution {

    static int[] parent;

    public int solution(int n, int[][] costs) {
        int answer = 0;
        parent = new int[n];

        // 1. 각 섬의 대표를 자기 자신으로 초기화
        for (int i = 0; i < n; i++) {
            parent[i] = i;
        }

        // 2. 다리 비용 기준 오름차순 정렬
        Arrays.sort(costs, (a, b) -> Integer.compare(a[2], b[2]));

        // 3. 가장 싼 다리부터 선택
        for (int[] cost : costs) {
            int islandA = cost[0];
            int islandB = cost[1];
            int bridgeCost = cost[2];

            // 4. 두 섬이 아직 연결되어 있지 않으면 연결
            if (find(islandA) != find(islandB)) {
                union(islandA, islandB);
                answer += bridgeCost;
            }
        }

        return answer;
    }

    // 해당 섬이 속한 그룹의 대표 찾기
    static int find(int island) {
        if (parent[island] == island) {
            return island;
        }

        parent[island] = find(parent[island]);
        return parent[island];
    }

    // 두 섬을 같은 그룹으로 합치기
    static void union(int islandA, int islandB) {
        int rootA = find(islandA);
        int rootB = find(islandB);

        if (rootA != rootB) {
            parent[rootB] = rootA;
        }
    }
}