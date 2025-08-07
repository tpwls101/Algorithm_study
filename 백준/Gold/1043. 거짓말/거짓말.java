import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

/**
 * <BJ_1043_거짓말>
 * 유니온 파인드
 * 거짓말을 할 수 있는 집합인지 아닌지 구별
 * 
 * 1. 각 파티에 참석하는 사람들의 번호를 저장하면서 union으로 집합 합치기
 * 		- ArrayList<Integer>를 자료형으로 가지는 배열
 * 		- 이 때, 진실을 아는 사람을 루트 노드로 보낸다. 그러면 이후 비교하기 쉬움.
 * 2. 파티를 다시 for문 돌린다.
 * 		- i번째 파티의 첫 번째 인원의 루트 노드가 진실을 아는 사람이라면 그 파티에서는 거짓말을 할 수 없다.
 * 		- 반면 진실을 모른다면 거짓말을 할 수 있으므로 카운트한다.
 * 
 * @author YooSejin
 *
 */

public class Main {
	
	static int N; // 사람의 수
	static int M; // 파티의 수
	static List<Integer> truth; // 진실을 아는 사람들
	static List<Integer>[] party; // 각 파티마다 참석하는 사람들을 저장
	static int[] parent;
	
	public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        
        // 루트 노드 초기화
        parent = new int[N+1];
        for(int i=0; i<=N; i++) {
        	parent[i] = i;
        }
        
        truth = new ArrayList<>();
        st = new StringTokenizer(br.readLine());
        int truthCnt = Integer.parseInt(st.nextToken()); // 진실을 아는 사람 수
        if(truthCnt == 0) { // 진실을 아는 사람이 아무도 없다면 모든 파티에서 거짓말을 할 수 있으므로 출력하고 종료
        	System.out.println(M);
        	return;
        }
        for(int i=0; i<truthCnt; i++) {
        	truth.add(Integer.parseInt(st.nextToken()));
        }
		
        party = new ArrayList[M];
        
        for(int i=0; i<M; i++) {
        	party[i] = new ArrayList<>(); // 초기화
        	
        	st = new StringTokenizer(br.readLine());
        	int cnt = Integer.parseInt(st.nextToken());
        	int x = Integer.parseInt(st.nextToken());
        	party[i].add(x);
        	
        	for(int j=1; j<cnt; j++) {
        		int y = Integer.parseInt(st.nextToken());
        		union(x, y); // 진실을 아는 사람을 루트 노드로 보내면서 연결
        		party[i].add(y);
        	}
        }
        
        int count = 0;
        for(int i=0; i<M; i++) {
        	int first = party[i].get(0); // 어차피 i번째 파티원은 하나의 집합으로 연결되어 있으므로 첫번째 사람만 확인
        	if(truth.contains(find(first))) { // 첫 번째 파티의 루트 노드가 진실을 아는 사람이라면 거짓말을 할 수 없음
        		continue;
        	} else { // 진실을 아는 사람이 없다면 거짓말을 할 수 있음
        		count++;
        	}
        }
        
        System.out.println(count);
	}
	
	static void union(int x, int y) {
		x = find(x);
		y = find(y);
		
		if(x == y) return; // 두 루트 노드가 같으면 이미 같은 집합
		
		if(truth.contains(y)) { // 진실을 아는 사람이라면 진실을 아는 사람을 루트 노드로 연결
			int tmp = x;
			x = y;
			y = tmp;
		}
		parent[y] = x;
	}
	
	static int find(int x) {
		if(parent[x] == x) return x;
		
		return parent[x] = find(parent[x]);
	}
}
