import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

/**
 * <방법1>
 * 해시를 사용한 집합과 맵
 * Hash는 시간복잡도가 O(1)이므로 속도는 훨씬 빠르다.
 * 
 * <방법2>
 * 트리를 사용한 집합과 맵
 * 정렬을 유지하기 위해 Hash가 아닌 Tree 자료구조 사용
 * value 값만 확인하면 되므로 TreeSet 사용
 * TreeSet을 사용하면 따로 정렬할 필요는 없지만 Tree의 시간복잡도는 O(logN)이므로 Hash에 비해 속도가 느리다.
 * 
 * @author YooSejin
 *
 */

public class BJ_1822_차집합_방법1 {
	
	static int N; // 집합 A의 원소의 개수
	static int M; // 집합 B의 원소의 개수
	static int[] A; // 집합 A의 원소를 저장할 배열
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());		
		
		A = new int[N];
		
		st = new StringTokenizer(br.readLine());
		for(int i=0; i<N; i++) {
			A[i] = Integer.parseInt(st.nextToken());
		}
		Arrays.sort(A);
		
		Map<Integer, Integer> map = new HashMap<>();
		st = new StringTokenizer(br.readLine());
		for(int i=0; i<M; i++) {
			int num = Integer.parseInt(st.nextToken());
			map.put(num, 0);
		}
		
		StringBuilder sb = new StringBuilder();
		int count = 0;
		for(int i=0; i<N; i++) {
			if(map.get(A[i]) == null) {
				count++;
				sb.append(A[i] + " ");
			}
		}
		
		if(count == 0) {
			System.out.println("0");
		} else {
			System.out.println(count + "\n" + sb);			
		}
	}

}
