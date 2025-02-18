import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * <BJ_11055_가장큰증가하는부분수열>
 * dp[i] : arr[i]를 포함하는 증가 부분 수열의 합의 최댓값
 * 
 * 이중 for문을 돌리면서 최댓값으로 매번 갱신해줘야 한다.
 * 만약 1 2 55 50 60 순서로 수열이 주어진다면
 * 60을 기준으로 봤을 때 이전 dp값이 최대라는 보장이 없다.
 * 1+2+50+60보다 1+2+55+60이 더 크기 때문에 이중으로 for문을 돌면서 최대로 dp[i]값을 갱신해야 한다.
 * 따라서 dp[i] = Math.max(dp[j] + arr[i], dp[i]) 점화식이 나온다.
 * 단, 이중 for문을 도는 과정에서 기준점보다 값이 작을 때에만 갱신해준다.
 * 
 * 이중 for문과 이 점화식을 사용하면, 기준점이 이전 값보다 작을 때에도 해결이 된다!
 * 
 * @author YooSejin
 *
 */

public class Main {
	
	static int N;
	static int[] arr;
	static int[] dp;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		
		N = Integer.parseInt(br.readLine());
		
		arr = new int[N];
		dp = new int[N];
		
		st = new StringTokenizer(br.readLine());
		for(int i=0; i<N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		
		dp[0] = arr[0];
		
		// dp[i] : arr[i]를 포함하는 증가 부분 수열의 합의 최댓값
		for(int i=1; i<N; i++) {
			dp[i] = arr[i]; // i번째 수 이전에 더 작은 수가 없을 수도 있으므로 dp[i] 미리 초기화
			for(int j=0; j<i; j++) {
				if(arr[j] < arr[i]) { // 증가 수열을 만족해야 하므로 기준점보다 작은 수만 갱신
					dp[i] = Math.max(dp[j] + arr[i], dp[i]);
				}
			}
		}
		
		// 첫 풀이 : 이렇게 풀면 증가 수열의 합의 최댓값으로 갱신이 안되고, 코드가 심플하지 못함
		// 위의 풀이를 사용하면 기준점보다 이전값이 클 때도 한번에 해결할 수 있음
//		for(int i=1; i<N; i++) {
//			if(arr[i] > arr[i-1]) {
//				dp[i] = dp[i-1] + arr[i]; // 여기서도 이중 for문으로 max값 갱신해야 함 (for문이 두 번 쓰임. 코드 작성 효율 떨어짐)
//			} else {
//				dp[i] = arr[i]; // i번째 수 이전에 더 작은 수가 없을 수도 있음
//				for(int j=i-1; j>=0; j--) {
//					if(arr[i] > arr[j]) {
//						dp[i] = dp[j] + arr[i];
//						break;
//					}
//				}
//			}
//		}
		
		int max = 0;
		for(int i=0; i<N; i++) {
			max = Math.max(max, dp[i]);
		}
		System.out.println(max);
	}

}
