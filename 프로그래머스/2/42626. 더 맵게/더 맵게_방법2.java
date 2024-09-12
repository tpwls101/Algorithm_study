import java.util.*;

/**
효율성 통과가 중요!!
scoville 배열의 길이와 K의 범위가 크기 때문에 list를 사용하면 시간초과 난다.
while문 안에서 list를 정렬하면 
    Collections.sort() : O(NlogN)
    정렬을 계속해서 반복하므로 결국 O(N^2logN)의 시간복잡도를 가진다.

따라서 Heap 자료구조를 활용해야 한다.
힙은 삽입/삭제에 O(logN)의 시간복잡도를 가진다.
while문을 아무리 돌려도 결국 O(NlogN)의 시간복잡도를 가지므로 괜찮다.
*/

class Solution {
    public int solution(int[] scoville, int K) {
        PriorityQueue<Integer> queue = new PriorityQueue<>();
        for(int i : scoville) {
            queue.add(i);
        }
        
        int count = 0;
        while(queue.size() > 1) {
            int first = queue.poll();
            if(first >= K) break;
            int second = queue.poll();
            queue.add(first + second * 2);
            count++;
        }
        
        if(queue.peek() < K) return -1;
        
        return count;
    }
}
