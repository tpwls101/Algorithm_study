import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * <BJ_9095_123더하기>
 * n은 11보다 작은 양수 -> dfs+백트래킹으로 가능
 * 범위가 커지면 DP로 풀어야 함
 * 
 * @author YooSejin
 *
 */

public class Main {
	
	static int T; // 테스트케이스 수
	static int N; // 1, 2, 3을 더해서 N 만들기 (11보다 작음)
	static int[] arr = { 1, 2, 3 };
	static int answer;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		T = Integer.parseInt(br.readLine());
		
		for(int i=0; i<T; i++) {
			N = Integer.parseInt(br.readLine());
			answer = 0;
			dfs(0);
			System.out.println(answer);
		}
	}
	
	static void dfs(int sum) {
		if(sum == N) {
			answer++;
			return;
		}
		
		for(int i=0; i<arr.length; i++) {
			if(sum + arr[i] > N) break;
			dfs(sum + arr[i]);
		}
	}

}
