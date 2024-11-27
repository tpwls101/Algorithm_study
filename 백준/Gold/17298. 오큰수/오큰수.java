import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;

/**
 * <BJ_17298_오큰수>
 * 최대 100만개를 출력해야 하니 StringBuilder 사용할 것
 * StringBuilder 사용안하면 시간초과남
 * 
 * @author YooSejin
 *
 */

public class Main {
	
	static int N; // 수열의 크기(100만까지)
	static int[] arr; // 수열의 각 원소를 저장할 배열
	static int[] answer;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		N = Integer.parseInt(br.readLine());
		
		arr = new int[N];
		answer = new int[N];
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		for(int i=0; i<N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		
		Stack<Integer> stack = new Stack<>();
		
		for(int i=N-1; i>=0; i--) {
			while(!stack.isEmpty()) {
				if(stack.peek() > arr[i]) {
					answer[i] = stack.peek();
					break;
				}
				stack.pop();
			}
			
			if(stack.isEmpty()) {
				answer[i] = -1;
			}
			
			stack.push(arr[i]);
		}
		
		StringBuilder sb = new StringBuilder();
		for(int i=0; i<N; i++) {
			sb.append(answer[i] + " ");
		}
		
		System.out.println(sb);
	}

}
