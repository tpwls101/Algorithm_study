import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * <BJ_1182_부분수열의합>
 * 부분집합에는 공집합도 포함되어 있음을 유의해야 한다!!
 * 
 * @author YooSejin
 *
 */
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
		
		// 부분집합에는 공집합도 포함되어 있음
		// 따라서 S가 0인 경우에는 공집합(아무것도 뽑지 않는 경우)도 정답으로 카운트하므로 정답을 -1 해줘야 함
		if(S == 0) {
			answer--;
		}
		
		System.out.println(answer);
	}
	
	public static void subset(int cnt) {
		if(cnt == N) {
			int sum = 0;
			for(int i=0; i<N; i++) {
				if(visited[i]) {
					sum += numbers[i];
				}
			}
			if(sum == S) answer++;
			return;
		}
		
		visited[cnt] = true;
		subset(cnt+1);
		visited[cnt] = false;
		subset(cnt+1);
	}

}
