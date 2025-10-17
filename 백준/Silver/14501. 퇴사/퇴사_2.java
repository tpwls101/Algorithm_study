import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * <BJ_14501_퇴사>
 * N의 범위가 15까지로 작기 때문에 DP말고 DFS를 돌려도 된다.
 * 
 * @author YooSejin
 *
 */

public class Main {
	
	static int N;
	static int[][] arr;
	static int answer = Integer.MIN_VALUE; // 최대 수익
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		
		N = Integer.parseInt(br.readLine());
		
		arr = new int[N][2];
		
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			arr[i][0] = Integer.parseInt(st.nextToken());
			arr[i][1] = Integer.parseInt(st.nextToken());
		}
		
		dfs(0, 0);
		
		System.out.println(answer);
	}
	
	static void dfs(int idx, int sum) {
		if(idx > N) return; // N일을 넘어서는 상담을 할 수 없음. 하지만 마지막 N일째까지는 할 수 있으므로 N보다 큰 경우에 리턴.
		
		for(int i=idx; i<N; i++) {
			dfs(i + arr[i][0], sum + arr[i][1]);
		}
		
		answer = Math.max(answer, sum);
	}

}
