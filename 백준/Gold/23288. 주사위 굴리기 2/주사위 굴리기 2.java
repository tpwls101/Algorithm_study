import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	
	static int N; // 지도의 세로 크기
	static int M; // 지도의 가로 크기
	static int K; // 이동 횟수
	static int[][] arr;
	static int direction = 0; // 이동 방향(디폴트 동쪽)
	static int[] dice = { 2, 4, 1, 3, 5, 6 };
	static int answer = 0; // 각 이동에서 획득하는 점수의 합
	
	// 동남서북
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
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
		
        arr = new int[N][M];
        
        for(int i=0; i<N; i++) {
        	st = new StringTokenizer(br.readLine());
        	for(int j=0; j<M; j++) {
        		arr[i][j] = Integer.parseInt(st.nextToken());
        	}
        }
        
        int x = 0;
        int y = 0;
        
        for(int move=0; move<K; move++) {
        	int nx = x + dx[direction];
        	int ny = y + dy[direction];
        	
        	// 이동 방향에 칸이 없으면 이동 방향을 반대로 바꾸고 한 칸 굴러간다
        	if(!isRange(nx, ny)) {
        		direction = (direction + 2) % 4;
        		nx = x + dx[direction];
        		ny = y + dy[direction];
        	}
        	
        	roll();
        	
        	// 점수 획득
        	answer += getScore(new Node(nx, ny));
        	
        	if(dice[5] > arr[nx][ny]) {
        		direction = (direction + 1) % 4; // 시계 방향으로 90도 회전
        	} else if(dice[5] < arr[nx][ny]) {
        		direction = (direction - 1 + 4) % 4; // 반시계 방향으로 90도 회전
        	}
        	
        	x = nx;
        	y = ny;
        }
        
        System.out.println(answer);
	}
	
	static void roll() {
		switch(direction) {
		case 0: // 동
			int temp = dice[3];
			dice[3] = dice[2];
			dice[2] = dice[1];
			dice[1] = dice[5];
			dice[5] = temp;
			break;
		case 1: // 남
			temp = dice[5];
			dice[5] = dice[4];
			dice[4] = dice[2];
			dice[2] = dice[0];
			dice[0] = temp;
			break;
		case 2: // 서
			temp = dice[1];
			dice[1] = dice[2];
			dice[2] = dice[3];
			dice[3] = dice[5];
			dice[5] = temp;
			break;
		case 3: // 북
			temp = dice[0];
			dice[0] = dice[2];
			dice[2] = dice[4];
			dice[4] = dice[5];
			dice[5] = temp;
			break;
		}
	}
	
	static boolean isRange(int x, int y) {
		if(x >= 0 && x < N && y >= 0 && y < M) {
			return true;
		}
		return false;
	}
	
	static int getScore(Node node) {
		Queue<Node> queue = new ArrayDeque<>();
		boolean[][] visited = new boolean[N][M];
		
		queue.add(node);
		visited[node.x][node.y] = true;
		
		int cnt = 1;
		
		while(!queue.isEmpty()) {
			Node current = queue.poll();
			
			for(int i=0; i<4; i++) {
				int nx = current.x + dx[i];
				int ny = current.y + dy[i];
				
				if(isRange(nx, ny)) {
					if(arr[nx][ny] == arr[node.x][node.y] && !visited[nx][ny]) {
						queue.add(new Node(nx, ny));
						visited[nx][ny] = true;
						cnt++;
					}
				}
			}
		}
		
		return arr[node.x][node.y] * cnt;
	}

}
