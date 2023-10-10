import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * <D4_7465_창용마을무리의개수>
 * 그래프 구현 방법 : 인접리스트 (ArrayList 안에 ArrayList가 있는 구조 사용했음)
 * 
 * ArrayList에 정점 번호를 넣고 각 정점과 인접한 정점들을 인접리스트로 구현하였다.
 * graph를 1부터 6까지 정점 번호 순서대로 차례로 돌면서 방문하지 않았으면 방문처리하고
 * 인접한 정점들 중 방문하지 않은 정점을 큐에 넣어준다.
 * 큐가 비면 한 무리가 완성된다.
 * 
 * @author 유세진
 *
 */

public class Solution {
	
	static int N; // 창용 마을에 사는 사람의 수
	static int M; // 서로를 알고 있는 사람의 관계 수
	static List<ArrayList<Integer>> graph; // 인접리스트
	static boolean[] visited; // 방문배열
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int T = Integer.parseInt(st.nextToken()); // 테스트케이스 수
		
		for(int tc=1; tc<=T; tc++) {
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			
			visited = new boolean[N+1]; // 0번 인덱스 사용x
			
			// 그래프 구현
			graph = new ArrayList<>();
			for(int i=0; i<=N; i++) { // 0번 인덱스 사용x
				graph.add(new ArrayList<>());
			}
			
			for(int i=0; i<M; i++) {
				st = new StringTokenizer(br.readLine());
				int from = Integer.parseInt(st.nextToken());
				int to = Integer.parseInt(st.nextToken());
				
				graph.get(from).add(to);
				graph.get(to).add(from);				
			}
			
			// 그래프 확인
//			for(int i=0; i<graph.size(); i++) {
//				System.out.println(graph.get(i));
//			}
			
			int count = 0; // 창용 마을 무리의 개수
			Queue<Integer> queue = new ArrayDeque<>();
			
			for(int i=1; i<=N; i++) {
				// 아직 방문하지 않은 정점이면 큐에 삽입하고 방문처리
				if(!visited[i]) {
					queue.offer(i);
					visited[i] = true;
					
					while(!queue.isEmpty()) {
						int current = queue.poll(); // 현재 정점
						for(int j=0; j<graph.get(current).size(); j++) {
							int vertex = graph.get(current).get(j); // 현재 정점과 인접한 정점
							// 아직 인접정점을 방문하지 않았으면 큐에 삽입
							if(!visited[vertex]) {
								queue.offer(vertex);
								visited[vertex] = true;
							}
						}
					}
					
					count++; // 창용 마을의 무리 개수 증가
				}
			}
			
			System.out.printf("#%d %d\n", tc, count);
		}
		
	}

}