import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * <D4_3289_서로소집합>
 * 처음에는 하나의 원소를 가진 서로소 집합을 만든다. (make)
 * 합집합 연산을 위해서 union 메서드를 만들고 a, b가 속한 집합의 대표자를 찾아 같게 만들어준다.
 * 두 원소가 같은 집합에 있는지 확인하기 위해서는 두 원소의 대표자가 같아야 하는데 이를 위해 find 메서드를 실행한다.
 * x의 부모가 자기자신이면 대표자이므로 자신의 값을 리턴하고, 밑의 자식 원소들이면 대표자의 값을 넣어주어 경로압축해준다. -> 시간 단축
 * 
 * 
 * @author 유세진
 *
 */

public class Solution {
	
	static int n; // a,b -> n 이하의 자연수 (1~n)
	static int[] parents; // 부모 인덱스를 저장할 배열
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		 
		int T = Integer.parseInt(br.readLine());
		 
		for(int test_case=1; test_case<=T; test_case++) {
			st = new StringTokenizer(br.readLine());
			n = Integer.parseInt(st.nextToken()); // a,b -> n 이하의 자연수 (1~n)
			int m = Integer.parseInt(st.nextToken()); // 입력으로 주어지는 연산의 개수
			
			parents = new int[n+1]; // 숫자 1의 부모인덱스는 배열의 1번 인덱스에 저장
			make();
			
			StringBuilder sb = new StringBuilder();
			sb.append("#" + test_case + " ");
			for(int i=0; i<m; i++) {
				st = new StringTokenizer(br.readLine());
				int operation = Integer.parseInt(st.nextToken()); // 합집합 연산인지 두 원소가 같은 집합에 포함되어 있는지 확인하는 연산인지
				int a = Integer.parseInt(st.nextToken());
				int b = Integer.parseInt(st.nextToken());
				
				// 합집합 연산
				if(operation == 0) {
					union(a, b);
				}
				// 두 원소가 같은 집합에 포함되어 있는지 확인
				else {
					if(find(a) == find(b)) {
						sb.append("1");
					} else {
						sb.append("0");
					}
				}
			}
			
			System.out.println(sb);
		}
	}
	
	// 서로소 집합 만들기
	private static void make() {
		for(int i=1; i<=n; i++) {
			parents[i] = i;
		}
	}
	
	// 원소의 집합의 식별자 찾기
	private static int find(int x) {
		if(parents[x] == x) return x; // x의 부모가 자기자신이면 대표자
		//return find(parents[x]); // 내꺼의 부모 원소의 식별자를 찾아라 
		return parents[x] = find(parents[x]); // 경로압축 !!!! (안하면 시간초과)
	}
	
	// 원소들의 집합 합치기
	private static void union(int a, int b) { // b를 a로 합치기
		// a, b가 아니라 a랑 b가 속한 집합의 식별자 바꾸기
		int aRoot = find(a); // a의 식별자 찾기
		int bRoot = find(b); // b의 식별자 찾기
		
		if(aRoot != bRoot) { // 두 식별자가 다르면
			parents[bRoot] = aRoot; // 같게 만들어주기
		}
	}

}