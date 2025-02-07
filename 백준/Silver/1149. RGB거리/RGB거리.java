import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * <BJ_1149_RGB거리>
 * 주의할 점 -> 그리디가 아님!!
 * 1번 집에서 최소 비용을 선택해 이후로도 계속 최소 비용을 선택한다고 최종적으로 최소 비용이 드는 것은 아님
 * 처음에 더 큰 비용을 지불해도 중간에 적은 비용이 들어 최종적으로 더 최소 비용이 들 수 있음
 * 1번 집을 빨강, 초록, 파랑 모든 색깔로 칠했을 떄를 고려해야 함
 * 
 * 따라서 dp를 2차원 배열로 만들고, n번 집이 R로 칠해졌을 때, G으로 칠해졌을 때, B로 칠해졌을 때의 최소 비용을 저장한다.
 * 그러면 행이 N일 때 각 집이 R/G/B로 칠해졌을 떄의 최소 비용이 나오고, 이 세 개 값의 최솟값을 구하면 전체 최소 비용을 구할 수 있다.
 * 
 * @author YooSejin
 *
 */

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		
		int N = Integer.parseInt(br.readLine()); // 집의 개수
		
		int[][] cost = new int[N+1][3];
		int[][] dp = new int[N+1][3]; // n번째 집이 R/G/B로 끝났을 때의 최소 비용을 저장
		
		for(int i=1; i<=N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0; j<3; j++) {
				cost[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		dp[1][0] = cost[1][0]; // R
		dp[1][1] = cost[1][1]; // G
		dp[1][2] = cost[1][2]; // B
		
		for(int i=2; i<=N; i++) {
			for(int j=0; j<3; j++) {
				dp[i][j] = cost[i][j] + Math.min(dp[i-1][(j+1)%3], dp[i-1][(j+2)%3]);
			}
		}
		
		System.out.println(Math.min(Math.min(dp[N][0], dp[N][1]), dp[N][2]));
	}

}
