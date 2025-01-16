import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

/**
 * <BJ_1916_최소비용구하기>
 * 최단경로 문제이고, 버스 비용이 모두 양의 정수이기에 다익스트라 알고리즘 문제이다.
 * 시간복잡도를 줄이기 위해 우선순위 큐를 사용한다.
 * 다익스트라 시간복잡도 : O(V^2 + E)
 * 우선순위 큐를 사용했을 때 시간복잡도 : O(ElogV)
 * 
 * <풀이 방법>
 * 현재 노드에서 갈 수 있는 모든 간선을 확인해 최단 경로를 갱신하고
 * 최단 경로로 갱신하면 우선순위 큐(힙, 여기서는 최소 힙)에 (해당 정점 번호, 누적된 최소 거리)를 넣어준다.
 * 우선순위 큐를 누적된 최소 비용 기준으로 오름차순 정렬해주었기 때문에 최소 힙으로 자동 정렬된다.
 * 우선순위 큐가 빌 때까지 즉, 모든 가능한 경로를 탐색한 후
 * 최소로 갱신되어 있는 일차원 배열에서 도착 정점의 인덱스에 해당하는 값을 확인하면 된다.
 * 
 * 이 때, 시간초과 안나려면 가지치기를 해주어야 한다.
 * 예를 들어 큐에서 꺼낸 현재 노드가 5번이고 누적된 비용이 10이라면
 * 5번 노드의 최소 비용인 4와 비교했을 때, 뒤에 어느 경로로 가도 최소 비용을 얻을 수 없다.
 * 따라서 가지치기해 시간을 줄일 수 있다.
 * 
 * @author YooSejin
 *
 */

public class Main {
	
	static int V; // 도시의 개수(정점)
	static int E; // 버스의 개수(간선)
	static List<ArrayList<Node>> graph;
	static int[] distance;
	static int start;
	static int destination;
	
	static class Node implements Comparable<Node> {
		int num;
		int cost;
		
		Node(int num, int cost) {
			this.num = num;
			this.cost = cost;
		}
		
		@Override
		public int compareTo(Node o) {
			return this.cost - o.cost; // 우선순위 큐에서 누적된 비용을 오름차순으로 정렬
		}
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		
		V = Integer.parseInt(br.readLine());
		E = Integer.parseInt(br.readLine());
		
		// 초기화 작업
		graph = new ArrayList<>();
		for(int i=0; i<=V; i++) {
			graph.add(new ArrayList<>());
		}
		
		distance = new int[V+1];
		Arrays.fill(distance, Integer.MAX_VALUE);
		
		// 버스 정보 저장
		for(int i=0; i<E; i++) {
			st = new StringTokenizer(br.readLine());
			int start = Integer.parseInt(st.nextToken());
			int end = Integer.parseInt(st.nextToken());
			int cost = Integer.parseInt(st.nextToken());
			
			graph.get(start).add(new Node(end, cost));
		}
		
		st = new StringTokenizer(br.readLine());
		start = Integer.parseInt(st.nextToken());
		destination = Integer.parseInt(st.nextToken());
		
		distance[start] = 0; // 시작 노드에서 시작 노드로 가는 비용은 0
		
		// 우선순위 큐에 현재 방문한 노드와 현재 방문한 노드까지의 누적된 비용을 저장한다.
		PriorityQueue<Node> pq = new PriorityQueue<>();
		pq.add(new Node(start, 0));
		
		while(!pq.isEmpty()) {
			Node current = pq.poll(); // 현재 노드
			int now = current.num; // 현재 노드 번호
			int nowCost = current.cost; // 현재 방문한 노드까지 누적된 비용
			
			// 현재 노드까지 누적된 비용이 distance 배열에 최소로 갱신된 비용보다 크다면 뒤에 어느 경로가 와도 최소 비용일 수 없다.
			// 가지치기 (시간초과 안나려면 필수)
			if(nowCost > distance[now]) continue;
			
			// 현재 노드에서 갈 수 있는 모든 노드를 탐색
			for(int i=0; i<graph.get(now).size(); i++) {
				Node dest = graph.get(now).get(i); // 갈 수 있는 노드
				
				// 도착 노드까지 갈 수 있는 비용을 최소로 갱신
				// (현재까지 누적된 비용 + 도착 노드까지 가는데 드는 비용)과 도착 노드에 저장된 최소 비용을 비교
				if(distance[dest.num] > nowCost + dest.cost) {
					distance[dest.num] = nowCost + dest.cost;
					
					// 도착 노드까지의 최소 비용이 갱신되면 우선순위 큐에 삽입
					pq.add(new Node(dest.num, distance[dest.num])); // 우선순위 큐에 넣으면 비용 기준 최소 힙으로 정렬됨
				} else {
					continue;
				}
			}
		}
		
		// distance[] 확인
//		for(int i=0; i<=V; i++) {
//			System.out.print(distance[i] + " ");
//		}
		
		System.out.println(distance[destination]);
	}

}
