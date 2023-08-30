import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * <>
 * mCn 구하기
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
			
//			for(int i=0; i<M+1; i++) {
//				for(int j=0; j<Math.min(i, N+1); j++) {
//					
//					if(j == 0 || j == i) {
//						dp[i][j] = 1;
//					} else {
//						dp[i][j] = dp[i-1][j-1] + dp[i-1][j];
//					}
//				}
//			}
			
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