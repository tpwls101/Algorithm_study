import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * <BJ_1976_여행가자>
 * 유니온 파인드 알고리즘
 * 경유해서라도 연결되어 있기만 하다면 여행 계획에 속한 도시들을 방문할 수 있다.
 * 하지만 하나라도 도시가 연결되어 있지 않다면 해당 도시를 여행할 수 없다.
 * 즉, 유니온 파인드 알고리즘을 통해 노드별로 그룹화하고 여행 순서대로 확인하면서 연결되어 있는지 확인하면 된다.
 * 
 * @author YooSejin
 *
 */

public class Main {
	
	static int N; // 도시의 수
	static int M; // 여행 계획에 속한 도시의 수
	static int[] parent; // i번 노드의 부모 노드를 저장(최종적으로는 루트 노드)
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		
		N = Integer.parseInt(br.readLine());
		M = Integer.parseInt(br.readLine());
		
		parent = new int[N+1];
		
		// parent 배열 초기화
		for(int i=0; i<=N; i++) {
			parent[i] = i;
		}
		
		for(int i=1; i<=N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=1; j<=N; j++) {
				int connect = Integer.parseInt(st.nextToken());
				if(connect == 1) {
					union(i, j);
				}
			}
		}
		
		int[] city = new int[M]; // 여행 계획 도시
		st = new StringTokenizer(br.readLine());
		for(int i=0; i<M; i++) {
			city[i] = Integer.parseInt(st.nextToken());
		}
		
		boolean isPossible = false;
		for(int i=0; i<M-1; i++) {
			if(isUnion(city[i], city[i+1])) {
				isPossible = true;
			} else {
				isPossible = false;
				break;
			}
		}
		
		System.out.println(isPossible ? "YES" : "NO");
	}
	
	static void union(int x, int y) {
		x = find(x);
		y = find(y);
		
		if(x == y) return; // 두 루트 노드가 같다면 이미 연결된 것
		
		// 더 작은 값이 부모 노드가 되도록
		if(x < y) parent[y] = x;
		else parent[x] = y;
	}
	
	// 루트 노드를 찾는 함수
	static int find(int x) {
		if(parent[x] == x) return x;
		
		// 부모 노드를 보내 루트 노드를 찾으면서 루트 노드의 값으로 배열 저장
		return parent[x] = find(parent[x]);
	}
	
	// 두 노드가 연결되어 있는지 확인
	static boolean isUnion(int x, int y) {
		x = find(x);
		y = find(y);
		
		if(x == y) return true;
		else return false;
	}

}
