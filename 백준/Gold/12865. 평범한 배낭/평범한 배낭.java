import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * <BJ_12865_평범한배낭> - 2차원 DP
 * dp에 무엇을 저장할 것인가? -> 가치의 최댓값
 * i번째 물건까지 넣을 수 있고 무게는 w까지 넣을 수 있을 때 가치의 최댓값을 저장한다.
 * 그렇다면 w가 현재 추가할 수 있는 물건의 무게(weight[i])보다 작다면 -> i번째 물건을 추가할 수 없으므로 dp[i-1][w]이 최댓값이 된다.
 * 그게 아니라면 i번째 물건도 사용할 수 있으므로 i번째 물건을 넣는 경우와 아닌 경우를 비교해 최댓값을 갱신해 준다.
 * 		- 전제 : i번째 물건도 배낭에 넣을 수 있음
 * 		- i번째 물건을 넣지 않는 경우 : dp[i-1][w]
 * 		- i번째 물건을 넣는 경우 : dp[i-1][w-weight[i]] + value[i]
 * 				-> i번째 물건을 넣을 수 있도록 무게룰 weight[i]만큼 뺐을 때의 최댓값에 i번째 물건의 무게 value[i]를 더해준다.
 * 		- 두 가지 경우 중 더 가치가 큰 경우를 최댓값으로 갱신한다.
 * 		- 그러면 dp[i][w]는 현재까지 가능한 경우 중 가장 가치가 큰 경우의 값을 저장하게 된다.
 * 따라서 dp[N][K]를 출력하면 된다.
 * 
 * @author YooSejin
 *
 */

public class Main {
	
	static int N; // 물품의 개수
	static int K; // 준서가 버틸 수 있는 무게
	static int[] weight;
	static int[] value;
	static int[][] dp; // 가치의 최댓값 저장 (dp[i][w] = i번째 물건까지 넣을 수 있고 무게는 w까지 넣을 수 있을 때)
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		
		weight = new int[N+1];
		value = new int[N+1];
		
		for(int i=1; i<=N; i++) {
			st = new StringTokenizer(br.readLine());
			weight[i] = Integer.parseInt(st.nextToken());
			value[i] = Integer.parseInt(st.nextToken());
		}
		
		dp = new int[N+1][K+1];
		
		for(int i=1; i<=N; i++) {
			for(int w=1; w<=K; w++) {
				// 무게는 w까지 넣을 수 있는데 i번째 물건의 무게가 더 크다면 i번째 물건을 사용할 수 없다.
				// 따라서 이전값이 최댓값이 된다.
				if(weight[i] > w) {
					dp[i][w] = dp[i-1][w];
				}
				// 그게 아니라면
				// i번째 물건을 추가할 수 없는 경우와 있는 경우를 비교해 최댓값을 갱신한다.
				else {
					dp[i][w] = Math.max(dp[i-1][w], dp[i-1][w-weight[i]] + value[i]);
				}
			}
		}
		
		System.out.println(dp[N][K]);
	}

}
