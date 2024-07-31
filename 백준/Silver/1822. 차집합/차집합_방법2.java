import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;
import java.util.TreeSet;

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

public class BJ_1822_차집합_방법2 {
	
	static int N; // 집합 A의 원소의 개수
	static int M; // 집합 B의 원소의 개수
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());		
		
		st = new StringTokenizer(br.readLine());
		TreeSet<Integer> set = new TreeSet<>();
		for(int i=0; i<N; i++) {
			set.add(Integer.parseInt(st.nextToken())); // TreeSet은 정렬이 유지된다!
		}
		
		st = new StringTokenizer(br.readLine());
		for(int i=0; i<M; i++) {
			int num = Integer.parseInt(st.nextToken());
			if(set.contains(num)) {
				set.remove(num);
			}
		}
		
		System.out.println(set.size());
		for(Integer s : set) {
			System.out.print(s + " ");
		}
	}

}
