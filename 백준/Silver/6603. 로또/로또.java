import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * <BJ_6603_로또>
 * 조합
 * k개 중 6개를 뽑기만 하면 된다.
 * 
 * @author YooSejin
 *
 */
public class Main {
	
	static int k = -1;
	static int[] S;
	static int[] numbers = new int[6]; // 내가 선택한 수를 저장할 배열
	static boolean[] visited;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		
		while(k != 0) {
			st = new StringTokenizer(br.readLine());
			k = Integer.parseInt(st.nextToken());
			
			S = new int[k];
			visited = new boolean[k];
			
			for(int i=0; i<k; i++) {
				S[i] = Integer.parseInt(st.nextToken());
			}
			
			comb(0, 0);
			System.out.println();
		}
	}
	
	public static void comb(int start, int cnt) {
		if(cnt == 6) {
			for(int n : numbers) {
				System.out.print(n + " ");
			}
			System.out.println();
			return;
		}
		
		for(int i=start; i<k; i++) {
			numbers[cnt] = S[i];
			visited[i] = true;
			comb(i+1, cnt+1);
		}
	}

}