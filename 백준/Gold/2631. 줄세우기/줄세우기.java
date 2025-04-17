import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * <BJ_2631_줄세우기>
 * 풀이 유형을 생각하는게 어려웠음.
 * 어떤 알고리즘을 써야하는지 감이 잘 안잡혔다.
 * 결론은 최장증가부분수열(LIS)을 활용하는 것이었다. -> 이는 DP 사용 -> 시간복잡도는 O(N^2)
 * 최장증가부분수열의 길이를 구하고, N-(길이)만큼만 이동시켜주면 그게 오름차순이 되는 최소 이동 횟수이다..!
 * 
 * @author YooSejin
 *
 */

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int N = Integer.parseInt(br.readLine()); // 아이들의 수
		
		int[] arr = new int[N];
		
		for(int i=0; i<N; i++) {
			arr[i] = Integer.parseInt(br.readLine());
		}
		
		// 최장증가부분수열의 길이 구하기
		// dp[i] : i번째 인덱스로 끝나는 최장증가부분수열의 길이 (0~i번째까지 중 최장증가부분수열의 길이가 아니라, 최장증가부분수열이 i번째 인덱스로 끝나야 함에 주의!)
		int[] dp = new int[N];
		
		for(int i=0; i<N; i++) {
			dp[i] = 1;
			for(int j=0; j<i; j++) {
				if(arr[j] < arr[i]) {
					dp[i] = Math.max(dp[i], dp[j]+1);
				}
			}
		}
		
		int max = 0; // 최장증가부분수열의 길이
		for(int i=0; i<N; i++) {
			max = Math.max(max, dp[i]);
		}
		
		// 최장증가부분수열이 아닌 것만 이동시키면 그게 최소 이동 횟수
		System.out.println(N - max);
	}

}
