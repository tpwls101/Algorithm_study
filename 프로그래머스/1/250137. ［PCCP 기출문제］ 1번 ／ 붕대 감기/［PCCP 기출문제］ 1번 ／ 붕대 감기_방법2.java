import java.util.*;

/**
시간이 1초씩 증가하면서 attacks 배열의 인덱스를 어떻게 바꿀 것인가?
처음에는 for문을 2번 사용하면서 코드가 이상해졌는데
attacks 배열의 인덱스를 변수로 선언해서 사용할 수 있을 것 같다.
*/

class Solution {
    public int solution(int[] bandage, int health, int[][] attacks) {
        int answer = health; // 현재 체력
        int attacksIdx = 0; // attacks 배열의 인덱스
        int keptTime = 0; // 연속 성공 횟수
        int time = attacks[attacks.length-1][0];
        
        // 몬스터의 마지막 공격 시간까지 진행
        for(int i=1; i<=time; i++) {
            
            // 몬스터에게 공격받으면 피해량 만큼 체력 감소
            if(i == attacks[attacksIdx][0]) {
                answer -= attacks[attacksIdx][1];
                attacksIdx++;
                keptTime = 0; // 공격받으면 연속 성공 횟수 초기화
                // 체력이 0이하가 되면 게임 종료
                if(answer <= 0) {
                    return -1;
                }
            } 
            
            // 공격받지 않았다면 체력 회복
            else {
                answer += bandage[1];
                keptTime++;

                // 연속으로 붕대 감기에 성공하면 추가 체력 획득
                if(keptTime == bandage[0]) {
                    answer += bandage[2];
                    keptTime = 0;
                }

                // 체력은 최대 체력을 넘을 수 없음
                if(answer > health) {
                    answer = health;
                }
            }
            
        }
        
        return answer;
    }
}
