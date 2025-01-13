import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

/**
 * <BJ_12101_123더하기2>
 * i를 만드는 경우의 수를 직접 써보면 규칙을 더 쉽게 찾을 수 있다!
 * 
 * 4를 만드는 경우는
 * 1 + (3을 만드는 경우)
 * 2 + (2를 만드는 경우)
 * 3 + (1을 만드는 경우)
 * 
 * 따라서 ArrayList<String> 자료형 dp에 i를 만드는 경우의 수를 저장해주고
 * dp[i]는 1 + dp[i-1]의 경우를 add 해주고, 2 + dp[i-2], 3 + dp[i-3]도 똑같이 해주면 된다.
 * 
 * <주의할 점>
 * 처음에 dp의 크기를 N+1로 초기화했는데 만약 N이 1,2,3이 나온다면 인덱스 에러가 난다.
 * N의 크기가 11보다 작으므로 그냥 크기를 11로 초기화해준다.
 * 또는 N+3으로 초기화 해도 된다. (1,2,3의 공간 보장)
 * 
 * @author YooSejin
 *
 */

public class Main {
	
	static int N; // 1, 2, 3의 합으로 N 만들기
	static int K; // 경우의 수를 정렬해서 K번째 식 출력하기
	static List<String>[] dp;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		
		dp = new ArrayList[11];
		
		// ArrayList 초기화
		for(int i=0; i<11; i++) {
			dp[i] = new ArrayList<>();
		}
		
		// n=1,2,3일 때 n을 만드는 경우의 수식을 ArrayList에 넣어주기
		dp[1].add("1");
		dp[2].add("1+1");
		dp[2].add("2");
		dp[3].add("1+1+1");
		dp[3].add("1+2");
		dp[3].add("2+1");
		dp[3].add("3");
		
		// dp[N] 구하기
		for(int i=4; i<=N; i++) {
			for(int j=1; j<=3; j++) {
				for(String op : dp[i-j]) {
					dp[i].add(j + "+" + op);
				}
			}
		}
		
		Collections.sort(dp[N]); // 사전순으로 정렬
		
		if(dp[N].size() < K) {
			System.out.println(-1);
		} else {
			System.out.println(dp[N].get(K-1));
		}
	}

}
