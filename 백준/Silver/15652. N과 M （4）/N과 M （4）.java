import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * <BJ_15652_N과M_4>
 * 중복 가능한 조합
 * 뒤에 수가 앞에 수보다 같거나 커야 하므로 조합을 사용해 재귀로 start 매개변수를 보내주면 됨
 * 조합은 방문처리 필요없음
 * 
 * @author YooSejin
 *
 */

public class Main {

	static int N;
	static int M;
	static int[] selected;
	static StringBuilder sb = new StringBuilder();
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        
        selected = new int[M];
        
        comb(0, 1);
        
        System.out.println(sb);
	}
	
	static void comb(int cnt, int start) {
		if(cnt == M) {
			for(int num : selected) {
				sb.append(num + " ");
			}
			sb.append("\n");
			return;
		}
		
		for(int i=start; i<=N; i++) {
			selected[cnt] = i;
			comb(cnt+1, i);
		}
	}

}
