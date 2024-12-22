import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	
	static int N; // 2^N x 2^N 크기의 격자
	static int Q; // 파이어스톰 시전 횟수
	static int len; // 격자의 한 변의 길이
	static int[][] ice; // 얼음의 양을 저장한 배열
	static int sum = 0; // 남아있는 얼음의 합
	static int max = 0; // 가장 큰 덩어리가 차지하는 칸의 개수
	
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
        Q = Integer.parseInt(st.nextToken());
		
        len = (int)Math.pow(2, N);
        ice = new int[len][len];
        
        for(int i=0; i<len; i++) {
        	st = new StringTokenizer(br.readLine());
        	for(int j=0; j<len; j++) {
        		ice[i][j] = Integer.parseInt(st.nextToken());
        	}
        }
		
        st = new StringTokenizer(br.readLine());
        for(int play=0; play<Q; play++) {
        	int L = Integer.parseInt(st.nextToken()); // 단계
        	
        	// 원본 배열 복사
        	int[][] original = new int[len][len];
        	for(int i=0; i<len; i++) {
        		original[i] = ice[i].clone();
        	}
        	
        	// 2^L x 2^L 크기의 부분 격자로 나누어, 모든 부분 격자를 시계 방향으로 90도 회전
        	int partLen = (int)Math.pow(2, L); // 부분 격자의 한 변의 길이
        	for(int i=0; i<len/partLen; i++) {
        		for(int j=0; j<len/partLen; j++) {
        			rotate(i*partLen, j*partLen, partLen, original);
        		}
        	}
        	
        	for(int i=0; i<len; i++) {
        		original[i] = ice[i].clone();
        	}
        	
        	// 얼음의 양 감소
        	for(int i=0; i<len; i++) {
            	for(int j=0; j<len; j++) {
            		check(i, j, original);
            	}
            }
        }
        
        // 남아있는 얼음의 합과 가장 큰 덩어리가 차지하는 칸의 개수 구하기
        bfs();
        
        System.out.println(sum);
        System.out.println(max);
	}
	
	static void rotate(int x, int y, int partLen, int[][] original) {
		for(int i=0; i<partLen; i++) {
			for(int j=0; j<partLen; j++) {
				ice[x+j][y+partLen-1-i] = original[x+i][y+j];
			}
		}
	}
	
	static void check(int x, int y, int[][] original) {
		int count = 0;
		
		for(int i=0; i<4; i++) {
			int nx = x + dx[i];
			int ny = y + dy[i];
			
			if(isRange(nx, ny)) {
				if(original[nx][ny] > 0) {
					count++;
				}
			}
		}
		
		if(count < 3) {
			if(ice[x][y] != 0) { // 이미 0이면 계속 0 유지
				ice[x][y] -= 1;
			}
		}
	}
	
	static boolean isRange(int x, int y) {
		if(x >= 0 && x < len && y >= 0 && y < len) {
			return true;
		}
		return false;
	}
	
	static void bfs() {
		Queue<Node> queue = new ArrayDeque<>();
		boolean[][] visited = new boolean[len][len];
		
		for(int i=0; i<len; i++) {
			for(int j=0; j<len; j++) {
				sum += ice[i][j];
				
				if(ice[i][j] > 0 && !visited[i][j]) {
					queue.add(new Node(i, j));
					visited[i][j] = true;
					
					int cnt = 1;
					
					while(!queue.isEmpty()) {
						Node current = queue.poll();
						
						for(int k=0; k<4; k++) {
							int nx = current.x + dx[k];
							int ny = current.y + dy[k];
							
							if(isRange(nx, ny)) {
								if(ice[nx][ny] > 0 && !visited[nx][ny]) {
									queue.add(new Node(nx, ny));
									visited[nx][ny] = true;
									cnt++;
								}
							}
						}
					}
					max = Math.max(max, cnt);
				}
			}
		}
	}

}
