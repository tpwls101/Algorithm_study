import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    
    static int N; // 도시의 수
    static int[][] cost; // 도시 간의 이동 비용을 저장할 배열
    static int[] orders; // 도시 순회 순서
    static boolean[] visited; // 방문 체크 배열
     // 이동 비용의 합
    static int answer = Integer.MAX_VALUE; // 최소비용
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        N = Integer.parseInt(st.nextToken()); // 도시의 수
        
        cost = new int[N][N]; // 도시 간의 이동 비용을 저장할 배열
        
        // 최소비용 입력받기
        for(int i=0; i<N; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j=0; j<N; j++) {
                cost[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        
        // 완전탐색
        for(int i=0; i<N; i++) {
        	visited = new boolean[N];
        	visited[i] = true;
            dfs(i, 1, 0, i); // 시작 도시 전달
        }
        
        System.out.println(answer);
    }
    
    // start : 다음 방문할 도시
    // first : 시작 도시
    private static void dfs(int start, int count, int sum, int first) {
    	if(answer < sum) return;
    	
    	if(count == N) { // 모든 도시를 다 돌았다면
            if(cost[start][first] == 0) return; // 마지막 도시에서 첫 번째 도시로 못돌아가면 리턴
        	sum += cost[start][first];
        	answer = Math.min(sum, answer);
        	return;
        }
        
     
        
        for(int i=0; i<N; i++) {
            if(!visited[i] && cost[start][i] != 0) {
            	visited[start] = true;
            	//sum += cost[start][i];
            	dfs(i, count+1, sum+cost[start][i], first);
            	visited[start] = false;
            }
        }
        
        
    }

}