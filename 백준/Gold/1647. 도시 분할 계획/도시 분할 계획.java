import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

/**
 * <BJ_1647_도시분할계획>
 * 최소 신장 트리(MST)와 크루스칼 알고리즘(최소 신장 트리를 구하기 위한 알고리즘)
 * 
 * 먼저 마을을 나누는 모든 경우의 수를 확인할 수 없다.
 * 정점 N의 범위가 10만까지이므로 모든 조합을 확인하면 시간초과가 날 것이다.
 * 
 * N개의 정점이 모두 연결되어 있고 무향 그래프이며 가중치의 최소 합을 구하는 문제이므로 최소 신장 트리와 크루스칼 알고리즘을 이용한다.
 * 1. 가중치를 기준으로 오름차순 정렬한다.
 * 2. 가중치가 낮은 간선부터 선택하며 두 노드 간에 union 함수 실행
 * 		- 가중치가 낮은 간선부터 연결하므로 자연스럽게 가중치의 최소 합을 구할 수 있다.
 * 		- 이 때, 싸이클이 발생하면 취소하고 다음 간선 선택
 * 		- 싸이클이 발생한 것은 두 노드의 대표 노드(루트 노드)가 같을 때를 말한다.
 * 3. 간선이 N-1개가 될 때까지 2번을 반복한다.
 * 4. 해당 문제에서는 두 도시로 분할하므로 최종 가중치의 합에서 현재까지 가중치 중 가장 큰 값을 빼주면 된다.
 * 
 * @author YooSejin
 *
 */

public class Main {
	
	static int V; // 집의 개수 (정점의 개수)
	static int E; // 길의 개수 (간선의 개수)
	static List<Edge> list;
	static int[] parent;
	
	static class Edge implements Comparable<Edge> {
		int from;
		int to;
		int cost;
		
		Edge(int from, int to, int cost) {
			this.from = from;
			this.to = to;
			this.cost = cost;
		}
		
		@Override
		public int compareTo(Edge o) {
			return this.cost - o.cost; // 가중치를 기준으로 오름차순 정렬
		}
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        V = Integer.parseInt(st.nextToken());
        E = Integer.parseInt(st.nextToken());
		
        list = new ArrayList<>();
        parent = new int[V+1];
        
        for(int i=0; i<E; i++) {
        	st = new StringTokenizer(br.readLine());
        	int from = Integer.parseInt(st.nextToken());
        	int to = Integer.parseInt(st.nextToken());
        	int cost = Integer.parseInt(st.nextToken());
        	list.add(new Edge(from, to, cost));
        }
		
        Collections.sort(list);
        
        // parent 배열 초기화
        for(int i=0; i<=V; i++) {
        	parent[i] = i;
        }
        
        int sum = 0;
        int max = 0;
        int edgeCnt = 0; // 연결된 간선 개수
        
        // 가중치가 낮은 간선부터 연결
        for(Edge edge : list) {
        	if(union(edge.from, edge.to)) { // 싸이클이 발생하지 않는 노드만 연결
        		sum += edge.cost;
        		max = Math.max(max, edge.cost);
        		edgeCnt++;
        	}
        	
        	if(edgeCnt == V-1) {
        		break;
        	}
        }
		
        // 두 도시를 분할한 후 최소 가중치의 합이므로 전체 가중치의 합에서 가장 큰 가중치의 값을 빼주면 된다.
        // 가장 큰 가중치의 합을 빼면 해당 간선은 끊어진 것.
        System.out.println(sum - max);
	}
	
	// 두 노드를 연결하는 함수
	static boolean union(int x, int y) {
		x = find(x);
		y = find(y);
		
		if(x == y) return false; // 두 노드의 루트 노드가 같으면 싸이클이 발생한 것
		
		if(x < y) parent[y] = x;
		else parent[x] = y;
		return true;
	}
	
	// 대표 노드(루트 노드)를 찾는 함수
	static int find(int x) {
		if(parent[x] == x) return x;
		
		return parent[x] = find(parent[x]);
	}

}
