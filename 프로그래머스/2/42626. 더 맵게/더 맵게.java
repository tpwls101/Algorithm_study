import java.util.*;

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