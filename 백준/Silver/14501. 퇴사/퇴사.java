import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	static int N; // 퇴사 전까지 남은 날
	static int[][] arr; // 상담 시간과 금액을 저장할 배열
	static boolean[] visited;
	static int answer = 0; // 최대 이익
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		
		N = Integer.parseInt(br.readLine());
		
		arr = new int[N][2];
		visited = new boolean[N];
		
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			arr[i][0] = Integer.parseInt(st.nextToken());
			arr[i][1] = Integer.parseInt(st.nextToken());
		}
		
		dfs(0, 0);
		
		System.out.println(answer);
	}
	
	// arr의 배열 인덱스(날짜), 금액의 합
	static void dfs(int index, int sum) {
		// 상담은 N일까지만 할 수 있음
		if(index > N) {
			return;
		}
		
		for(int i=index; i<N; i++) {
			visited[i] = true;
			dfs(i + arr[i][0], sum + arr[i][1]);
			visited[i] = false;
		}
		
		answer = Math.max(answer, sum);
	}

}