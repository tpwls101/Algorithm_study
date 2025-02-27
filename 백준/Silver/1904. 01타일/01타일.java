import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * <BJ_1904_01타일>
 * dp[i] : 길이가 i인 모든 2진 수열의 개수
 * 점화식 : dp[i] = dp[i-1] + dp[i-2]
 * i-1번째에 타일 1을 붙이고 i-2번째에 00 타일을 붙이면 됨
 * 
 * @author YooSejin
 *
 */

public class Main {
	
	static int N;
	static int[] dp;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		N = Integer.parseInt(br.readLine());
		
		dp = new int[1000001];
		
		dp[1] = 1;
		dp[2] = 2;
		
		for(int i=3; i<=1000000; i++) {
			dp[i] = (dp[i-1] + dp[i-2]) % 15746;
		}
		
		System.out.println(dp[N]);
	}

}
