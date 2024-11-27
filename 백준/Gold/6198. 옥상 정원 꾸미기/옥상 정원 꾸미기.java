import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

/**
 * <BJ_6198_옥상정원꾸미기>
 * 스택에 남아있다는 건 현재 빌딩을 벤치마킹 할 수 있다는 뜻
 * 
 * N	현재 벤치마킹 할 수 있는 빌딩 수		어떤 빌딩인가(빌딩 높이) 	스택
 * 1	0					x			10
 * 2	1					10			10 3
 * 3	1					10			10 7
 * 4	2					10 7			10 7 4
 * 5	0					x			12
 * 6	1					12			12 2
 * 
 * 따라서 총 벤치마킹 할 수 있는 빌딩 수는 1+1+2+1 = 5
 * 10 3번, 7 1번, 12 1번
 * 
 * !! 주의사항 !!
 * 8만개의 빌딩이 내림차순 정렬되어 있다면
 * 벤치마킹 가능한 빌딩 수는 79999 + 79998 + ... + 1 + 0 = 80000 * 80001 / 2 = 약 32억
 * 따라서 정답은 long 타입이어야 한다.
 * 
 * @author YooSejin
 *
 */

public class Main {
	
	static int N; // 빌딩의 개수
	static long answer = 0; // 벤치마킹 할 수 있는 빌딩 수
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		N = Integer.parseInt(br.readLine());
		
		Stack<Integer> stack = new Stack<>();
		
		for(int i=0; i<N; i++) {
			int height = Integer.parseInt(br.readLine());
			
			// 스택에 있는 빌딩의 높이보다 더 높은 빌딩 차례가 되면 스택에서 제거한다
			while(!stack.isEmpty()) {
				// i번째 빌딩보다 낮거나 같은 애들은 스택에서 빼버린다
				if(stack.peek() <= height) {
					stack.pop();
				} else { // 더 낮은 건물이면 스택 유지, 스택에 있는 건물은 현재까지 벤치마킹이 가능한 것
					break;
				}
			}
			
			answer += stack.size(); //스택 사이즈를 더함으로써 벤치마킹 가능한 개수를 더해준다
			stack.push(height);
		}
		
		System.out.println(answer);
	}

}
