import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;

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