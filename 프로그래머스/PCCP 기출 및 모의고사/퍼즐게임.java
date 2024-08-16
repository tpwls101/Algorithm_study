/**
limit : long 타입!!!

<기출문제2 2번>
시간초과 해결할 것!
*/

class Solution {
    public int solution(int[] diffs, int[] times, long limit) {
        long totalTime = Long.MAX_VALUE;
        int level = 1;

        // 퍼즐을 푸는데 제한시간보다 오래 걸리면 다음 숙련도로 도전
        while(totalTime > limit) {
            totalTime = 0;

            for(int i=0; i<diffs.length; i++) {
                // 숙련도가 퍼즐 난이도보다 높은거나 같은 경우
                if(level >= diffs[i]) {
                    totalTime += times[i];
                }
                // 숙련도가 퍼즐 난이도보다 낮은 경우
                else {
                    totalTime += (times[i] + times[i-1]) * (diffs[i] - level) + times[i];
                }
            }

            if(totalTime > limit) {
                level++;
            }
        }

        return level;
    }
}
