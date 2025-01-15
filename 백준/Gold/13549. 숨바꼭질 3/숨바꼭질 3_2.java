import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * <BJ_13549_숨바꼭질3> - ArrayDeque를 사용한 방식
 * 최단거리 문제! -> BFS
 * 가중치가 0 또는 1 -> 0-1 BFS
 * 
 * visited 처리를 안하면 메모리 초과난다.
 * x2, -1, +1 순서로 큐에 넣을 때 (이 때, 이 순서도 중요함)
 * 이미 방문한 곳이면 더 빠른 시간에 해당 위치를 방문한 것이기에 더 늦게 방문한 것을 큐에 넣을 필요가 없다.
 * 
 * <주의사항>
 * 연산 순서가 중요하다.
 * +1/-1보다 x2가 비용이 적게 들기 때문에 x2를 가장 먼저 해줘야 한다.
 * 그리고 +1 -1 이 아니라 -1 +1 순서로 처리해야 한다.
 * 왜냐하면 전자의 경우 -1x2 보다 +1+1이 먼저 도착해서 visited 처리를 하고 뒤따라오는 최단경로를 차단해버리기 때문이다.
 * +1+1보다 비용이 적게 드는 -1x2가 적합한 최단경로이므로 -1 +1 순서로 처리해야 한다.
 * 
 * (0-1 BFS를 쓸 때는 최단경로가 반드시 제일 먼저 도착한다는 이론적 뒷받침이 없다고 한다.
 * 따라서 다익스트라에서는 이미 왔었던 정점이면 continue해도 되지만
 * 0-1 BFS에서는 한번 도착했던 정점이라도 바로 차단하지 말고 거리가 줄어들면 또 갱신하여 큐에 들어가도록 해야 한다.)
 * 
 * @author YooSejin
 *
 */

public class Main {
	
	static int N; // 수빈이가 있는 위치
	static int K; // 동생이 있는 위치
	static int answer = Integer.MAX_VALUE; // 수빈이가 동생을 찾는 가장 빠른 시간
	
	static class Node {
		int pos;
		int time;
		
		Node(int pos, int time) {
			this.pos = pos;
			this.time = time;
		}
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		
		bfs(new Node(N, 0));
		
		System.out.println(answer);
	}
	
	static void bfs(Node node) {
		ArrayDeque<Node> queue = new ArrayDeque<>();
		boolean[] visited = new boolean[100001];
		
		queue.add(node);
		visited[node.pos] = true;
		
		while(!queue.isEmpty()) {
			Node current = queue.poll();
			
			if(current.pos == K) {
//				answer = Math.min(answer, current.time);
				answer = current.time; // deque를 사용하면 가중치가 작은 경우를 앞으로 삽입하기 때문에 갱신 안해도 바로 정답이 나옴
				break;
			}
			
			// x2 -1 +1 순서
			// 가중치가 0인 경우 deque의 맨 앞에 삽입해 먼저 처리한다.
			if(current.pos * 2 <= 100000 && !visited[current.pos * 2]) {
				queue.addFirst(new Node(current.pos * 2, current.time));
				visited[current.pos * 2] = true;
			}
			
			if(current.pos - 1 >= 0 && !visited[current.pos - 1]) {
				queue.add(new Node(current.pos - 1, current.time + 1));
				visited[current.pos - 1] = true;
			}
			
			if(current.pos + 1 <= 100000 && !visited[current.pos + 1]) {
				queue.add(new Node(current.pos + 1, current.time + 1));
				visited[current.pos + 1] = true;
			}
		}
	}

}
