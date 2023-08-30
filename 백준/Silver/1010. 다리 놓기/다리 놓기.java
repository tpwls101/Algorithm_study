import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * <BJ_1010_다리놓기>
 * mCn 구하기
 * 입력값 N과 M의 범위가 0 < N<=M < 30 이므로 mCn을 팩토리얼로 계산하면 계산량이 많아진다.
 * 따라서 이항계수를 구하는 공식 nCk = n-1Ck-1 + n-1Ck 을 사용한다.
 * => 파스칼의 삼각형과 동적계획법을 적용하여 이항계수 계산!
 *
 * !!! 놓친 부분 !!!
 * for(int j=0; j<Math.min(i, N+1); j++) -> X
 * for(int j=0; j<=Math.min(i, N); j++) -> O
 * 
 * 메모리 : 15144kb, 실행시간 : 172ms
 * 
 * @author SSAFY
 *
 */

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int T = Integer.parseInt(st.nextToken()); // 테스트케이스의 수
		
		for(int test_case=1; test_case<=T; test_case++) {
			st = new StringTokenizer(br.readLine());
			
			int N = Integer.parseInt(st.nextToken()); // 강의 서쪽에 있는 사이트의 개수 (0~30)
			int M = Integer.parseInt(st.nextToken()); // 강의 동쪽에 있는 사이트의 개수 (0~30)
			
			// 파스칼의 삼각형 사용
			// 동적계획법을 적용한 이항계수 계산 => nCk = n-1Ck-1 + n-1Ck
			int[][] dp = new int[M+1][N+1];

			for(int i=0; i<=M; i++) {
				for(int j=0; j<=Math.min(i, N); j++) {
					if(j == 0 || j == i) {
						dp[i][j] = 1;
					} else {
						dp[i][j] = dp[i-1][j-1] + dp[i-1][j];
					}
				}
			}
			
			System.out.println(dp[M][N]);
		}
		
	}

}
