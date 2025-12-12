import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * <BJ_2631_줄세우기> - DP, LIS(최장증가부분수열)
 * 문제 유형을 파악하기 어려운 문제
 * 하지만 문제 유형만 알면 금방 풀 수 있다.
 * 문제에서 요구하는 것은 아이들을 최소로 옮겨야 하는 것이다. (번호 순서대로 세워야 함)
 * 그렇다면 몇 명의 아이들을 이동시키면 될지만 알면 된다.
 * 즉, 번호 순서대로 서있는 아이들(최장증가부분수열)은 그대로 두고 나머지만 이동시키면 된다는 뜻이다.
 * 따라서 전체 아이들의 수에서 최장증가부분수열의 길이를 빼주기만 하면 최소 몇 명을 이동시켜야 하는지가 나온다.
 * 
 * 최장증가부분수열은 DP를 이용한다.
 * N의 크기도 200까지이므로 2중 for문을 돌리면 된다.
 * 
 * @author YooSejin
 *
 */

public class BJ_2631_줄세우기 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int N = Integer.parseInt(br.readLine()); // 아이들의 수
		
		int[] arr = new int[N];
		
		for(int i=0; i<N; i++) {
			arr[i] = Integer.parseInt(br.readLine());
		}
		
		int[] dp = new int[N]; // dp[i] = arr[i]가 마지막인 부분 수열에서 최장증가부분수열의 길이
		
		for(int i=0; i<N; i++) {
			dp[i] = 1; // 초기화
			
			for(int j=0; j<i; j++) {
				if(arr[j] < arr[i]) {
					dp[i] = Math.max(dp[i], dp[j] + 1);
				}
			}
		}
		
		// 최장증가부분수열의 길이 구하기
		int max = Integer.MIN_VALUE;
		for(int i=0; i<N; i++) {
			max = Math.max(max, dp[i]);
		}
		
		System.out.println(N - max); // 최장증가부분수열에 해당하는 사람은 그대로 놔두고 아닌 사람만 자리를 옮기면 됨
	}

}
