/**
그래프가 나오면 자꾸 이중for문 돌려서 arr[][]==1인지 확인하려고 하는데
행렬이 아니라 그림을 잘 보고 생각해보자.
로직에 집중할 것!

모든 컴퓨터를 방문하면서 확인하니 컴퓨터 개수만큼 for문을 한번 돌리면 되고
방문했으면 방문을 true로 설정
그 다음 2차원 배열에서 연결되어 있는(값이 1인) 컴퓨터를 찾는다.
그 컴퓨터가 방문하지 않은 컴퓨터인 경우에만 다시 dfs 탐색
*/

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
