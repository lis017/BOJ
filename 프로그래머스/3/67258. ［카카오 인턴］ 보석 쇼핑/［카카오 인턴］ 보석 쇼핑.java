import java.util.*;

class Solution {
    public int[] solution(String[] gems) {
        Set<String> set = new HashSet<>();

        for (String gem : gems) {
            set.add(gem);
        }

        int targetCount = set.size();
        int last = gems.length;

        Map<String, Integer> map = new HashMap<>();

        int left = 0;
        int right = 0;

        int[] result = new int[2];
        int min = Integer.MAX_VALUE;

        while (right < last) {
            map.put(gems[right], map.getOrDefault(gems[right], 0) + 1);

            while (map.size() == targetCount) {
                if (right - left < min) {
                    min = right - left;
                    result[0] = left + 1;
                    result[1] = right + 1;
                }

                String leftGem = gems[left];

                map.put(leftGem, map.get(leftGem) - 1);

                if (map.get(leftGem) == 0) {
                    map.remove(leftGem);
                }

                left++;
            }

            right++;
        }

        return result;
    }
}