import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;

public class Main {
	
	static int N; // 탑의 개수
	
	static class Top {
		int num; // 탑의 번호
		int height; // 탑의 높이
		
		Top(int num, int height) {
			this.num = num;
			this.height = height;
		}
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		N = Integer.parseInt(br.readLine());
		
		Stack<Top> stack = new Stack<>();
		
		StringBuilder sb = new StringBuilder();
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		for(int i=1; i<=N; i++) {
			int height = Integer.parseInt(st.nextToken()); // i번째 탑의 높이
			
			while(!stack.isEmpty()) {
				if(stack.peek().height >= height) { // peek한게 더 크거나 같으면 레이저 수신 가능
					sb.append(stack.peek().num + " ");
					break;
				}
				stack.pop(); // i번째 탑보다 낮은 탑은 어차피 i번째 탑이 push 되면 수신할 일이 없으므로 제거해버린다 (높이가 더 높은 i번째 탑이 수신하기 때문)
			}
			
			// 맨 처음 탑은 수신할 탑이 없으니 0 출력
			// i번째 탑이 더 높으면 stack에서 다 pop하고 수신 가능합 탑이 없으므로 0 출력
			if(stack.isEmpty()) {
				sb.append("0 ");
			}
			
			stack.push(new Top(i, height));
		}
		
		System.out.println(sb);
	}

}