import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * <BJ_1260_DFS와BFS>
 * 처음에는 연결리스트(LinkedList)를 활용하여 인접리스트를 구현했음 -> 정점 번호 정렬 어려움 -> ArrayList안에 ArrayList
 * 정렬을 통해 정점번호가 작은 것부터 탐색하며 번호를 출력하기 때문에 visited 배열을 통한 방문체크가 필요없음!
 * 
 * @author 유세진
 *
 */

public class Main {
	
	static int N; // 정점의 개수 (1~1000)
	static int M; // 간선의 개수 (1~10000)
	static int V; // 탐색을 시작할 정점의 번호
	static boolean[] visited; // 방문 체크 배열
	static ArrayList<ArrayList<Integer>> graph; // 그래프(인접리스트 - ArrayList 안에 ArrayList)
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		V = Integer.parseInt(st.nextToken());
		
		visited = new boolean[N+1];
		
		// 인접리스트(ArrayList 사용)로 그래프 구현
		graph = new ArrayList<>();
		for(int i=0; i<=N; i++) {
			graph.add(new ArrayList<>());
		}
		
		for(int i=0; i<M; i++) {
			st = new StringTokenizer(br.readLine());
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			
			// 양방향 그래프(무향그래프)이므로 1->2 넣어주면 2->1도 넣어주어야 함
			graph.get(from).add(to);
			graph.get(to).add(from);
		}
		
		// ArrayList 안에 있는 정점들 오름차순 정렬
		for(int i=0; i<graph.size(); i++) {
			 Collections.sort(graph.get(i));
		}
		
		dfs(V);
		
		System.out.println();
		visited = new boolean[N+1];	
		bfs(V);
		
	}
	
	// vertex : 방문하는 정점의 번호
	private static void dfs(int vertex) {
		System.out.print(vertex + " "); // 방문한 정점 번호 출력
		visited[vertex] = true; // 방문 처리
		
		// 방문한 정점과 인접한 정점들 중 아직 방문하지 않았으면 다시 dfs 호출
		for(int v : graph.get(vertex)) {
			if(!visited[v]) {
				dfs(v);
			}
		}
	}
	
	// vertex : 방문하는 정점의 번호
	private static void bfs(int vertex) {
		Queue<Integer> queue = new ArrayDeque<Integer>();
		queue.offer(vertex);
		visited[vertex] = true; // 시작 정점 찜콩
		
		while(!queue.isEmpty()) {
			int current = queue.poll(); // 현재 정점
			System.out.print(current + " "); // 방문한 정점 번호 출력
			
			// 현재 방문한 정점과 인접한 정점들 중 아직 방문하지 않았다면 큐에 삽입하고 찜콩
			for(int v : graph.get(current)) {
				if(!visited[v]) {
					queue.offer(v);
					visited[v] = true;
				}
			}
		}
	}

}