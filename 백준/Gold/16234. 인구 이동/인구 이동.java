import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * <BJ_16234_인구이동>
 * 인구이동이 더 가능한가를 어떻게 확인할지?
 * while문 안에 boolean 타입 변수(isPossible)를 하나 사용하고, list에 인구 이동이 가능한 국가를 담는다.
 * 인구이동이 가능한 국가가 2개 이상은 있어야 인구이동이 일어나므로
 * list의 크기를 확인해 2보다 크면 인구 이동을 발생시키고, isPossible = true로 바꾼다.
 * while문을 돌 때마다 isPossible을 false로 초기화해주기 때문에
 * 모든 국가를 돌며 더 이상 인구이동을 할 수 없으면 isPossible이 false가 되어 while문을 벗어나고
 * 인구 이동이 며칠 동안 일어나는지를 출력한다.
 * 
 * @author YooSejin
 *
 */

public class Main {
	
	static int N; // NxN 크기의 땅
	static int L; // 두 나라의 인구 차이는 L명 이상
	static int R; // 두 나라의 인구 차이는 R명 이하
	static int[][] arr;
	static boolean[][] visited;
	static List<Node> list; // list에 인구이동이 가능한 국가 저장
	static int sum = 0; // 연합의 인구수 총합
	static int answer = 0; // 인구 이동이 발생하는 날짜 수
	
	static int[] dx = { 0, 1, 0, -1 };
	static int[] dy = { 1, 0, -1, 0 };
	
	static class Node {
		int x;
		int y;
		
		Node(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		L = Integer.parseInt(st.nextToken());
		R = Integer.parseInt(st.nextToken());
		
		arr = new int[N][N];
		
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0; j<N; j++) {
				arr[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		while(true) {
			visited = new boolean[N][N];
			
			boolean isPossible = false; // 인구이동이 가능한가
			
			for(int i=0; i<N; i++) {
				for(int j=0; j<N; j++) {
					if(!visited[i][j]) {
						bfs(new Node(i, j));
						if(list.size() >= 2) { // 인구이동할 국가가 2개 이상 있어야 인구 이동
							move(); // 인구 이동
							isPossible = true;
						}
					}
				}
			}
			
			if(!isPossible) { // 더 이상 인구이동을 할 수없으면 결과 리턴
				break;
			}
			
			answer++;
		}
		
		System.out.println(answer);
	}
	
	static void bfs(Node node) {
		Queue<Node> queue = new ArrayDeque<>();
		list = new ArrayList<>(); // list에 인구이동이 가능한 국가 추가
		
		queue.add(node);
		list.add(node);
		visited[node.x][node.y] = true;
		
		sum = arr[node.x][node.y]; // 연합의 인구수
		
		while(!queue.isEmpty()) {
			Node current = queue.poll();
			
			for(int i=0; i<4; i++) {
				int nx = current.x + dx[i];
				int ny = current.y + dy[i];
				
				if(nx >= 0 && nx < N && ny >= 0 && ny < N) {
					int diff = Math.abs(arr[current.x][current.y] - arr[nx][ny]); // 두 나라의 인구 차이
					if(diff >= L && diff <= R && !visited[nx][ny]) {
						queue.add(new Node(nx, ny));
						list.add(new Node(nx, ny));
						visited[nx][ny] = true;
						sum += arr[nx][ny];
					}
				}
			}
		}
	}
	
	static void move() {
		int avg = sum / list.size();
		for(Node node : list) {
			arr[node.x][node.y] = avg;
		}
	}

}