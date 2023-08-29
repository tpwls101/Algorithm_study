import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * <BJ_1463_1로만들기>
 * for문을 돌려서 memo[]에 차례대로 1을 만들기 위해 필요한 연산 횟수의 최소값 저장한다.
 * 
 * @author 유세진
 *
 */

public class Main {
	
	static int[] memo = new int[1000001]; // 메모이제이션 (입력값이 1~1000000이므로 크기는 1000001)
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int num = Integer.parseInt(st.nextToken()); // 입력받은 정수

		Arrays.fill(memo, Integer.MAX_VALUE);
		memo[1] = 0;
		memo[2] = 1;
		memo[3] = 1;
		
		// for문 돌려서 memo[]에 차례대로 1을 만들기 위해 필요한 연산 횟수의 최소값 저장
		for(int i=4; i<=num; i++) {
			if(i % 3 == 0) { 
				// i를 3으로 나눈 수의 연산횟수에 +1 해주고, 최소값을 비교해서 memo에 저장
				memo[i] = Math.min(memo[i], memo[i/3] + 1);
			}
			
			if(i % 2 == 0) {
				// i를 2으로 나눈 수의 연산횟수에 +1 해주고, 최소값을 비교해서 memo에 저장
				memo[i] = Math.min(memo[i], memo[i/2] + 1);
				
			}
			
			// i에서 -1 해준 수의 연산횟수에 +1 해주고, 최소값을 비교해서 memo에 저장
			memo[i] = Math.min(memo[i], memo[i-1] + 1);
		}
		
		System.out.println(memo[num]);
	}
	
}