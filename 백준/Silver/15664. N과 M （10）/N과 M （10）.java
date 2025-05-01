import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * <BJ_15664_N과M_10>
 * 조합
 * 중복 가능한 조합은 아니지만 같은 수가 주어져 중복이 가능한 것처럼 보일 수 있다.
 * 현재 숫자가 이전 숫자와 같으면 중복되는 수열이므로 continue
 * 자릿수가 바뀔 떄마다(cnt가 바뀔 때마다) 새롭게 재귀 함수(perm)를 호출해 tmp가 0으로 초기화된다.
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
		
        comb(0, 0);
        
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
		
		int tmp = 0;
		for(int i=start; i<N; i++) {
			int now = number[i];
			
			if(now == tmp) continue;
			
			selected[cnt] = number[i];
			tmp = now;
			comb(cnt+1, i+1);
		}
	}

}
