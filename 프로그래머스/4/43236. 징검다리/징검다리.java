import java.util.*;

/**
<1번 방법>
1. 바위의 위치 정렬
2. n개의 바위 제거
3. 바위들 간의 거리 구하기
    3-1. 그 중 최솟값 구하기
4. 2번을 반복하며 최솟값의 최대값 구하기
=> 이렇게 풀면 시간초과!!

<2번 방법>
바위 사이의 최소 거리를 임의로 가정하여 돌을 제거하고
제거된 돌의 개수를 n과 비교하며 이분탐색 진행
*/

class Solution {
    public int solution(int distance, int[] rocks, int n) {
        int answer = 0;
        
        Arrays.sort(rocks);
        
        // 바위 간의 거리를 구하기 위해 도착지점 추가
        int[] newRocks = new int[rocks.length + 1];
        for(int i=0; i<rocks.length; i++) {
            newRocks[i] = rocks[i];
        }
        newRocks[newRocks.length - 1] = distance;
        
        // 이분탐색 범위 설정 : 바위 사이의 최소 거리 (1 ~ distance)
        int left = 1;
        int right = distance;
        
        while(left <= right) {
            int mid = (left + right) / 2; // 바위 사이의 최소 거리로 가정
            
            // 바위 사이의 거리가 최소 거리 이상인지 확인하기
            
            int removedStone = 0; // 제거된 바위의 수
            int start = 0; // 시작 바위
            
            for(int i=0; i<newRocks.length; i++) {
                int end = newRocks[i]; // 도착 바위
                
                // 바위 사이의 거리가 최소 거리가 안되면 제거
                if(end - start < mid) {
                    removedStone++;
                } else {
                    start = newRocks[i];
                }
            }
            
            // 제거된 바위가 제거해야 할 바위(n)보다 많다면 바위 간의 최소 거리 줄이기
            if(removedStone > n) {
                right = mid - 1;
            } else {
                left = mid + 1;
                answer = mid; // 바위 간의 최소 거리의 최댓값
            }
        }
        
        return answer;
    }
}