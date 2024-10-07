import java.util.*;

/**
이분탐색의 범위 설정 : 내가 찾아야 할 정답의 범위를 설정한다!
따라서 심사를 받는데 걸리는 시간의 범위를 구한다. (0 ~ 가장 오래 걸린 심사 시간)

타입 주의!!
특히 long right = times[times.length - 1] * (long)n;
n을 long 타입으로 바꿔줘야 한다.

n명을 심사하면 되는데 n==sum이 안될 수도 있다!
따라서 sum >= n 인 경우에 계속해서 심사 시간의 최솟값을 찾아준다.
*/

class Solution {
    public long solution(int n, int[] times) {
        long answer = 0;
        
        Arrays.sort(times);
        
        long left = 0;
        long right = times[times.length - 1] * (long)n; // 가장 오래 걸리는 심사 시간
        
        while(left <= right) {
            long mid = (left + right) / 2;
            
            // mid 시간 동안 각 심사대별로 몇명의 사람을 심사할 수 있는지 구해서 더하기
            long sum = 0; // mid 시간 동안 검사할 수 있는 사람의 수
            for(int i=0; i<times.length; i++) {
                sum += mid / times[i];
            }
            
            // mid 시간 동안 심사할 수 있는 사람의 수
            // n보다 작으면 -> 심사 시간 늘리기
            // n보다 크면 -> 심시 시간 줄이기
            // n명을 심사하면 되는데 n==sum이 안될 수도 있다!
            if(sum < n) {
                left = mid + 1;
            } else {
                // 심사 시간의 최솟값을 구해야 하므로 while문을 벗어날 때까지 줄이기
                answer = mid;
                right = mid - 1;
            }
        }
        
        return answer;
    }
}