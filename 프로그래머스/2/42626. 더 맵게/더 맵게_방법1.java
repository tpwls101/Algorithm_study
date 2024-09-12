import java.util.*;

/**
list를 사용한 방법
O(N^2logN)의 시간복잡도를 가져 효율성을 통과하지 못한다.
*/

class Solution {
    public int solution(int[] scoville, int K) {
        List<Integer> list = new ArrayList<>();
        for(int i : scoville) {
            list.add(i);
        }
        
        int count = 0;
        while(true) {
            Collections.sort(list);
            
            int newScoville = 0;
            if(list.size() == 0) return -1;
            if(list.get(0) >= K) break;
            newScoville += list.get(0);
            list.remove(0);
            
            if(list.size() == 0) return -1;
            newScoville += list.get(0) * 2;
            list.remove(0);
            
            list.add(newScoville);
            count++;
        }
        
        return count;
    }
}
