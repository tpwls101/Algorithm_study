import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * <BJ_15656_N과M_7>
 * 중복순열
 * 
 * @author YooSejin
 *
 */

public class Main {
	
	static int N;
	static int M;
	static int[] number;
	static int[] selected;
	static StringBuilder sb = new StringBuilder();
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        
        number = new int[N];
        selected = new int[M];
        
        st = new StringTokenizer(br.readLine());
        for(int i=0; i<N; i++) {
        	number[i] = Integer.parseInt(st.nextToken());
        }
        Arrays.sort(number);
		
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
		
		for(int i=0; i<N; i++) {
			selected[cnt] = number[i];
			perm(cnt+1);
		}
	}

}
