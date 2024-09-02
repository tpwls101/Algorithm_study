import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

/**
 * <BJ_2143_두배열의합>
 * 투 포인터 사용하는 문제
 * 처음에는 순열로 풀 수 있나 했는데 i~j까지의 원소를 모두 사용해야 하기 때문에 투 포인터로 풀어야 한다.
 * 
 * 각 배열의 부배열의 의 합 모든 경우를 구한 다음 T에 맞는 원소들을 매칭해주면 된다!
 * 
 * 1. 모든 부배열의 원소의 합을 구해서 list에 저장
 * 2. listA는 오름차순 정렬하고 listB는 내림차순 정렬
 * 		ex. listA = { 1, 1, 2, 3, 3, 4, 4, 5, 6, 7 }
 * 			listB = { 6, 5, 4, 3, 2, 1 }
 * 3. pointerA와 pointerB는 각 리스트의 시작 지점으로 초기화
 * 4. listA.get(pointerA) + listB.get(pointerB)
 * 		두 부배열의 합이
 * 		1) T보다 큰 경우 : pointerB 증가
 * 		2) T보다 작은 경우 : pointerA 증가
 * 		3) T인 경우 : count!! 단, 연속된 수가 있는 경우에는 Acnt*Bcnt 만큼 더해줄 것!
 * 
 * 주의사항!!
 * 부배열의 원소의 합은 최대 10억 -> list는 Integer 타입 가능
 * listA와 listB의 합은 최대 20억 -> Integer 타입 가능
 * Acnt, Bcnt, count는 long 타입!!!
 * 
 * @author YooSejin
 *
 */

public class Main {

	static int T; // A의 부 배열의 합에 B의 부 배열의 합을 더해서 T 만들기
	static int N;
	static int M;
	static int[] A;
	static int[] B;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		
		T = Integer.parseInt(br.readLine());
		
		N = Integer.parseInt(br.readLine());
		A = new int[N];
		st = new StringTokenizer(br.readLine());
		for(int i=0; i<N; i++) {
			A[i] = Integer.parseInt(st.nextToken());
		}
		
		M = Integer.parseInt(br.readLine());
		B = new int[M];
		st = new StringTokenizer(br.readLine());
		for(int i=0; i<M; i++) {
			B[i] = Integer.parseInt(st.nextToken());
		}
		
		// 부배열의 원소의 합을 구해서 list에 저장
		List<Integer> listA = new ArrayList<>();
		List<Integer> listB = new ArrayList<>();
		
		for(int i=0; i<N; i++) {
			int sum = 0;
			for(int j=i; j<N; j++) {
				sum += A[j];
				listA.add(sum);
			}
		}
		
		for(int i=0; i<M; i++) {
			int sum = 0;
			for(int j=i; j<M; j++) {
				sum += B[j];
				listB.add(sum);
			}
		}
		
		// A는 오름차순 정렬, B는 내림차순 정렬
		Collections.sort(listA);
		Collections.sort(listB, Collections.reverseOrder());
		
		int pointerA = 0;
		int pointerB = 0;
		long count = 0;
		
		while(pointerA < listA.size() && pointerB < listB.size()) {
			// int 타입 가능 (최대 20억)
			int sum = listA.get(pointerA) + listB.get(pointerB);
			
			if(sum > T) {
				pointerB++;
			} else if(sum < T) {
				pointerA++;
			} else {
				// 합이 T인 경우에는 연속된 수가 있다면 곱해서 더해줘야 한다!
				// 예시)
				// listA = { 1, 1, 1, 2, 3, 4 }
				// listB = { 1, 3, 3, 4 }
				// T = 4, 합 4를 찾으면
				// Acnt = 연속된 1이 3개 = 3
				// Bcnt = 연속된 3이 2개 = 2
				// count += 3*2
				
				int a = listA.get(pointerA);
				int b = listB.get(pointerB);
				
				long Acnt = 0; // listA에서 연속된 수의 수 (long 타입 필수)
				long Bcnt = 0; // listB에서 연속된 수의 수 (long 타입 필수)
				
				while(pointerA < listA.size() && listA.get(pointerA) == a) {
					Acnt++;
					pointerA++;
				}
				
				while(pointerB < listB.size() && listB.get(pointerB) == b) {
					Bcnt++;
					pointerB++;
				}
				
				count += Acnt * Bcnt; // 최대 50만*50만이므로 long 타입
			}
		}
		
		System.out.println(count);
	}

}
