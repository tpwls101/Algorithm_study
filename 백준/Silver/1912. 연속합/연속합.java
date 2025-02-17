import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * <BJ_1912_연속합>
 * dp[i]에 처음부터 i번째까지의 숫자들 중 연속된 수의 최댓값을 저장(X)
 * 앞의 연속된 수들을 포함하느냐 버리느냐!!
 * 
 * @author YooSejin
 *
 */

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		
		int N = Integer.parseInt(br.readLine());
		
		int[] arr = new int[N];
		int[] dp = new int[N];
		
		st = new StringTokenizer(br.readLine());
		for(int i=0; i<N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		
		dp[0] = arr[0];
		int max = dp[0];
		
		for(int i=1; i<N; i++) {
			dp[i] = Math.max(dp[i-1] + arr[i], arr[i]); // 앞의 연속된 수들을 포함하느냐 버리느냐
			max = Math.max(dp[i], max);
		}
		
		System.out.println(max);
	}

}
