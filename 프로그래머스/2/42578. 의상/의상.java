import java.util.*;

/*
1. 종류별로 가능한 옷의 개수를 map에 저장
2. 옷을 선택 안하는 것도 하나의 방법이므로 각 종류별로 +1 한 후 곱해 경우의 수 계산
3. 단, 모두 선택 안하는 공집합의 경우는 불가하므로 -1
*/

class Solution {
    public int solution(String[][] clothes) {
        Map<String, Integer> map = new HashMap<>();
        
        for(String[] cloth : clothes) {
            map.put(cloth[1], map.getOrDefault(cloth[1], 0) + 1);
        }
        
        int answer = 1;
        
        for(int value : map.values()) {
            answer *= (value + 1);
        }
        
        return answer - 1;
    }
}