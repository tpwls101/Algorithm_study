import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	static int N; // 정수의 개수
	static int S; // 부분수열의 원소의 합이 S가 되어야 함
	static int[] numbers;
	static boolean visited[];
	static int answer = 0; // 합이 S가 되는 부분수열의 개수
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		S = Integer.parseInt(st.nextToken());
		
		numbers = new int[N];
		visited = new boolean[N];
		
		st = new StringTokenizer(br.readLine());
		for(int i=0; i<N; i++) {
			numbers[i] = Integer.parseInt(st.nextToken());
		}
		
		subset(0);
		
		System.out.println(answer);
	}
	
	public static void subset(int cnt) {
		if(cnt == N) {
			int falseCnt = 0;
			int sum = 0;
			
			for(int i=0; i<N; i++) {
				if(visited[i]) {
					sum += numbers[i];
				} else {
					falseCnt++;
				}
			}
			
			// 모든 숫자가 선택 안된 경우에는 부분수열의 크기가 양수가 아니므로 제외
			if(falseCnt == N) return;
			if(sum == S) answer++;
			return;
		}
		
		visited[cnt] = true;
		subset(cnt+1);
		visited[cnt] = false;
		subset(cnt+1);
	}

}