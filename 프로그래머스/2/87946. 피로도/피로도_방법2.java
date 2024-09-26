/**
dfs로 완전탐색
max값 비교하는 위치가 헷갈린다.
*/

class Solution {
    
    static int k;
    static int[][] dungeons;
    static boolean[] visited;
    static int answer = 0;
    
    public int solution(int k, int[][] dungeons) {
        this.k = k;
        this.dungeons = dungeons;
        
        visited = new boolean[dungeons.length];
        
        dfs(0, k); // depth랑 현재 피로도
        
        return answer;
    }
    
    // current : 현재 피로도
    static public void dfs(int depth, int current) {
        for(int i=0; i<dungeons.length; i++) {
            if(visited[i]) continue;
            if(current >= dungeons[i][0]) {
                visited[i] = true;
                dfs(depth+1, current-dungeons[i][1]);
                visited[i] = false;
            }
        }
        answer = Math.max(answer, depth);
    }
}
