import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;

/**
 * <BJ_11726_2xn타일링>
 * n=i-1일 때 가능한 경우에 세로 타일을 오른쪽 맨 끝에 추가하는 경우와
 * n=i-2일 때 가능한 경우에 가로 타일 두개, 또는 정사각형 타일을 오른쪽 맨 끝에 두는 경우로 나뉘어진다.
 * 따라서 dp[i] = dp[i-1] + dp[i-2]*2 점화식이 나온다.
 * 
 * @author YooSejin
 *
 */

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		
		// dp 배열의 크기를 N+1로 설정하면 N이 1 또는 2일 때를 따로 처리해줘야 함
		int[] dp = new int[1001];
		
		dp[1] = 1;
		dp[2] = 3;
		
		for(int i=3; i<=1000; i++) {
			dp[i] = (dp[i-1] + dp[i-2] * 2) % 10007;
		}
		
		System.out.println(dp[N]);
	}

}
