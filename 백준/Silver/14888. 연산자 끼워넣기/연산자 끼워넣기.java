import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;

/**
 * <BJ_14888_연산자끼워넣기>
 * 순열 문제!!
 * 
 * @author YooSejin
 *
 */

public class Main {

	static int N; // 수의 개수
	static int[] num; // 수를 저장할 배열
	static int[] operator; // 연산자를 저장할 배열 : 0(+) 1(-) 2(x) 3(/)
	static int[] arr; // 순열 경우의 수를 저장할 배열
	static boolean[] visited;
	static int max = Integer.MIN_VALUE;
	static int min = Integer.MAX_VALUE;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		
		N = Integer.parseInt(br.readLine());
		
		num = new int[N];
		operator = new int[N-1];
		arr = new int[N-1];
		visited = new boolean[N-1];
		
		st = new StringTokenizer(br.readLine());
		for(int i=0; i<N; i++) {
			num[i] = Integer.parseInt(st.nextToken());
		}
		
		st = new StringTokenizer(br.readLine());
		List<Integer> list = new ArrayList<>();
		for(int i=0; i<4; i++) {
			int cnt = Integer.parseInt(st.nextToken());
			for(int j=0; j<cnt; j++) {
				list.add(i);
			}
		}
		
		for(int i=0; i<N-1; i++) {
			operator[i] = list.get(i);
		}
		
		perm(0);
		
		System.out.println(max);
		System.out.println(min);
	}
	
	static void perm(int cnt) {
		if(cnt == N-1) {
			// 연산 수행
			int result = num[0];
			for(int i=1; i<N; i++) {
				int oper = arr[i-1];
				switch(oper) {
				case 0:
					result += num[i];
					break;
				case 1:
					result -= num[i];
					break;
				case 2:
					result *= num[i];
					break;
				case 3:
					// 음수를 양수로 나누는 경우 : 양수로 바꾼 뒤 몫을 구하고, 그 몫을 음수로 바꾼다.
					if(result < 0) {
						result = Math.abs(result);
						result /= num[i];
						result -= result*2;
						break;
					} else {
						result /= num[i];
						break;
					}
				}
			}
			
			max = Math.max(max, result);
			min = Math.min(min, result);
			
			return;
		}
		
		for(int i=0; i<N-1; i++) {
			if(visited[i]) continue;
			arr[cnt] = operator[i];
			visited[i] = true;
			perm(cnt+1);
			visited[i] = false;
		}
	}

}