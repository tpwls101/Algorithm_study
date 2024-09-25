/**
갈색과 노란색 격자의 최소 수를 고려하면 3x3부터 시작함
*/

class Solution {
    public int[] solution(int brown, int yellow) {
        int[] answer = new int[2];
        
        for(int y=3; y<=brown+yellow; y++) {
            for(int x=y; x<=brown+yellow; x++) {
                if(x * y == brown + yellow) {
                    // 색깔별로 갯수 맞는지 체크
                    if(x*2 + (y-2)*2 == brown && (x-2)*(y-2) == yellow) {
                        answer[0] = x;
                        answer[1] = y;
                        return answer;
                    }
                }
            }
        }
        
        return answer;
    }
}