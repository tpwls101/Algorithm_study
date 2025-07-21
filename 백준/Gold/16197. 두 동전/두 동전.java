import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * <BJ_16197_두동전>
 * 두 동전이 '동시에' 움직이기 때문에 각 동전 객체에 이동 횟수를 저장할 필요 없고 count로 한번에 처리해도 된다.
 * 
 * !! 주의할 점 !!
 * while문에서 for문을 돌릴 때 큐의 사이즈만큼 for문을 돌리고나서 count를 증가해야 한다.
 * 그렇게 해야 버튼을 한번 눌렀을 때 이동 가능한 모든 좌표들에 대해 새로운 이동 좌표를 만들 수 있고, 동일한 버튼 누른 횟수를 가질 수 있다.
 * 만약 큐 사이즈로 for문을 돌리지 않으면 버튼을 두번 눌러 가능한 좌표에서 처음으로 새롭게 좌표를 만들고 count가 증가해 3이 된후
 * 다른 좌표에서 또 새롭게 좌표를 만들어 count가 4가 되는 일이 발생한다.
 * 하지만 새로운 두 좌표는 모두 두번 누른 후 추가로 누른 것이므로 count가 3이 되어야 한다.
 * 
 * @author YooSejin
 *
 */

public class Main {
	
	static int N;
	static int M;
	static char[][] arr;
	
	static int[] dx = { -1, 0, 1, 0 }; // 상우하좌
	static int[] dy = { 0, 1, 0, -1 };
	
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
		
        arr = new char[N][M];
        
        Node coin1 = null;
        Node coin2 = null;
        
        for(int i=0; i<N; i++) {
        	String str = br.readLine();
        	for(int j=0; j<M; j++) {
        		arr[i][j] = str.charAt(j);
        		if(arr[i][j] == 'o') {
        			if(coin1 == null) {
        				coin1 = new Node(i, j);
        			} else {
        				coin2 = new Node(i, j);
        			}
        		}
        	}
        }
        
        int answer = bfs(coin1, coin2);
        
        System.out.println(answer);
	}
	
	static int bfs(Node coin1, Node coin2) {
		Queue<Node[]> queue = new ArrayDeque<>();
		queue.add(new Node[] {coin1, coin2});
		
		int count = 1; // 이동 횟수
		
		while(!queue.isEmpty()) { // 동전 하나가 보드 밖으로 나갈 때까지 반복
			if(count > 10) return -1; // 버튼을 10번보다 많이 눌러야 하면 -1 출력
			
			int len = queue.size();
			for(int l=0; l<len; l++) { // 버튼을 누르는 횟수마다 동일한 레벨에 있는 노드들만 처리하기 위해 필수
				Node[] coins = queue.poll();
				
				// 2개의 동전을 상하좌우로 동시에 탐색
				for(int i=0; i<4; i++) {
					int nx1 = coins[0].x + dx[i];
					int ny1 = coins[0].y + dy[i];
					
					int nx2 = coins[1].x + dx[i];
					int ny2 = coins[1].y + dy[i];
					
					// 둘 중 하나만 보드 밖으로 나가야 성공
					if((isOut(nx1, ny1) && !isOut(nx2, ny2)) || (!isOut(nx1, ny1) && isOut(nx2, ny2))) {
//						System.out.println("둘 중 하나 밖으로 성공 : " + nx1 + " " + ny1 + " / " + nx2 + " " + ny2);
						return count;
					}
					
					// 둘 다 보드 밖으로 떨어지면 실패 -> 다음 탐색
					if(isOut(nx1, ny1) && isOut(nx2, ny2)) {
						continue;
					}
					
					// 벽을 만나면 이동하지 않음
					if(isWall(nx1, ny1)) {
						nx1 = coins[0].x;
						ny1 = coins[0].y;
					}
					
					if(isWall(nx2, ny2)) {
						nx2 = coins[1].x;
						ny2 = coins[1].y;
					}
					
					// 이동 후 두 동전이 같은 위치에 있으면 어차피 한개만 떨어질 수 없으므로 가지치기
					if(nx1 == nx2 && ny1 == ny2) {
						continue;
					}
					
					// 방문처리??
					// 두 동전 모두 이동하지 못하고 원래 자리에 그대로 있으면 큐에 담지 않고, 다음 탐색 -> 이동 count만 증가시키고 반복하게 되기 때문
					if(nx1 == coins[0].x && ny1 == coins[0].y && nx2 == coins[1].x && ny2 == coins[1].y) {
						continue;
					}
					
//					System.out.println(nx1 + " " + ny1 + " / " + nx2 + " " + ny2 + " / count = " + count);
					
					queue.add(new Node[] {new Node(nx1, ny1), new Node(nx2, ny2)});
				}
			}
			count++;
        }
		return -1; // while문을 계속 돌려 무한루프에 빠지면 -1 리턴
	}
	
	static boolean isOut(int x, int y) {
		if(x < 0 || x >= N || y < 0 || y >= M) {
			return true;
		}
		return false;
	}
	
	static boolean isWall(int x, int y) {
		if(arr[x][y] == '#') {
			return true;
		}
		return false;
	}
	
}
