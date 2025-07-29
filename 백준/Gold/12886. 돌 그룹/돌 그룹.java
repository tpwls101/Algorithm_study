import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.HashSet;
import java.util.Queue;
import java.util.Set;
import java.util.StringTokenizer;

/**
 * <BJ_12886_돌그룹>
 * BFS문제
 * Stone 객체에 A,B,C 각 돌의 개수를 담아 큐에 넣고 가능한 모든 경우를 확인해 다시 큐에 넣는 것까지 맞게 접근했음.
 * 이 때 중복 처리를 어떻게 할 것이냐 -> 2차원 배열을 사용하는 경우도 있었지만 나는 Set<String>을 사용했음.
 * 		- 2차원 배열을 사용하는 경우에는 a와 b, 두개만 알아도 합이 일정하기에 c까지 알 수 있어서 2차원 배열 사용
 * 여기서 한가지 포인트는 가지치기가 가능하다는 것 (처음에 생각하지 못했음)
 * 합이 3으로 나눠떨어지지 않으면 어떻게 해도 같은 개수로 만들 수 없다.
 * 
 * @author YooSejin
 *
 */

public class Main {
	
	static class Stone {
		int a, b, c;
		
		Stone(int a, int b, int c) {
			this.a = a;
			this.b = b;
			this.c = c;
		}
		
		@Override
		public String toString() {
			return a + " " + b + " " + c;
		}
	}

	public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        int A = Integer.parseInt(st.nextToken());
        int B = Integer.parseInt(st.nextToken());
        int C = Integer.parseInt(st.nextToken());
		
        System.out.println(bfs(new Stone(A, B, C)) ? 1 : 0);
	}
	
	static boolean bfs(Stone stone) {
		Queue<Stone> queue = new ArrayDeque<>();
		Set<String> set = new HashSet<>(); // 방문처리 역할
		
		queue.add(stone);
		set.add(stone.toString());
		
		int sum = stone.a + stone.b + stone.c;
		if(sum % 3 != 0) return false; // 합이 3으로 나눠떨어지지 않으면 뭘해도 돌을 같은 개수로 만들 수 없다.
		
		while(!queue.isEmpty()) {
			Stone current = queue.poll();
			
			int a = current.a;
			int b = current.b;
			int c = current.c;
			
			if(a == b && b == c) return true;
			
			// 크기가 같지 않은 두 그룹을 골라야 함
			if(a != b) {
				int na = (a < b) ? a+a : a-b;
				int nb = (a < b) ? b-a : b+b;
				
				String visit = new Stone(na, nb, c).toString();
				if(!set.contains(visit)) {
					queue.add(new Stone(na, nb, c));
					set.add(visit);
				}
			}
			
			if(a != c) {
				int na = (a < c) ? a+a : a-c;
				int nc = (a < c) ? c-a : c+c;
				
				String visit = new Stone(na, b, nc).toString();
				if(!set.contains(visit)) {
					queue.add(new Stone(na, b, nc));
					set.add(visit);
				}
			}
			
			if(current.b != current.c) {
				int nb = (b < c) ? b+b : b-c;
				int nc = (b < c) ? c-b : c+c;
				
				String visit = new Stone(a, nb, nc).toString();
				if(!set.contains(visit)) {
					queue.add(new Stone(a, nb, nc));
					set.add(visit);
				}
			}
		}
		
		return false;
	}

}
