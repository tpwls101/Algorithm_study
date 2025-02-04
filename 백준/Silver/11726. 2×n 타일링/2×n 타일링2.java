import java.util.Scanner;

/**
 * <BJ_11726_2xn타일링>
 * n = 1,2,3,4, ... ,9 일 때 직접 가능한 경우의 수를 그려보면 규칙이 나온다.
 * 일단 i번째 경우의 수는 (i-1번째 경우의 수) + (i-2번째 경우의 수)라는 규칙을 찾을 수 있다.
 * 그림으로 생각해보면 n이 i-1일 때 가능한 경우의 수에 2x1 타일을 오른쪽 맨 끝에 추가하는 경우와
 * n이 i-2일 때 가능한 경우의 수에 1x2 타일 두개를 오른쪽 맨 끝에 두는 경우로 나뉘어진다.
 * 따라서 dp[i] = dp[i-1] + dp[i-2] 점화식이 나온다.
 * 결론적으로 n이 1씩 증가할 때마다 끝이 어떻게 변하는지를 생각해보면 i-1일 때 세로 타일을 추가하고, i-2일 때 가로 타일을 추가하는 경우가 추가된다.
 * 
 * @author YooSejin
 *
 */

public class Main {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();
		
		// dp 배열의 크기를 N+1로 설정하면 N이 1 또는 2일 때를 따로 처리해줘야 함
		int[] dp = new int[1001];
		
		dp[1] = 1;
		dp[2] = 2;
		
		for(int i=3; i<=1000; i++) {
			dp[i] = (dp[i-1] + dp[i-2]) % 10007;
		}
		
		System.out.println(dp[N]);
	}

}
