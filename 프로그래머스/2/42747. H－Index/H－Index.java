import java.util.*;

class Solution {
    public int solution(int[] citations) {
        int answer = 0;
        
        Arrays.sort(citations);
        
        for(int i=0; i<citations.length; i++) {
            int h = citations.length - i; // citations[i]번 이상 인용된 횟수
            
            if(citations[i] >= h) {
                answer = h;
                break;
            }
        }
        
        return answer;
    }
}
