import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * <BJ_11057_오르막수>
 * dp[i][j] 값을 저장할 때 모듈러 연산을 안하면 오버플로우가 발생한다.
 * 따라서 dp값 저장할 때 모듈러 연산을 해주고, 최악의 경우에도 int형으로 충분하다.
 * dp에 10007로 나눈 값이 저장되어 있지만, N자리 수일 때 가능한 경우의 수를 모두 더한 후에도 모듈러 연산을 해줘야 함에 주의하자.
 * 
 * @author YooSejin
 *
 */

public class Main {
	
	static int N;
	static int[][] dp;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		N = Integer.parseInt(br.readLine());
		
		dp = new int[N+1][10];
		
		for(int i=0; i<10; i++) {
			dp[1][i] = 1;
		}
		
		for(int i=2; i<=N; i++) {
			for(int j=0; j<10; j++) {
				if(j == 0) {
					dp[i][j] = 1;
					continue;
				}
				dp[i][j] = (dp[i][j-1] + dp[i-1][j]) % 10007;
			}
		}
		
		int answer = 0;
		for(int i=0; i<10; i++) {
			answer += dp[N][i];
		}
		System.out.println(answer % 10007);
	}

}
