/**
순열로 가능한 모든 경우의 수 탐색
각 경우마다 실행해서 탐험할 수 있는 던전 수 구하기
각 경우마다 최댓값으로 갱신
*/

class Solution {
    
    static int[] order; // 던전에 가는 순서를 저장할 배열
    static boolean[] visited;
    static int k;
    static int[][] dungeons;
    static int answer = 0;
    
    public int solution(int k, int[][] dungeons) {
        this.k = k;
        this.dungeons = dungeons;
        
        order = new int[dungeons.length];
        visited = new boolean[dungeons.length];
        
        perm(0, k);
        
        return answer;
    }
    
    // current : 현재 피로도
    static public void perm(int cnt, int current) {
        if(cnt == dungeons.length) {
            // order 순서에 따라 던전 탐험
            int count = 0;
            for(int i=0; i<order.length; i++) {
                int dungeonNum = order[i]; // 현재 던전 번호
                if(current >= dungeons[dungeonNum][0]) {
                    current -= dungeons[dungeonNum][1];
                    count++;
                } else {
                    break;
                }
            }
            answer = Math.max(answer, count);
            current = k; // 현재 피로도 초기화
            return;
        }
        
        for(int i=0; i<dungeons.length; i++) {
            if(visited[i]) continue;
            order[cnt] = i;
            visited[i] = true;
            perm(cnt+1, current);
            visited[i] = false;
        }
    }
}