import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

/**
 * <BJ_2075_N번째큰수>
 * 1. list에 N*N개의 수를 담아 정렬한 후 N번째 큰 수를 구하는 방법 (1976ms)
 * 
 * 2. 우선순위 큐를 사용하는 방법
 * 		- 시간 복잡도 단축 : O(N) -> N개의 숫자만 비교해 정렬
 * 		- 공간 복잡도 또한 효율적 : N만큼의 공간만 사용하기 때문
 * 
 * @author YooSejin
 *
 */

public class Main {
	
	static int N;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		
		N = Integer.parseInt(br.readLine());
		
		PriorityQueue<Integer> pq = new PriorityQueue<>();
		
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0; j<N; j++) {
				int num = Integer.parseInt(st.nextToken());
				
				if(pq.size() == N) {
					if(pq.peek() < num) {
						pq.poll();
						pq.add(num);
					}
				} else {
					pq.add(num);
				}
			}
		}
		
		System.out.println(pq.poll());
	}

}