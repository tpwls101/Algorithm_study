import java.util.*;

/**
말이 너무 헷갈린다
문제 별로임 ㅜㅜ
*/

class Solution {
    public int solution(int[] citations) {
        int answer = 0;
        
        Arrays.sort(citations);
        
        for(int i=0; i<citations.length; i++) {
            int h = citations.length - i; // citations[i]번 이상 인용된 횟수
            
            // ??? ㅜㅜ
            if(citations[i] >= h) {
                answer = h;
                break;
            }
        }
        
        return answer;
    }
}
