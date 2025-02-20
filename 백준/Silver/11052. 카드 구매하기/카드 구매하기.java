import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * <BJ_11052_카드구매하기>
 * 카드를 i개 구매하는 방법
 * 카드가 1개 들어있는 카드팩을 구매하고, 카드가 i-1개 들어있는 카드팩을 구매
 * 카드가 2개 들어있는 카드팩을 구매하고, 카드가 i-2개 들어있는 카드팩을 구매
 * 카드가 3개 들어있는 카드팩을 구매하고, 카드가 i-3개 들어있는 카드팩을 구매
 * ...
 * 
 * 각 dp[i]에 카드를 i개 구매할 때의 최대 가격을 저장
 * 그러면 dp[N]이 카드를 N개 구매할 때 가격의 최댓값이다.
 * 
 * @author YooSejin
 *
 */

public class Main {
	
	static int N; // 구매하려는 카드의 개수
	static int[] cost;
	static int[] dp;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		
		N = Integer.parseInt(br.readLine());
		
		cost = new int[N+1];
		dp = new int[N+1];
		
		st = new StringTokenizer(br.readLine());
		for(int i=1; i<=N; i++) {
			cost[i] = Integer.parseInt(st.nextToken());
		}
		
		// dp[i] : 카드를 i개 살 때의 가격의 최댓값
		dp[1] = cost[1];
		
		for(int i=2; i<=N; i++) {
			dp[i] = cost[i];
			for(int j=1; j<=i/2; j++) {
				dp[i] = Math.max(dp[j] + dp[i-j], dp[i]);
			}
		}
		
		System.out.println(dp[N]);
	}

}
