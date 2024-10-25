import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * <BJ_13458_시험감독>
 * 최악의 경우 시험장은 100만개
 * 각 시험장의 응시자 수도 100만명
 * 근데 총감독관과 부감독관이 감시할 수 있는 응시자 수는 1명이라면
 * while문 100만번 돌고 또 시험장 개수만큼 도니까 100만 x 100만 -> 시간초과!! 주의!!
 * 
 * while문 말고 나눠서 구해야 한다!
 * + 최소 감독수는 100만x100만 = 1조까지 가능하므로 long 타입을 써야한다!!! (간과하기 쉬운 것! 매우 중요!)
 * 
 * @author YooSejin
 *
 */

public class Main {
	
	static int N; // 시험장의 개수
	static int[] A; // 각 시험장의 응시자 수
	static int B; // 총감독관이 한 시험장에서 감시할 수 있는 응시자 수
	static int C; // 부감독관이 한 시험장에서 감시할 수 있는 응시자 수
	static long count = 0; // 필요한 감독관 수의 최솟값
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		
		N = Integer.parseInt(br.readLine());
		
		A = new int[N];
		
		st = new StringTokenizer(br.readLine());
		for(int i=0; i<N; i++) {
			A[i] = Integer.parseInt(st.nextToken());
		}
		
		st = new StringTokenizer(br.readLine());
		B = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		
		for(int i=0; i<N; i++) {
			// 총감독관은 1명만 있어야함
			count++;
			A[i] -= B;
			
			// 부감독관이 추가로 필요하다면 추가
			if(A[i] > 0) {
				count += A[i] / C;
				if(A[i] % C != 0) count++;
			}
		}
		
		System.out.println(count);
	}

}