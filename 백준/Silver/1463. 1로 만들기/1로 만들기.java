import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	
	static int[] memo; // 메모이제이션
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int num = Integer.parseInt(st.nextToken()); // 입력받은 정수

		memo = new int[1000001];
		Arrays.fill(memo, Integer.MAX_VALUE);
		memo[1] = 0;
		memo[2] = 1;
		memo[3] = 1;
		
		for(int i=4; i<=num; i++) {
			if(i % 3 == 0) {
				memo[i] = Math.min(memo[i], memo[i/3] + 1);
			}
			
			if(i % 2 == 0) {
				memo[i] = Math.min(memo[i], memo[i/2] + 1);
				
			}
			
			memo[i] = Math.min(memo[i], memo[i-1] + 1);
		}
		
		System.out.println(memo[num]);
	}
	
}
