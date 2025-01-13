import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
	
	static int N; // 1, 2, 3의 합으로 N 만들기
	static int K; // 경우의 수를 정렬해서 K번째 식 출력하기
	static int[] arr = { 1, 2, 3 };
	static int cnt = 0;
	static String answer = "";
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		
		dfs("", 0);
		
		if(answer.equals("")) {
			System.out.println(-1);
		} else {
			System.out.println(answer);
		}
	}
	
	static void dfs(String operation, int sum) {
		if(sum == N) {
			cnt++;
			if(cnt == K) {
				// '+' 기호는 예약어로 사용되기 때문에 인식하기 위해 \\를 붙여줘야 한다.
				answer = operation.replaceFirst("\\+", "");
			}
			return;
		}
		
		for(int i=0; i<arr.length; i++) {
			if(sum + arr[i] > N) break;
			dfs(operation + "+" + arr[i], sum + arr[i]);
		}
	}

}
