import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * <BJ_2240_자두나무>
 * DP문제는 조금만 복잡해져도 아직 어렵다....
 * 
 * [시간][이동횟수][현재위치(1 or 2)] 이렇게 3차원 배열로 해도 되지만
 * 이 문제는 나무가 1번, 2번 두 개밖에 없기 때문에 이동을 몇 번 했느냐에 따라 위치가 정해진다.
 * 한번도 이동하지 않았다면(w=0) 1번 나무 아래에 있게 되고, 한번 이동했으면(w=1) 2번 나무 아래에 있게 된다.
 * 그리고 한번 더 이동하면(w=2) 다시 1번 나무 아래에 있게 된다.
 * 따라서 짝수번 이동하면 1번 나무 아래에, 홀수번 이동하면 2번 나무 아래에 있는 것을 알 수 있기 때문에 2차원 배열로 풀 수가 있다.
 * 
 * dp[t][w] : t초에 w번 이동해서 얻을 수 있는 최대 자두 수
 * 
 * t초 후 w번 이동했을 때
 * 1. 열매가 떨어지는 위치에 있다면
 * 		- 가만히 있었을 때 : dp[t-1][w] + 1 (1초 전에랑 이동횟수가 똑같고 열매를 얻으므로 +1)
 * 		- 이동했을 때 : dp[t-1][w-1] + 1 (1초 전에는 w-1번 이동했었고, 한번 더 이동하여 열매를 얻음)
 * 		-> 둘 중 최대값으로 저장
 * 2. 현재 위치와 열매가 떨어지는 위치가 다르다면
 * 		- 가만히 있었을 때 : dp[t-1][w] (1초 전이랑 이동횟수가 똑같고 얻는 열매는 없음)
 * 		- 이동했을 때 : dp[t-1][w-1] (1초 전에는 w-1번 이동했었고, 한번 더 이동했지만 열매는 못얻음)
 * 		-> 둘 중 최대값으로 저장
 * 
 * 이후 T초일 때 for문 돌려서 w번 움직여서 얻은 자두 중 최대값을 구하면 된다.
 * 
 * @author YooSejin
 *
 */

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
		
		int answer = 0;
		for(int w=0; w<=W; w++) {
			answer = Math.max(answer, dp[T][w]);
		}
		System.out.println(answer);
	}

}
