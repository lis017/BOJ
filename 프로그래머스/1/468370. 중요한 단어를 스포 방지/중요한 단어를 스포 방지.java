import java.util.*;

class Solution {

    static class Word {
        String text;
        int start;
        int end;

        Word(String text, int start, int end) {
            this.text = text;
            this.start = start;
            this.end = end;
        }
    }

    public int solution(String message, int[][] spoiler_ranges) {
        List<Word> words = parseWords(message);

        Set<String> appearedInNormalArea = new HashSet<>();
        List<Word>[] revealBuckets = new ArrayList[spoiler_ranges.length];
        for (int i = 0; i < spoiler_ranges.length; i++) {
            revealBuckets[i] = new ArrayList<>();
        }

        int rangePointer = 0;

        for (Word word : words) {
            // 현재 단어 시작점보다 완전히 왼쪽에 끝난 스포 구간은 넘김
            while (rangePointer < spoiler_ranges.length &&
                   spoiler_ranges[rangePointer][1] < word.start) {
                rangePointer++;
            }

            boolean isSpoilerWord = false;
            int lastRangeIndex = -1;

            // 현재 단어와 겹치는 모든 스포 구간 확인
            int k = rangePointer;
            while (k < spoiler_ranges.length &&
                   spoiler_ranges[k][0] <= word.end) {
                isSpoilerWord = true;
                lastRangeIndex = k;
                k++;
            }

            if (!isSpoilerWord) {
                // 스포 구간에 전혀 걸치지 않은 단어 = 일반 구간에서 등장한 단어
                appearedInNormalArea.add(word.text);
            } else {
                // 마지막 스포 구간이 열릴 때 이 단어가 공개됨
                revealBuckets[lastRangeIndex].add(word);
            }
        }

        int answer = 0;
        Set<String> revealedSpoilerWords = new HashSet<>();

        for (int i = 0; i < spoiler_ranges.length; i++) {
            // 같은 구간에서 여러 단어가 열리면, parseWords 순서 자체가 왼쪽 -> 오른쪽 순서
            for (Word word : revealBuckets[i]) {
                if (appearedInNormalArea.contains(word.text)) {
                    continue;
                }
                if (revealedSpoilerWords.contains(word.text)) {
                    continue;
                }

                answer++;
                revealedSpoilerWords.add(word.text);
            }
        }

        return answer;
    }

    private List<Word> parseWords(String message) {
        List<Word> words = new ArrayList<>();
        int n = message.length();
        int start = 0;

        while (start < n) {
            int end = start;
            while (end < n && message.charAt(end) != ' ') {
                end++;
            }

            words.add(new Word(message.substring(start, end), start, end - 1));
            start = end + 1;
        }

        return words;
    }
}