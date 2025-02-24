import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * <BJ_2156_포도주시식>
 * BJ_2579_계단오르기 문제와 유사하다. 연속으로 두번까지만 가능하다.
 * 단, 계단오르기는 반드시 마지막 계단을 밟아야하는 것과 달리 이번 문제의 포인트는 반드시 마지막 포도주를 시식할 필요가 없다는 것이다.
 * 따라서 포도주를 안마시고 마시고는 경우(XO), 안마시고 마시고 마시는 경우(XOO), 마시고 안마시는 경우(OX)(현재 포도주를 반드시 시식할 필요가 없음. 이전과 전전 포도주를 마시는 경우가 더 많이 마실 수도 있음.)
 * 세 가지 경우의 최댓값으로 dp[i]를 갱신해야 한다.
 * 
 * 나는 처음에 i번째 포도주를 반드시 마시는 경우만 확인하고, dp 배열에서 가장 큰 값을 정답으로 찾아냈는데
 * 생각해보니 포도주가 훨씬 많은 경우에는 앞에서 큰 수가 나와도 뒤에서 더 마시다 보면 뒤의 값이 최대가 되기 마련이다.
 * 따라서 현재 포도주를 마시는 경우와 마시지 않는 경우로 나눠 최댓값을 갱신한 뒤, dp[N]번째 값을 출력하면 마실 수 있는 최대 양이 된다.
 * 
 * @author YooSejin
 *
 */

public class Main {
	
	static int N; // 포도주 잔의 개수
	static int[] arr;
	static int[] dp;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		N = Integer.parseInt(br.readLine());
		
		arr = new int[10001];
		dp = new int[10001];
		
		for(int i=1; i<=N; i++) {
			arr[i] = Integer.parseInt(br.readLine());
		}
		
		// dp[i] : i번 포도주를 마시거나 마시지 않을 때 마실 수 있는 포도주의 최대 양
		dp[1] = arr[1];
		dp[2] = dp[1] + arr[2];
		
		for(int i=3; i<=N; i++) {
			dp[i] = Math.max(Math.max(dp[i-2] + arr[i], dp[i-3] + arr[i-1] + arr[i]), dp[i-1]); // XO or XOO or OX
		}
		
		System.out.println(dp[N]);
	}

}
