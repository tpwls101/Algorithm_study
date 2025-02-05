import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * <BJ_2193_이친수>
 * dp[i-1]에 해당하는 수에 0을 붙이는 경우와
 * dp[i-2]에 해당하는 수에 01을 붙이는 경우 -> 1이 연속으로 두번 나올 수 없으니 끝이 0이 오든 1이 오든 01을 붙이면 1이 두번 나올 수 없다.
 * 
 * dp배열의 타입은 long을 사용해야 함에 주의할 것
 * N은 90까지 가능하므로 최악의 경우 2^90가지 경우의 수가 나올 수 있다 생각하면 long 타입을 써야 함
 * 
 * @author YooSejin
 *
 */

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int N = Integer.parseInt(br.readLine());
		
		long[] dp = new long[91];
		
		dp[1] = 1;
		dp[2] = 1;
		
		for(int i=3; i<=90; i++) {
			dp[i] = dp[i-1] + dp[i-2];
		}
		
		System.out.println(dp[N]);
	}

}
