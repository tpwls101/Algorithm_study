import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * <BJ_123더하기> - DP 문제
 * 1, 2, 3을 만드는 경우의 수는 먼저 지정해둠
 * 4부터는 4를 만들기 위해서는 1+3, 2+2, 3+1
 * 5를 만들기 위해서는 1+4, 2+3, 3+2
 * 6을 만들기 위해서는 1+5, 2+4, 3+3
 * 이처럼 1, 2, 3 각각에 어떤 수를 더해야하는지 알 수 있기 때문에
 * 그 수를 만들기 위한 경우의 수를 구해서 더해주면 된다.
 * 따라서 점화식은 dp[i] = dp[i-1] + dp[i-2] + dp[i-3]이 나온다.
 * 
 * 직접 써보면 규칙이 더 쉽게 보인다!!
 * 
 * @author YooSejin
 *
 */

public class Main {
	
	static int T; // 테스트케이스 수
	static int N; // 1, 2, 3을 더해서 N 만들기 (11보다 작음)
	static int[] dp = new int[12]; // dp[i] : i를 만드는 경우의 수
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		T = Integer.parseInt(br.readLine());
		
		dp[1] = 1; // 1
		dp[2] = 2; // 1+1, 2
		dp[3] = 4; // 1+1+1, 1+2, 2+1, 3
		
		for(int i=4; i<=11; i++) {
			dp[i] = dp[i-1] + dp[i-2] + dp[i-3];
		}
		
		for(int i=0; i<T; i++) {
			N = Integer.parseInt(br.readLine());
			System.out.println(dp[N]);
		}
	}

}
