import java.util.*;

/**
한 종류에 대해 안입는 경우도 포함되므로 의상개수+1 로 계산
다만, 모두 안입는 경우는 포함하면 안되므로 최종 결과에서 -1
*/

class Solution {
    public int solution(String[][] clothes) {
        int answer = 1;
        
        Map<String, Integer> map = new HashMap<>();
        for(int i=0; i<clothes.length; i++) {
            map.put(clothes[i][1], map.getOrDefault(clothes[i][1], 0) + 1);
        }
        
        for(int value : map.values()) {
            answer *= (value + 1);
        }
        
        return answer - 1; // 아무것도 안입는 경우의 수 제외
    }
}