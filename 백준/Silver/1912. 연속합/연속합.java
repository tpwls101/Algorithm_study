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
	
	static int[] arr;
	static Integer[] dp;
	static int max;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		
		int N = Integer.parseInt(br.readLine());
		
		arr = new int[N];
		dp = new Integer[N];
		
		st = new StringTokenizer(br.readLine());
		for(int i=0; i<N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		
		dp[0] = arr[0];
		
		recur(N-1);
		
		max = dp[0];
		for(int i=0; i<N; i++) {
			max = Math.max(max, dp[i]);
		}
		
		System.out.println(max);
	}
	
	static int recur(int N) {
		if(dp[N] == null) {
			dp[N] = Math.max(recur(N-1) + arr[N], arr[N]);
		}
		
		return dp[N];
	}

}
