import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * <BJ_15649__N과M_1>
 * 순열
 * 
 * @author YooSejin
 *
 */

public class Main {
	
	static int N;
	static int M;
	static boolean[] visited;
	static int[] selected;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        
        visited = new boolean[N+1];
        selected = new int[M];
		
        perm(0);
	}
	
	static void perm(int cnt) {
		if(cnt == M) {
			for(int num : selected) {
				System.out.print(num + " ");
			}
			System.out.println();
			return;
		}
		
		for(int i=1; i<=N; i++) {
			if(visited[i]) continue;
			visited[i] = true;
			selected[cnt] = i;
			perm(cnt+1);
			visited[i] = false;
		}
	}

}
