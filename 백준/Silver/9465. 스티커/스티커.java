import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * <BJ_9465_스티커>
 * 먼저 그리디는 아님. 점수가 큰 스티커부터 떼어내는 것보다 다른 두개의 스티커를 떼는게 점수가 더 클 수 있음.
 * 완탐을 돌리기엔 입력 범위가 10만으로 너무 큼.
 * 따라서 DP로 풀어야겠다고 판단. (부분 구조 만족)
 * 
 * dp[i][j] : arr[i][j] 스티커를 반드시 뗄 때의 최대 점수
 * 다만 위-아래-위-... 이런식으로 지그재그로만 점수를 얻을 수 있는게 아니라
 * 아래-x-위-... 이런식으로 점수를 획득하는게 더 최대 점수일 수도 있기에 둘 중 최대로 점수를 갱신해야 한다.
 * 
 * @author YooSejin
 *
 */

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		
		int T = Integer.parseInt(br.readLine());
		
		int[][] arr;
		int[][] dp;
		StringBuilder sb = new StringBuilder();
		
		for(int t=0; t<T; t++) {
			int N = Integer.parseInt(br.readLine());
			
			arr = new int[2][N];
			dp = new int[2][N];
			
			for(int i=0; i<2; i++) {
				st = new StringTokenizer(br.readLine());
				for(int j=0; j<N; j++) {
					arr[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			
			// dp[i][j] : arr[i][j] 스티커를 반드시 뗄 때의 최대 점수
			dp[0][0] = arr[0][0];
			dp[1][0] = arr[1][0];
			
			if(N > 1) { // N이 1일 수도 있으므로 예외 처리 필수!
				dp[0][1] = dp[1][0] + arr[0][1];
				dp[1][1] = dp[0][0] + arr[1][1];
			}
			
			for(int i=2; i<N; i++) {
				for(int j=0; j<2; j++) {
					dp[j][i] = Math.max(dp[(j+1)%2][i-1], dp[(j+1)%2][i-2]) + arr[j][i];
				}
			}
			
			sb.append(Math.max(dp[0][N-1], dp[1][N-1]) + "\n");
		}
		
		System.out.println(sb);
	}

}
