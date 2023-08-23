import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * <D4_3124_최소스패닝트리>
 * 간선정보(from, to, weight)를 가중치에 따라 오름차순 정렬하여
 * 가중치가 낮은 간선부터 트리를 만들어 가중치의 합을 구하면 가중치의 최소 합이 보장된다.
 * 
 * 간선정보를 저장할 클래스 Edge를 만들고 from과 to 정점을 합치면서(union) 트리를 만들어나간다.
 * 사이클이 존재하면 다음 간선을 불러오고 이를 간선의 개수가 V-1개가 될 때까지 반복하며 가중치를 더해준다.
 * 
 * 주의 : 정점의 개수는 최대 100,000개이고 가중치의 절대값이 1,000,000을 넘지 않으므로 가중치의 합은 long 타입이 되어야 한다!
 * 
 * 메모리 : 117,772 kb
 * 실행시간 : 1,869 ms
 * 
 * @author 유세진
 *
 */

public class Solution {
	
	// 간선 정보를 저장할 클래스
	static class Edge implements Comparable<Edge> {
		int from;
		int to;
		int weight;
		
		public Edge(int from, int to, int weight) {
			this.from = from;
			this.to = to;
			this.weight = weight;
		}
		
		// 간선을 가중치에 따라 오름차순 정렬
		public int compareTo(Edge o) {
			return this.weight - o.weight; // 양수:오름차순, 음수:내림차순
		}
	}
	
	static int V; // 정점의 개수
	static int E; // 간선의 개수
	static Edge[] edgeList; // 간선정보를 저장하는 리스트
	static int[] parents; // 정점의 부모인덱스를 저장해주는 배열
	
	// V개의 정점들을 각자 집합으로 만들어주는 메서드
	private static void make() {
		parents = new int[V+1]; // 0번 인덱스는 비어있으므로 크기는 V+1
		for(int i=1; i<=V; i++) { // i는 1부터 V까지 -> 정점이 1부터 시작하기 때문
			parents[i] = i; // i번째 정점의 부모는 i -> 자기자신이 대표자
		}
	}
	
	// 두 정점 합치기 -> 트리 만들기
	private static boolean union(int a, int b) {
		int aRoot = find(a); // a원소를 포함한 집합의 대표자 찾기
		int bRoot = find(b); // b원소를 포함한 집합의 대표자 찾기
		
		if(aRoot == bRoot) return false; // 대표자가 같다면 이미 같은 집합 (여기서는 사이클 발생과 같은 의미)
		parents[bRoot] = aRoot; // b원소를 포함한 집합의 대표자를 a원소를 포함한 집합의 대표자와 같게 만들어줌 -> 하나의 집합 (트리 증가)
		return true;
	}
	
	// 해당 원소의 대표자를 찾는 메서드
	private static int find(int x) {
		if(parents[x] == x) return x; // x의 부모인덱스가 자기 자신이면 x가 대표자
		else return parents[x] = find(parents[x]); // 경로 압축 (-> 시간 단축)
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int T = Integer.parseInt(st.nextToken());
		
		for(int test_case=1; test_case<=T; test_case++) {
			st = new StringTokenizer(br.readLine());
			
			V = Integer.parseInt(st.nextToken()); // 정점의 개수 (1 ~ 100,000)
			E = Integer.parseInt(st.nextToken()); // 간선의 개수 (1 ~ 200,000)
			
			edgeList = new Edge[E]; // 생성 및 초기화
			
			for(int i=0; i<E; i++) {
				st = new StringTokenizer(br.readLine());
				
				int A = Integer.parseInt(st.nextToken()); // from
				int B = Integer.parseInt(st.nextToken()); // to
				int C = Integer.parseInt(st.nextToken()); // 가중치 (음수도 가능, 절대값이 1,000,000을 넘지 않음)
				
				edgeList[i] = new Edge(A, B, C);
			}
			
			Arrays.sort(edgeList); // 간선정보를 가중치에 따라 오름차순 정렬 -> 앞에서부터 그리디적으로 가중치를 더해도 최소가 보장됨!

			// V개의 정점들을 각자 집합으로 만들어주기
			make();
			
			int count = 0; // 간선의 개수 카운트
			long answer = 0; // 가중치의 최소 합
			
			// 가중치가 낮은 간선부터 선택하면서 트리 증가
			for(Edge edge : edgeList) {
				boolean flag = union(edge.from, edge.to); // 두 정점을 합치면 true 반환, 사이클이 발생해서 합치지 못하면 false 반환
				if(flag) {
					count++; // 두 정점을 연결하면 간선 개수 증가
					answer += edge.weight; // 두 정점을 연결하면 가중치 더하기
					if(count == V-1) { // 간선의 개수가 V-1이면 모든 정점이 연결된 것! (신장 트리)
						break;
					}
				}
			}
			
			System.out.printf("#%d %d\n", test_case, answer);
			
		}
		
	}

}