/**
처음 실수!
begin을 전역변수로 사용해서 단어 변환이 가능하면 begin = words[i]; 로 바꿔줬는데
begin을 바꿔버리면 이후에 target과 비교가 불가능하다.
*/

class Solution {
    
    static String begin;
    static String target;
    static String[] words;
    static boolean[] visited;
    static int count = Integer.MAX_VALUE; // 변환 횟수
    
    public int solution(String begin, String target, String[] words) {
        this.begin = begin;
        this.target = target;
        this.words = words;
        
        visited = new boolean[words.length];
        
        dfs(begin, 0); // count 전달. 아직 0번 바뀜.
        
        return (count == Integer.MAX_VALUE) ? 0 : count;
    }
    
    static public void dfs(String currentWord, int cnt) {
        if(currentWord.equals(target)) {
            count = Math.min(cnt, count);
            return;
        }
        
        for(int i=0; i<words.length; i++) {
            if(visited[i]) continue;
            
            // 단어 비교 : 다른 알파벳이 하나여야 변환 가능
            int differentCnt = 0; // 다른 알파벳의 수
            for(int j=0; j<currentWord.length(); j++) {
                if(currentWord.charAt(j) != words[i].charAt(j)) {
                    differentCnt++;
                }
            }
            
            if(differentCnt == 1) {
                visited[i] = true;
                dfs(words[i], cnt+1);
                visited[i] = false;
            }
        }
    }
}