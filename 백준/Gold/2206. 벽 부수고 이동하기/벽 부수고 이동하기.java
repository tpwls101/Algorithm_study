import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * <BJ_2206_벽부수고이동하기>
 * 방문 배열을 2차원으로 하면 실패! 반례가 존재한다!
 * 3차원 배열 사용해서 벽을 부쉈을 때와 안부쉈을 때로 나눠서 확인해야 한다.
 * 왜냐하면 벽을 부수지 않았을 때 거리가 더 짧은데, 이미 벽을 부숴서 방문해버리면 벽을 부수지 않은 길에서 방문할 수 없기 때문이다.
 * 
 * !! 3차원 방분 배열 처리에 주의할 것 !!
 * (N,M)에 도착하면 최단 거리 찾고 종료해야 되는데 return 안써서 시간 버림 ㅜㅜ
 * 
 * @author YooSejin
 *
 */

public class Main {
	
	static int N;
	static int M;
	static int[][] arr;
	static boolean[][][] visited;
	static int answer = -1; // 최단 거리
	
	static int[] dx = { 0, 1, 0, -1 };
	static int[] dy = { 1, 0, -1, 0 };
	
	static class Node {
		int x;
		int y;
		int brokenWall; // 지금까지 부순 벽의 개수
		int distance; // 현재 좌표까지의 거리
		
		Node(int x, int y, int brokenWall, int distance) {
			this.x = x;
			this.y = y;
			this.brokenWall = brokenWall;
			this.distance = distance;
		}
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		arr = new int[N][M];
		visited = new boolean[N][M][2]; // 벽을 안부쉈을 때와 부쉈을 때 나눠서 저장
		
		for(int i=0; i<N; i++) {
			String[] sArr = br.readLine().split("");
			for(int j=0; j<M; j++) {
				arr[i][j] = Integer.parseInt(sArr[j]);
			}
		}
		
		bfs(new Node(0, 0, 0, 1));
		
		System.out.println(answer);
	}
	
	static void bfs(Node node) {
		Queue<Node> queue = new ArrayDeque<>();
		queue.add(node);
		visited[node.x][node.y][0] = true;
		
		while(!queue.isEmpty()) {
			Node current = queue.poll();
			
			if(current.x == N-1 && current.y == M-1) {
				answer = current.distance;
				return; // ;;; 정답 찾았으면 종료해야 되는데 return 안써서 시간 버림 ㅜㅜ
			}
			
			for(int i=0; i<4; i++) {
				int nx = current.x + dx[i];
				int ny = current.y + dy[i];
				
				if(nx >= 0 && nx < N && ny >= 0 && ny < M) {
					if(arr[nx][ny] == 1) { // 다음 칸이 벽일 때
						if(current.brokenWall == 0 && !visited[nx][ny][1]) { // 현재 부순 벽이 아직 없다면
							queue.add(new Node(nx, ny, current.brokenWall+1, current.distance+1));
							visited[nx][ny][1] = true;
						}
					} else { // 다음 칸이 빈 칸일 때
						// 현재 부순 벽이 있냐 없냐로 구분해서 방문 배열 처리
						if(current.brokenWall == 0 && !visited[nx][ny][0]) { // 아직 부순 벽이 없으면 -> visited[][][0] 확인
							queue.add(new Node(nx, ny, current.brokenWall, current.distance+1));
							visited[nx][ny][0] = true;
						} else if(current.brokenWall == 1 && !visited[nx][ny][1]) { // 부순 벽이 있으면 -> visited[][][1] 확인
							queue.add(new Node(nx, ny, current.brokenWall, current.distance+1));
							visited[nx][ny][1] = true;
						}
					}
				}
				
//				if(nx >= 0 && nx < N && ny >= 0 && ny < M) {
//					if(current.brokenWall == 1) { // 벽을 부순적이 있다면
//						if(arr[nx][ny] == 0 && !visited[nx][ny][1]) { // 빈 칸만 지나갈 수 있음
//							queue.add(new Node(nx, ny, current.brokenWall, current.distance+1));
//							visited[nx][ny][1] = true;
//						}
//					} else if(current.brokenWall == 0) { // 벽을 부순적이 없다면
//						if(arr[nx][ny] == 1) { // 다음 벽이 벽이라면
//							if(!visited[nx][ny][1]) {
//								queue.add(new Node(nx, ny, current.brokenWall+1, current.distance+1));
//								visited[nx][ny][1] = true;
//							}
//						} else { // 다음 벽이 빈 칸이라면
//							if(!visited[nx][ny][0]) {
//								queue.add(new Node(nx, ny, current.brokenWall, current.distance+1));
//								visited[nx][ny][0] = true;
//							}
//						}
//					}
//				}
			}
		}
	}

}