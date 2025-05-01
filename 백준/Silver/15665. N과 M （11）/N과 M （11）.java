import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * <BJ_15665_N과M_11>
 * 중복해서 선택 가능한 순열
 * 
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
		
		int tmp = 0; // 이전 값 (재귀 함수를 호출할 때마다 0으로 초기화 됨. 즉, cnt가 바뀔 때마다.)
		for(int i=0; i<N; i++) {
			int now = number[i];
			
			if(now == tmp) continue;
			
			selected[cnt] = number[i];
			tmp = now;
			perm(cnt+1);
		}
	}

}
