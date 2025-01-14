import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * <BJ_15989_123더하기4>
 * 전형적인 DP 문제라고 함
 * DP가 아직 익숙하지 않은 나에겐 너무 어려웠다ㅜㅜ
 * 
 * n의 범위가 10,000까지라 백트래킹하면 시간초과날 수 있을 것 같다.
 * 
 * 이 문제는 수식 중복이 되지 않기 때문에 이차원 배열을 사용한다.
 * dp[i][j]는 i를 만드는데 +j로 끝나는 경우의 수이다.
 * N=4일 때를 예로 들어보자.
 * 3을 만드는 경우 +1 -> 이 때, 중요한 건 오름차순이 전제되어 있어야 한다. 따라서 3을 만드는 경우 중 +1로 끝나는 경우에만 가능하다.
 * 2를 만드는 경우 +2 -> 2를 만드는 경우 중 1로 끝나거나 2로 끝나는 경우만 가능하다.
 * 1을 만드는 경우 +3 -> 1을 만드는 경우 중 1로 끝나거나 2로 끝나거나 3으로 끝나는 경우가 가능하다.
 * 이를 식으로 만들면
 * dp[i][1] = dp[i-1][1];
 * dp[i][2] = dp[i-2][1] + dp[i-2][2];
 * dp[i][3] = dp[i-3][1] + dp[i-3][2] + dp[i-3][3];
 * 
 * 생각이 많이 필요한 것 같다. 규칙을 어떻게 찾는지 많이 연습해보자.
 * 
 * @author YooSejin
 *
 */

public class Main {

	static int T;
	static int N;
	static int[][] dp = new int[10001][4];
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		T = Integer.parseInt(br.readLine());
		
		dp[1][1] = 1; // 1
		dp[2][1] = 1; // 1+1
		dp[2][2] = 1; // 2
		dp[3][1] = 1; // 1+1+1
		dp[3][2] = 1; // 1+2
		dp[3][3] = 1; // 3
		
		for(int i=4; i<=10000; i++) {
			dp[i][1] = dp[i-1][1];
			dp[i][2] = dp[i-2][1] + dp[i-2][2];
			dp[i][3] = dp[i-3][1] + dp[i-3][2] + dp[i-3][3];
		}
		
		for(int i=0; i<T; i++) {
			N = Integer.parseInt(br.readLine());
			System.out.println(dp[N][1] + dp[N][2] + dp[N][3]);
		}
	}

}
