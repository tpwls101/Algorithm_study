import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * <BJ_9461_파도반수열>
 * 점화식은 찾아 맞았지만 답의 범위 고려를 못했다.
 * 근데 N=100일 때 int형의 범위를 넘는다는건 어떻게 아는걸까...?
 * 직접 찍어봐서??
 * 그렇다. 직접 찍어보는게 제일 빠르다..ㅎ
 * 가장 최악의 범위를 입력해 직접 출력값을 확인해보자. 오버플로우가 발생하면 long 타입을 써줄 것!!
 * 
 * @author YooSejin
 *
 */

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int T = Integer.parseInt(br.readLine());
		
		long[] dp = new long[101];
		
		dp[1] = 1;
		dp[2] = 1;
		dp[3] = 1;
		dp[4] = 2;
		dp[5] = 2;
		
		for(int i=6; i<=100; i++) {
			dp[i] = dp[i-1] + dp[i-5];
		}
		
		for(int t=0; t<T; t++) {
			int N = Integer.parseInt(br.readLine());
			System.out.println(dp[N]);
		}
	}

}
