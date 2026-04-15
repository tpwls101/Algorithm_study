import java.util.*;

/**
우선순위 큐에 Process 객체를 저장해야 하나 했는데
우선순위가 작은 프로세스가 먼저 나오면 뒤로 보내야하는 것을 처리하기 어려웠다.
대신 그럴 필요 없이 우선순위 큐에 우선순위를 큰 순서부터 차례대로 저장하고
큐가 빌 때까지 for문을 계속 돌리면서
우선순위가 큰 것부터 프로세스를 하나씩 실행하고 (실행하면 큐에서 제거 + n번째로 실행함을 저장)
해당 프로세스가 location 위치에 있는 것이라면 실행 순서를 리턴하면 된다.

생각 못한 부분은 while문과 for문을 계속 돌리면서 원하는 답이 나올 때까지 찾는 것이었다.
*/

class Solution {
    public int solution(int[] priorities, int location) {
        PriorityQueue<Integer> pq = new PriorityQueue<>(Collections.reverseOrder()); // 우선순위가 큰 순서대로
        
        for(int i=0; i<priorities.length; i++) {
            pq.offer(priorities[i]);
        }
        
        int answer = 0; // location 위치에 있는 프로세스가 몇 번째로 실행되는가
        
        while(!pq.isEmpty()) {
            for(int i=0; i<priorities.length; i++) {
                if(pq.peek() == priorities[i]) {
                    pq.poll();
                    answer++;
                    
                    if(location == i) return answer;
                }
            }
        }
        
        return answer;
    }
}