class Solution {
    public int solution(String skill, String[] skill_trees) {
//         int[] order = new int[26]; // 스킬별 배워야하는 순서 저장
        
//         int num = 1;
//         for(int i=0; i<skill.length(); i++) {
//             char c = skill.charAt(i);
//             order[c - 'A'] = num++;
//         }
        
//         int answer = 0;
//         int skillCnt = skill.length() + 1;
        
//         for(String skillTree : skill_trees) {
//             num = 1; // 초기화
//             boolean isRight = true; // 초기화
            
//             for(int i=0; i<skillTree.length(); i++) {
//                 char c = skillTree.charAt(i);
                
//                 // order[]의 값이 0인 경우는 선행 스킬과 상관없음
//                 if(order[c - 'A'] == 0) continue;
                
//                 // 앞의 스킬을 배우지 않은 경우 카운트 x
//                 if(order[c - 'A'] != num) {
//                     isRight = false;
//                     break;
//                 } else if(order[c - 'A'] == num) {
//                     num++;
//                 }
                
//                 // 이미 선행 스킬을 다 만족하면 뒤에는 확인할 필요 없음
//                 if(num == skillCnt) break;
//             }
            
//             if(isRight) answer++;
//         }
        
//         return answer;
        
        
        // 방법2. replaceAll() 사용
        int answer = 0;
        
        for(String skillTree : skill_trees) {
            boolean isPossible = true;
            String s = skillTree.replaceAll("[^" + skill + "]", ""); // skill을 제외한 문자를 모두 ""로 치환
            
            for(int i=0; i<s.length(); i++) {
                if(s.charAt(i) != skill.charAt(i)) {
                    isPossible = false;
                    break;
                }
            }
            
            if(isPossible) answer++;
        }
        
        return answer;
    }
}