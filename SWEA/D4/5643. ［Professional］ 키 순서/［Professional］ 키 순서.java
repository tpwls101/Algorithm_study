import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * <D4_5643_키순서>
 * 
 * N이 500까지 -> 500^3 = 1억2500만 -> 플로이드워샬 (시간복잡도 : O(n^3)) 쓰자!
 * 
 * dfs, bfs로도 가능! -> 이걸로도 풀어보자!
 * 
 * @author 유세진
 *
 */

public class Solution {
	
	public static int N; // 학생 수 (정점의 개수)
	public static int M; // 두 학생의 키를 비교한 횟수 (간선의 개수)
	public static int[][] graph;
	public static boolean[] visited;
	public static int answer;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int T = Integer.parseInt(br.readLine()); // 테스트케이스 수
		
		for(int tc=1; tc<=T; tc++) {
			
			answer = 0;
			
			N = Integer.parseInt(br.readLine());
			M = Integer.parseInt(br.readLine());
			
			graph = new int[N+1][N+1]; // 0번 인덱스 사용x
			visited = new boolean[N+1]; // 0번 인덱스 사용x
			
			// 입력값 받아서 인접행렬 만들기
			for(int i=0; i<M; i++) {
				StringTokenizer st = new StringTokenizer(br.readLine());
				
				// a -> b
				int a = Integer.parseInt(st.nextToken());
				int b = Integer.parseInt(st.nextToken());
				
				graph[a][b] = 1;
			}
			
			// 그래프 확인
//			for(int i=0; i<=N; i++) {
//				System.out.println(Arrays.toString(graph[i]));
//			}
			
			// 플로이드 워샬
			for(int i=1; i<=N; i++) {
				for(int j=1; j<=N; j++) {
					// 플로이드 워샬에서는 직행할 때와 경유할 때의 최소값을 비교해줘야 하므로 0을 ∞로 바꿔줘야 함!!
					if(i != j && graph[i][j] == 0) {
						//graph[i][j] = Integer.MAX_VALUE / 2; // 오버플로우 방지용 나누기 2
						graph[i][j] = 10000; // N이 500까지니까 어차피 커봤자 최대 500! -> 500 이상인 수를 max_value로 넣어주면 된다
					}
				}
			}
			
			// 경출도 (k,i,j)
			for(int k=1; k<=N; k++) { // 경유지
				for(int i=1; i<=N; i++) {
					// 출발지는 경유지와 같으면 안됨
					if(i != k) {
						for(int j=1; j<=N; j++) { // 출발지
							// 도착지는 경유지와 출발지와 같으면 안됨
							if(j != k && j != i) {
								// i에서 j로 직행할 때와 k를 경유해서 갈 때의 최소값을 비교해서 저장 (모든 쌍 최단경로)
								graph[i][j] = Math.min(graph[i][j], graph[i][k] + graph[k][j]);
							}
						}
					}
				}
			}
			
			// 그래프 확인
//			for(int i=0; i<=N; i++) {
//				System.out.println(Arrays.toString(graph[i]));
//			}
			
			// 자신의 키가 몇 번째인지 알 수 있는 학생들은 모두 몇 명?
			for(int i=1; i<=N; i++) {
				int count = 0;
				
				for(int j=1; j<=N; j++) {
					// 0이나 ∞가 아닌 값이 있으면 갈 수 있다는 뜻! (키 비교가 가능하다는 뜻)
					// 자기 자신보다 키가 큰 학생 수 확인
					//if(graph[i][j] != Integer.MAX_VALUE/2 && graph[i][j] != 0) {
					if(graph[i][j] != 10000 && graph[i][j] != 0) {
						count++;
					}
				}
				//System.out.println(i + "학생 " + count);
				
				for(int j=1; j<=N; j++) {
					// 자기 자신보다 키가 작은 학생 수 확인
					if(graph[j][i] < 10000 && graph[j][i] != 0) {
						count++;
					}
				}
				//System.out.println(i + "학생 " + count);
				
				if(count == N-1) { // 자기 자신보다 키가 큰 학생과 키가 작은 학생 수의 합이 N-1이면 자신의 키가 몇 번째인지 알 수 있다!
					answer++;
				}
			}
			
			System.out.println("#" + tc + " " + answer);
		}
		
	}

}