import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.HashMap;
import java.util.Map;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * <BJ_16928_뱀과사다리게임>
 * 처음에는 dfs로 풀어야하나? 생각했는데 범위가 100까지라 시간초과 날 것 같았음.
 * 그래서 bfs를 활용하기로 함. 그리고 최소 횟수를 구해야하니 bfs를 사용하면 적합할 것 같음. (최소 이동 횟수 -> 즉, 최단거리!!)
 * 큐에 가능한 위치(num)와 주사위 굴린 횟수를 넣고 map을 이용해 사다리나 뱀을 만나면 처리해줌.
 * 그리고 방문처리 해야 함.
 * 왜냐하면 1에서 +6해서 7을 갔을 경우랑 1에서 거쳐거쳐 7을 갔을 경우랑 비교하면 이미 최소 주사위 수가 아니기에 큐에 추가할 필요가 없음.
 * 
 * @author YooSejin
 *
 */

public class Main {
	
	static int N; // 사다리의 수
	static int M; // 뱀의 수
	static Map<Integer, Integer> map;
	static boolean[] visited = new boolean[101]; // 0번째 인덱스는 사용x
	static int answer = 0; // 주사위를 굴려야하는 최소 횟수
	
	static class Node {
		int num; // 칸 번호
		int cnt; // 주사위 굴린 횟수
		
		Node(int num, int cnt) {
			this.num = num;
			this.cnt = cnt;
		}
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		map = new HashMap<>();
		
		for(int i=0; i<N+M; i++) {
			st = new StringTokenizer(br.readLine());
			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());
			map.put(x, y);
		}
		
		bfs(new Node(1, 0));
		
		System.out.println(answer);
	}
	
	static void bfs(Node node) {
		Queue<Node> queue = new ArrayDeque<>();
		queue.add(node);
		visited[node.num] = true;
		
		while(!queue.isEmpty()) {
			Node current = queue.poll();
			
			for(int i=6; i>0; i--) {
				int next = current.num + i;
				
				if(next == 100) {
					answer = current.cnt + 1;
					return;
				}
				
				if(map.containsKey(next)) {
					visited[next] = true;
					next = map.get(next);
				}
				
				if(next <= 100 && !visited[next]) {
					queue.add(new Node(next, current.cnt + 1));
					visited[next] = true;
				}
			}
		}
	}

}
