import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * <BJ_15651__N과M_3>
 * 중복 선택이 가능한 순열
 * 방문처리는 이미 선택한 수를 중복 선택하는 걸 방지하기 위해 사용하므로 여기서는 필요없음
 * 
 * 주의 : 출력이 많아 sysout 쓰면 시간초과난다. StringBuilder 사용해야 함.
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
		
        perm(0);
        
        System.out.println(sb);
	}
	
	static void perm(int cnt) {
		if(cnt == M) {
			for(int num : selected) {
				sb.append(num + " ");
			}
			sb.append("\n");
			return;
		}
		
		for(int i=1; i<=N; i++) {
			selected[cnt] = i;
			perm(cnt+1);
		}
	}

}
