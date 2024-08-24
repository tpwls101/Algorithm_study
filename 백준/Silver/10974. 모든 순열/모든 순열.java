import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

	static int N;
	static int[] numbers;
	static boolean[] visited;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		N = Integer.parseInt(br.readLine());
		numbers = new int[N];
		visited = new boolean[N+1];
		
		perm(0);
	}
	
	public static void perm(int cnt) {
		if(cnt == N) {
			for(int n : numbers) {
				System.out.print(n + " ");
			}
			System.out.println();
			return;
		}
		
		for(int i=1; i<=N; i++) {
			if(visited[i]) continue;
			numbers[cnt] = i;
			visited[i] = true;
			perm(cnt+1);
			visited[i] = false;
		}
	}

}