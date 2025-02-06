import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * <BJ_10844_쉬운계단수>
 * 처음엔 N=1,2,3, ... 일 경우 가능한 경우의 수를 다 적어보았다.
 * 그러면 (N-1일 때 x2 - 임의의 수) 이런식으로 규칙이 나오나 싶었지만 실패
 * 
 * 잘 보면 맨 마지막 자리의 숫자가 무엇인가에 따라 +1,-1이 되기도 하고 +1만 혹은 -1만 되기도 한다.
 * 따라서 N=1,2, .. 100일 때 각각에 대해 오른쪽 맨 끝의 자리에 0~9의 숫자를 각각 몇 개 사용할 수 있는가를 2차원 배열로 구해주었다.
 * 즉, 행에는 N을, 열에는 N번째 자리에 0~9를 몇 개 사용할 수 있는가를 저장해주었다.
 * 그러면 1~8로 끝나는 경우는 +1,-1을 해서 만들 수 있다.
 * 단, 0으로 끝나는 경우는 1로 끝나는 경우에서 -1을 해 만들 수 밖에 없고
 * 9로 끝나는 경우는 8로 끝나는 경우에서 +1을 해 만들 수 밖에 없으니 따로 처리해주어야 한다.
 * 
 * @author YooSejin
 *
 */

public class Main {
	
	final static long mod = 1000000000;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int N = Integer.parseInt(br.readLine());
		
		long[][] dp = new long[101][10];
		
		// 초기화
		for(int i=1; i<10; i++) {
			dp[1][i] = 1;
		}
		
		for(int i=2; i<=100; i++) {
			dp[i][0] = dp[i-1][1] % mod;
			for(int j=1; j<=8; j++) {
				dp[i][j] = (dp[i-1][j-1] + dp[i-1][j+1]) % mod;
			}
			dp[i][9] = dp[i-1][8] % mod;
		}
		
		long answer = 0;
		for(int i=0; i<10; i++) {
			answer += dp[N][i];
		}
		System.out.println(answer % mod);
	}

}
