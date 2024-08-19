import java.util.*;

/**
<모의고사1 1번>
*/

class Solution {
    public String solution(String input_string) {
        String answer = "";

        for(int i=0; i<26; i++) {
            int count = 0;
            List<Integer> list = new ArrayList<>();
            for(int j=0; j<input_string.length(); j++) {
                if((char)(i+97) == input_string.charAt(j)) {
                    count++;
                    list.add(j); // 알파벳의 위치 인덱스 저장
                }
            }

            // 하나의 알파벳이 2회 이상 나오고 2개 이상의 부분으로 나뉘어져 있는지 확인
            int group = 1; // 알파벳 덩어리의 갯수
            if(count >= 2) {
                for(int k=1; k<list.size(); k++) {
                    if(list.get(k) != list.get(k-1) + 1) {
                        group++;
                    }
                }
                if(group >= 2) {
                    answer += (char)(i+97);
                }
            } else {
                continue; // 알파벳이 2회 이상 나오지 않으면 다음 알파벳 탐색
            }
        }

        return (answer.equals("")) ? "N" : answer;
    }
}
