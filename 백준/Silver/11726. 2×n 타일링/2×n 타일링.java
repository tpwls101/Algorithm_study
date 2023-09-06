import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * <BJ_11726_2xn타일링>
 * 2xn 크기의 직사각형을 1x2, 2x1 타일로 채우는 방법의 수는?
 * 10,007로 나눈 나머지를 출력!
 * 
 * n번째 경우의 수를 만들기 위해서는?
 * 보통 n-1번째와 n-2번째 고려!
 * n-1, n-2일 때 경우의 수가 몇 개인지는 중요x
 * n = (n-1) + (n-2)
 * 
 * @author 유세진
 *
 */

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine()); // 2xn 직사각형
		
		int[] dp = new int[1001]; // 0번 인덱스 사용x
		dp[1] = 1;
		dp[2] = 2;
		
		for(int i=3; i<n+1; i++) {
			dp[i] = (dp[i-1] + dp[i-2]) % 10007; // %10007 안해주면 오버플로우 발생
		}
		
		System.out.println(dp[n]);
		
	}

}