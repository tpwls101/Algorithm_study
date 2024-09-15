import java.util.*;

/**
깃헙에 DFS 버전도 있는데 BFS 훨 쉽다ㅜㅜ
*/

class Solution {
    
    static String target;
    static String[] words;
    static boolean[] visited;
    
    static class Word {
        String word;
        int count; // 이 단어로 바뀌는데 몇 번 걸렸는지
        
        Word(String word, int count) {
            this.word = word;
            this.count = count;
        }
    }
    
    public int solution(String begin, String target, String[] words) {
        this.target = target;
        this.words = words;
        visited = new boolean[words.length];
        
        return bfs(new Word(begin, 0));
    }
    
    static public int bfs(Word w) {
        Queue<Word> queue = new ArrayDeque<>();
        queue.add(w);
        
        while(!queue.isEmpty()) {
            Word current = queue.poll();
            if(current.word.equals(target)) {
                return current.count;
            }
            
            // 현재 단어와 비교해서 한글자만 차이나는 단어 큐에 넣기
            for(int i=0; i<words.length; i++) {
                if(visited[i]) continue;
                
                int differentCnt = 0; // 다른 알파벳의 수
                for(int j=0; j<words[i].length(); j++) {
                    if(current.word.charAt(j) != words[i].charAt(j)) {
                        differentCnt++;
                    }
                }
                
                if(differentCnt == 1) {
                    queue.add(new Word(words[i], current.count+1));
                    visited[i] = true;
                }
            }
        }
        
        return 0;
    }
}