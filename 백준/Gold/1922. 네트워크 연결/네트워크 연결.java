import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

/**
 * <BJ_1922_네트워크연결>
 * 문제에서 모든 컴퓨터를 연결해야 하고, 연결하는 비용을 최소로 하고자 한다.
 * 그리고 a-b가 연결되어 있고, b-c가 연결되어 있으면, a-c도 연결되어 있는 것이다.
 * 따라서 N개의 정점이 주어지면 N-1개만 연결해 비용을 최소로 하면 된다는 뜻이다.
 * 즉, 최소 스패닝 트리 문제이며 크루스칼 알고리즘을 사용하면 된다.
 * 추가로 입력값으로 '간선' 정보가 주어지고 있으니, 크루스칼임을 파악할 수 있다. (간선을 중심으로 그래프 표현)
 * 
 * <크루스칼 알고리즘>
 * 1. 비용을 기준으로 오름차순 정렬 -> 비용이 적은 정점부터 연결하면 결과적으로 최소 비용
 * 2. 비용이 적은 정점부터 하나씩 연결. 싸이클이 생기면 취소하고 다음 연결.
 * 3. N-1개의 간선이 연결될 때까지 반복
 * 
 * @author YooSejin
 *
 */

public class Main {
	
	static int N; // 컴퓨터의 수
	static int M; // 연결 가능한 선의 수
	static List<Edge> list; // 간선 정보 저장
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
			return this.cost - o.cost;
		}
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		
		N = Integer.parseInt(br.readLine());
		M = Integer.parseInt(br.readLine());
		
		list = new ArrayList<>();
		parent = new int[N+1];
		
		for(int i=1; i<=N; i++) {
			parent[i] = i;
		}
		
		for(int i=0; i<M; i++) {
			st = new StringTokenizer(br.readLine());
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			int cost = Integer.parseInt(st.nextToken());
			list.add(new Edge(from, to, cost));
		}
		
		Collections.sort(list);
		
		int cnt = 0;
		int min = 0;
		
		for(Edge edge : list) {
			if(union(edge.from, edge.to)) {
				cnt++;
				min += edge.cost;
			} else {
				continue;
			}
			
			if(cnt == N-1) break;
		}
		
		System.out.println(min);
	}
	
	static boolean union(int x, int y) {
		x = find(x);
		y = find(y);
		
		if(x == y) return false; // 이미 연결되어 있음
		
		if(x < y) parent[y] = x;
		else parent[x] = y;
		return true;
	}
	
	static int find(int x) {
		if(parent[x] == x) return x;
		
		return parent[x] = find(parent[x]);
	}

}
