import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * <D6_1263_사람네트워크2>
 * 다익스트라 알고리즘 : 시작 정점에서 도착 정점까지의 최단 거리 구하기 (1차원 배열 사용)
 * 플로이드 워샬 알고리즘 : 모든 정점에서 모든 정점에 대한 최단 거리 구하기 (2차원 배열 사용)
 * 
 * 1번 노드 : 2,3,4,5 노드로 가는 최단 경로의 합 구하기
 * 2번 노드 : 1,3,4,5 노드로 가는 최단 경로의 합 구하기
 * 3번 노드 : 1,2,4,5 노드로 가는 최단 경로의 합 구하기
 * 4번 노드 : 1,2,3,5 노드로 가는 최단 경로의 합 구하기
 * 5번 노드 : 1,2,3,4 노드로 가는 최단 경로의 합 구하기
 * => 모든 사람에 대한 최단거리의 합을 구해야하므로 플로이드 워샬 알고리즘 사용!
 * 
 * 플로이드 워샬 알고리즘을 적용하고 나면 인접행렬에 i번째 노드에서 j번째 노드로 가는 모든 경로의 최소값이 저장되어 있다.
 * 
 * @author 유세진
 *
 */

public class Solution {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int T = Integer.parseInt(br.readLine()); // 테스트케이스 수
		
		for(int tc=1; tc<=T; tc++) {
			
			StringTokenizer st = new StringTokenizer(br.readLine());
			int N = Integer.parseInt(st.nextToken()); // 사람 수
			
			int[][] graph = new int[N][N];
			
			for(int i=0; i<N; i++) {
				for(int j=0; j<N; j++) {
					graph[i][j] = Integer.parseInt(st.nextToken()); // 입력받은 원본 그래프
				}
			}
			
			// 최소 비교를 해야하므로 자기 자신으로 가는 정점말고 나머지 중 값이 0인 것은 MAX_VALUE(∞)를 넣어준다!
			// 주의 : graph[i][k] + graph[k][j] 와 같이 더하는 과정에서 오버플로우가 날 수 있으므로 MAX_VALUE/2로 넣어준다 !!!
			for(int i=0; i<N; i++) {
				for(int j=0; j<N; j++) {
					if(i == j) continue;
					if(graph[i][j] == 0) {
						// 
						graph[i][j] = Integer.MAX_VALUE / 2;
					}
				}
			}
			
			// 플로이드워샬 알고리즘
			for(int k=0; k<N; k++) { // 경유지
				for(int i=0; i<N; i++) { // 출발지
					if(i == k) continue; // 출발지와 경유지가 같으면 안됨!
					for(int j=0; j<N; j++) { // 도착지
						if(j == k || j == i) continue; // 도착지가 경유지나 출발지와 같으면 안됨!
						// 경유하지 않고 갈 때의 최단거리와 k번 노드를 경유하고 갈 때의 최단거리 중 최소값을 인접행렬에 갱신!
						graph[i][j] = Math.min(graph[i][j], graph[i][k] + graph[k][j]);
					}
				}
			}
			
			// i에서 j까지의 최단거리를 저장한 인접행렬 adjMatrix
			// 한 행의 합이 CC(i) -> i번째 노드의 최단거리의 합!
			int min = Integer.MAX_VALUE;
			for(int i=0; i<N; i++) {
				int sum = 0;
				for(int j=0; j<N; j++) {
					sum += graph[i][j];
				}
				min = Math.min(sum, min);
			}
			
			System.out.printf("#%d %d\n", tc, min);
		}
		
	}

}