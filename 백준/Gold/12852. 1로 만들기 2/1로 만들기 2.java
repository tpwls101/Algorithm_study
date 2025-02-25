import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * <BJ_12852_1로만들기2>
 * dp[i]를 최댓값(Integer.MAX_VALUE)으로 초기화한 후, 3으로 나눌 수 있을 때, 2로 나눌 수 있을 때, -1할 때 비교해 최소값으로 갱신해도 되지만
 * -1은 어떤 수에서도 가능하기 때문에 dp[i]를 dp[i-1] + 1로 초기화해 3으로 나눌 수 있을 때와 2로 나눌 수 있을 때로 비교해 최소값으로 갱신해도 된다.
 * 
 * N을 1로 만드는 방법은 trace 배열을 만들어 i가 갈 수 있는, 연산이 가장 적은 수를 저장한다.
 * 예를 들어 10이 갈 수 있는 수는 -1을 한 9나 나누기 2를 한 5이다.
 * dp[9] = 2이고 dp[5] = 3으로 10에서 9로 갈 때 연산이 더 적으니 trace[10]에는 9를 저장한다.
 * 즉, 10에서 9로 가는 것이 가장 연산 횟수가 적은 방법이라는 뜻이다.
 * 
 * @author YooSejin
 *
 */

public class Main {
	
	static int N;
	static int[] dp;
	static int[] trace;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		N = Integer.parseInt(br.readLine());
		
		dp = new int[N+1];
		trace = new int[N+1];
		
		// dp[i] : i를 1로 만드는데 사용되는 연산의 최솟값
		for(int i=2; i<=N; i++) {
			// 최댓값(Integer.MAX_VALUE)로 초기화해도 되지만
			// -1 연산은 모든 i값에서 가능하기 때문에 dp[i-1] + 1로 초기화하는 것이 더 효율적이다.
			dp[i] = dp[i-1] + 1; // -1 연산
			trace[i] = i-1;
			
			if(i % 3 == 0 && dp[i/3] + 1 < dp[i]) {
				dp[i] = dp[i/3] + 1;
				trace[i] = i/3;
			}
			
			if(i % 2 == 0 && dp[i/2] + 1 < dp[i]) {
				dp[i] = dp[i/2] + 1;
				trace[i] = i/2;
			}
		}
		
		System.out.println(dp[N]);
		
		StringBuilder sb = new StringBuilder();
		while(N > 0) {
			sb.append(N + " ");
			N = trace[N];
		}
		System.out.println(sb);
	}
	
}
