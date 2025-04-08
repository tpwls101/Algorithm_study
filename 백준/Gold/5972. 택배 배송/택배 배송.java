import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

/**
 * <BJ_5972_택배배송>
 * 우선순위 큐를 쓰는 이유는 비용이 적은 순대로 큐에 넣어 최소 비용이 드는 정점을 빠르게 찾기 위해서다.
 * 
 * 1. 그래프 초기화 및 시작점과 도착점, 비용 저장
 * 2. 최소 비용을 갱신할 1차원 배열 사용
 * 		- 최솟값으로 갱신하기 위해 최댓값으로 초기화
 * 		- 단, 시작점에서 시작점으로의 최소 비용은 0
 * 3. 시작점에서 갈 수 있는 노드를 돌리며 현재 누적된 비용에서 가는데 드는 비용을 더한 값이 최소 비용보다 더 작으면 새롭게 갱신
 * 		- 최소 비용을 갱신하면 우선순위 큐에 추가
 * 4. 우선순위 큐에서 꺼낸 노드의 누적 비용이 최소 비용보다 크다면 어차피 최소 비용으로 갱신할 수 없음 -> 따라서 가지치기(continue)
 * 		- 이 부분은 이 문제에서 안해도 시간초과 걸리지 않음 (선택!!)
 * 
 * @author YooSejin
 *
 */

public class Main {
	
	static int V; // 정점(헛간)
	static int E; // 간선(길)
	static ArrayList<ArrayList<Node>> graph;
	static int[] distance; // 최소비용을 저장할 배열
	
	static class Node implements Comparable<Node> {
		int num; // 갈 수 있는 노드 번호
		int cost; // 누적된 비용
		
		Node(int num, int cost) {
			this.num = num;
			this.cost = cost;
		}
		
		@Override
		public int compareTo(Node o) {
			return this.cost - o.cost; // 비용이 적은 순대로 큐에 삽입 (오름차순)
		}
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		V = Integer.parseInt(st.nextToken());
		E = Integer.parseInt(st.nextToken());
		
		// 그래프 초기화 작업
		graph = new ArrayList<>();
		for(int i=0; i<=V; i++) {
			graph.add(new ArrayList<>());
		}
		
		// 입력값 받기
		for(int i=0; i<E; i++) {
			st = new StringTokenizer(br.readLine());
			int start = Integer.parseInt(st.nextToken());
			int end = Integer.parseInt(st.nextToken());
			int cost = Integer.parseInt(st.nextToken());
			
			// 양방향 그래프
			graph.get(start).add(new Node(end, cost));
			graph.get(end).add(new Node(start, cost));
		}
		
		distance = new int[V+1];
		Arrays.fill(distance, Integer.MAX_VALUE); // 최단 거리로 갱신하기 위해 최댓값으로 초기화
		distance[1] = 0; // 출발점에서 출발점까지의 최단 거리는 0
		
		PriorityQueue<Node> pq = new PriorityQueue<>();
		pq.add(new Node(1, 0));
		
		while(!pq.isEmpty()) {
			Node current = pq.poll();
			int node = current.num;
			int cost = current.cost;
			
			// 가지치기 (이 문제에서는 선택사항. 안해도 시간초과 걸리지 않는다.)
			// 큐에서 뽑아 가져온 현재까지 누적된 비용이 최단 비용보다 크다면 더 진행해도 최소 비용으로 갱신할 수 없다.
			if(cost > distance[node]) continue;
			
			for(int i=0; i<graph.get(node).size(); i++) {
				Node next = graph.get(node).get(i); // 현재 노드에서 갈 수 있는 다음 노드
				
				if(cost + next.cost < distance[next.num]) {
					distance[next.num] = cost + next.cost; // 최단 거리 갱신
					pq.add(new Node(next.num, distance[next.num]));
				}
			}
		}
		
		System.out.println(distance[V]);
	}

}
