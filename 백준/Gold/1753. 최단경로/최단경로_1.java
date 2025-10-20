import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

/**
 * <BJ_1753_최단경로>
 * 시작점에서 다른 모든 정점까지의 최단 경로를 구하고, 가중치는 양의 정수이므로 다익스트라 문제.
 * 우선순위 큐를 사용했다.
 * 
 * 1. 시작점을 큐에 넣음
 * 2. 큐에서 정점을 하나 꺼내 갈 수 있는 노드 탐색
 * 3. 다음 노드의 최단 경로와 현재 노드까지의 누적 경로 + 가중치를 비교해 최소값으로 최단 경로 갱신
 * 4. 최단경로를 갱신하면 큐에 노드 삽입
 * 5. 우선순위 큐가 빌 때까지 반복
 * 6. 그러면 1차원 배열에 해당 노드까지의 최단 경로가 최종 저장됨
 * 
 * 이 문제의 시간 제한은 1초.
 * 0.5초일 경우에는 가지치기를 안하면 시간초과가 나지만 1초일 때는 시간초과가 나지 않았다.
 * 이 문제에서는 가지치기 해도 876ms -> 860ms 로 얼마 차이가 나지 않는다.
 * 
 * @author YooSejin
 *
 */

public class Main {
	
	static int V;
	static int E;
	static int start;
	static List<ArrayList<Node>> graph;
	static int[] distance;
	
	static class Node implements Comparable<Node> {
		int num;
		int cost;
		
		Node(int num, int cost) {
			this.num = num;
			this.cost = cost;
		}
		
		@Override
		public int compareTo(Node o) {
			return this.cost - o.cost; // 누적 경로가 더 적은 순으로 정렬(오름차순)
		}
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		V = Integer.parseInt(st.nextToken());
		E = Integer.parseInt(st.nextToken());
		
		// 초기화
		graph = new ArrayList<>();
		for(int i=0; i<=V; i++) {
			graph.add(new ArrayList<>());
		}
		
		distance = new int[V+1];
		Arrays.fill(distance, Integer.MAX_VALUE);
		
		start = Integer.parseInt(br.readLine());
		distance[start] = 0;
		
		// 간선 정보 입력받기
		for(int i=0; i<E; i++) {
			st = new StringTokenizer(br.readLine());
			int u = Integer.parseInt(st.nextToken());
			int v = Integer.parseInt(st.nextToken());
			int w = Integer.parseInt(st.nextToken());
			
			graph.get(u).add(new Node(v, w));
		}
		
		// 우선순위 큐에 (정점 번호)와 (현재까지 누적된 거리)를 담는다.
		// 누적된 경로가 더 적은 순으로 자동 정렬된다. -> 최소 힙
		PriorityQueue<Node> pq = new PriorityQueue<>();
		pq.add(new Node(start, 0));
		
		while(!pq.isEmpty()) {
			Node node = pq.poll(); // 현재 노드
			int now = node.num; // 현재 노드 번호
			int nowCost = node.cost; // 현재 노드까지 누적된 경로
			
			// 예를 들어 1-2-5 까지의 누적 경로는 5인데 1-3-5까지의 누적 경로가 8이라면
			// 그 뒤의 경로를 탐색해도 최단 경로일 수 없다.
			if(distance[now] < nowCost) continue;
			
			// 현재 노드에서 갈 수 있는 다음 노드 탐색
			for(Node next : graph.get(now)) {
				// 다음 노드의 최단 경로와 현재 노드까지의 누적 경로 + 가중치를 비교해 최소값으로 최단 경로를 갱신
				if(distance[next.num] > nowCost + next.cost) {
					distance[next.num] = nowCost + next.cost;
					
					// 최단경로가 갱신되면 우선순위 큐에 노드 삽입
					pq.add(new Node(next.num, distance[next.num]));
				}
			}
		}
		
		for(int i=1; i<=V; i++) {
			System.out.println(distance[i] == Integer.MAX_VALUE ? "INF" : distance[i]);
		}
	}

}
