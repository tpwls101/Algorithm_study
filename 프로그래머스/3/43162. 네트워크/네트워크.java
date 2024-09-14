class Solution {
    
    static int n; // 컴퓨터의 개수
    static int[][] computers;
    static boolean[] visited;
    static int count = 0; // 네트워크 수
    
    public int solution(int n, int[][] computers) {
        this.n = n;
        this.computers = computers;
        
        visited = new boolean[n];
        
        // 모든 컴퓨터를 돌면서 아직 방문안한 컴퓨터만 골라 dfs로 탐색한다.
        for(int i=0; i<n; i++) {
            if(!visited[i]) {
                count++;
                dfs(i); // 컴퓨터 번호 전달
            }
        }
        
        return count;
    }
    
    static public void dfs(int node) {
        visited[node] = true;
        
        for(int i=0; i<n; i++) {
            if(computers[node][i] == 1 && !visited[i]) {
                dfs(i);
            }
        }
    }
}