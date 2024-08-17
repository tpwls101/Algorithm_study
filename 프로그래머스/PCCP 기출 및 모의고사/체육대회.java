/**
<모의고사1 2번>
순열
*/

class Solution {
    
    static int[] index;
    static boolean[] visited;
    static int[][] a; // 능력치
    static int answer = 0;

    public int solution(int[][] ability) {
        index = new int[ability[0].length];
        visited = new boolean[ability.length];
        a = ability;
        perm(0);
        return answer;
    }

    static void perm(int cnt) {
        // 하나의 순열이 완성되면 합 계산
        if(cnt == index.length) {
            int sum = 0;
            
            for(int i=0; i<index.length; i++) {
                sum += a[index[i]][i];
            }
            answer = Math.max(answer, sum);
            return;
        }
        
        for(int i=0; i<a.length; i++) {
            if(visited[i]) continue;
            index[cnt] = i;
            visited[i] = true;
            perm(cnt+1);
            visited[i] = false;
        }
    }
}
