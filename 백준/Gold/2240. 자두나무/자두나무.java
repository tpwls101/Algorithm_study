import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	
	static int T; // 자두는 T초 동안 떨어짐
	static int W; // 자두는 최대 W번만 움직임
	static int[] tree; // t초에 몇 번 나무에서 자두가 떨어지는지 저장
	static int[][] dp; // [시간][이동횟수]
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		T = Integer.parseInt(st.nextToken());
		W = Integer.parseInt(st.nextToken());
		
		tree = new int[T+1];
		
		for(int i=1; i<=T; i++) {
			tree[i] = Integer.parseInt(br.readLine());
		}
		
		dp = new int[T+1][W+1];
		
		int pos = 1; // 처음에 무조건 1번 나무 아래 있음
		
		// dp[t][w] : t초에 w번 이동해서 얻을 수 있는 최대 자두 수
		for(int t=1; t<=T; t++) {
			
			// w = 0인 경우
			// 움직인 적이 한번도 없을 때
			pos = 1; // 움직인 적이 한번도 없으면 위치는 계속 1번 나무 아래
			if(tree[t] == pos) {
				dp[t][0] = dp[t-1][0] + 1;
			} else {
				dp[t][0] = dp[t-1][0];
			}
			
			// 움직인 적이 한번이라도 있을 때
			for(int w=1; w<=W; w++) {
				
				// 현재 내 위치
				if(w % 2 == 0) { // 짝수번 움직이면 1번 나무 아래
					pos = 1;
				} else { // 홀수번 움직이면 2번 나무 아래
					pos = 2;
				}
				
				if(tree[t] == pos) { // t초 후 열매가 떨어지는 위치와 내 위치가 같을 때
					dp[t][w] = Math.max(dp[t-1][w], dp[t-1][w-1]) + 1;
				} else { // t초 후에 열매가 떨어지는 위치에 있지 않을 때
					dp[t][w] = Math.max(dp[t-1][w], dp[t-1][w-1]);
				}
			}
		}
		
		// 확인
//		for(int t=1; t<=T; t++) {
//			for(int w=0; w<=W; w++) {
//				System.out.print(dp[t][w] + " ");
//			}
//			System.out.println();
//		}
//		System.out.println();
		
		int answer = 0;
		for(int w=0; w<=W; w++) {
			answer = Math.max(answer, dp[T][w]);
		}
		System.out.println(answer);
	}

}
