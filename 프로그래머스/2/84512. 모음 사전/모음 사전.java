import java.util.*;

/**
모든 경우의 수를 다 돌 수 밖에 없는 구조
따라서 if(s.equals(word)) 일 때 answer를 출력하고 싶었지만 안됨

모든 경우의 수를 list에 저장하고
전체 for문 돌리면서 내가 찾는 단어가 나오면 순서 리턴
*/

class Solution {
    
    static String[] alphabet = { "A", "E", "I", "O", "U" };
    static String word;
    static List<String> list = new ArrayList<>();
    static int answer = 0;
    
    public int solution(String word) {
        this.word = word;
        
        dfs("", 0);
        
        for(String s : list) {
            if(s.equals(word)) {
                answer = list.indexOf(s); // list에 ""도 포함되어 있으므로 인덱스 순서 맞음
            }
        }
        
        return answer;
    }
    
    static public void dfs(String s, int depth) {
        //System.out.println(answer + "번째 단어 " + s);
        list.add(s);
        
        if(depth == 5) {
            return;
        }
        
        for(int i=0; i<5; i++) {
            answer++;
            dfs(s+alphabet[i], depth+1);
        }
    }
}