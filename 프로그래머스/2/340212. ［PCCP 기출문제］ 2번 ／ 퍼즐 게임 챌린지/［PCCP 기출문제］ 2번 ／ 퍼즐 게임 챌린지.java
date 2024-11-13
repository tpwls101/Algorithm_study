import java.util.*;

/**
이분탐색의 범위 : 숙련도 (1 ~ 퍼즐 난이도의 최댓값)

주의할 점
1. totalTime(총 소요시간)의 타입
2. while문의 조건 -> left <= right 로 하면 무한루프에 빠짐
*/

class Solution {
    public int solution(int[] diffs, int[] times, long limit) {
        int answer = 0;
        
        int[] copy = diffs.clone();
        Arrays.sort(copy);
        int right = copy[diffs.length - 1]; // 퍼즐 난이도의 최댓값
        int left = 1;
        int mid = 0;
        
        while(left < right) { // left <= right 로 하면 무한루프에 빠짐
            mid = (left + right) / 2;
            // System.out.print("숙련도 = " + mid);
            
            // 숙련도가 mid 값일 때의 총 퍼즐 소요 시간 구하기
            long totalTime = 0; // long 타입 주의
            for(int i=0; i<diffs.length; i++) {
                if(diffs[i] <= mid) { // 퍼즐 난이도보다 내 숙련도가 더 높거나 같은 경우
                    totalTime += times[i];
                } else { // 퍼즐 난이도가 더 높은 경우
                    totalTime += (times[i] + times[i-1]) * (diffs[i] - mid) + times[i];
                }
            }
            // System.out.println(", 총 소요시간 = " + totalTime);
            
            if(totalTime <= limit) { // 제한 시간 내에 퍼즐을 푼 경우
                right = mid;
            } else {
                left = mid + 1;
            }
        }
        
        return right;
    }
}