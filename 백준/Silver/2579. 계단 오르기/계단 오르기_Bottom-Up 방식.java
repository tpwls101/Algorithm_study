import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * <BJ_2579_계단오르기> - Bottom-Up 방식
 * 마지막 계단은 반드시 밟는다.
 * 따라서  (O,O) 또는 
 *    (O,X,O) -> i번째는 무조건 밟는 두 가지 경우를 비교해야 한다.
 * 따라서 dp[i]는 (dp[i-1] + i번째 계단의 점수)와 (dp[i-2] + i번째 계단의 점수)의 최댓값을 저장해야 한다.
 * 
 * 내가 푼 풀이가 틀린 이유 :
 * dp[i-1]을 사용하면 안된다! i번째와 i-1번째 계단을 밟는 경우 i-2번째 계단은 밟으면 안된다.
 * i-2번째 계단을 밟았는지 안밟았는지는 dp[i-1]에서 알 수가 없다.
 * 따라서 dp[i-3]을 사용하고, 왜냐 이 때는 i-2번째 계단을 안밟은 상태이므로
 * dp[i-3]에 i-1번째 계단의 점수를 더한 후 다시 i번째 계단의 점수를 더해줘야 xoo의 최대 점수가 나온다.
 * 
 * @author YooSejin
 *
 */

public class Main {
	
	static int N; // 계단의 개수
	static int[] score;
	static int[] dp;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		N = Integer.parseInt(br.readLine());
		
		score = new int[N+1];
		dp = new int[N+1];
		
		for(int i=1; i<=N; i++) {
			score[i] = Integer.parseInt(br.readLine());
		}
		
		dp[1] = score[1];
		
		// 예외 처리
		if(N >= 2) {
			dp[2] = score[1] + score[2];
		}
		
		for(int i=3; i<=N; i++) {
			dp[i] = Math.max(dp[i-2], dp[i-3]+score[i-1]) + score[i];
		}
		
		System.out.println(dp[N]);
	}
	
}
