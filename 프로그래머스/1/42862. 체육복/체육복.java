import java.util.*;

class Solution {
    public int solution(int n, int[] lost, int[] reserve) {
        int answer = n - lost.length;
        
        Arrays.sort(lost);
        Arrays.sort(reserve);
        
        // List<Integer> list = new ArrayList<>();
        // for(int i=0; i<reserve.length; i++) {
        //     list.add(reserve[i]);
        // }
        
        // 내 옷을 도난당한 경우 먼저 해결해야 함
        for(int i=0; i<lost.length; i++) {
            for(int j=0; j<reserve.length; j++) {
                if(lost[i] == reserve[j]) {
                    answer++;
                    lost[i] = -1;
                    reserve[j] = -1;
                }
            }
        }
        
        // 여벌옷 없고 도난당한 친구들
        for(int i=0; i<lost.length; i++) {
            for(int j=0; j<reserve.length; j++) {
                if(lost[i]-1 == reserve[j] || lost[i]+1 == reserve[j]) {
                    // 여벌옷 빌려주면 -1 처리 (0으로 처리하면 lost[i]-1이랑 겹치니 주의)
                    answer++;
                    lost[i] = -1;
                    reserve[j] = -1;
                    break;
                }
            }
        }
        
        return answer;
    }
}