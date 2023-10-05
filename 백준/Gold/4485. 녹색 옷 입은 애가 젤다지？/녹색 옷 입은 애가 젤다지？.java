import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * <BJ_4485_녹색옷입은애가젤다지>
 * 링크가 잃을 수 밖에 없는 최소 금액은 얼마인가? -> 즉, (0,0)에서 (N-1,N-1)까지 가는데 필요한 최소비용 구하는 문제!
 * 
 * @author 유세진
 *
 */

public class Main {
	
	static int N; // 동굴의 크기
	static int[][] arr;
	static int[][] sum;
	//static boolean[][] visited;
	
	static int[] dx = { 0, 1, 0, -1 }; // 행
	static int[] dy = { 1, 0, -1, 0 }; // 열
	
	static class Pos {
		int x;
		int y;
		
		Pos(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int num = 1;
		
		while(true) {
			
			N = Integer.parseInt(br.readLine());
			if(N == 0) break;
			
			arr = new int[N][N];
			sum = new int[N][N];
			
			//Arrays.fill(sum, Integer.MAX_VALUE); // sum 배열에서 최소값 갱신을 위해 max value로 초기화
			//Arrays.fill(sum, 1000000);
			for(int i=0; i<N; i++) {
				Arrays.fill(sum[i], Integer.MAX_VALUE);
			}
			
			// 입력
			for(int i=0; i<N; i++) {
				StringTokenizer st = new StringTokenizer(br.readLine());
				for(int j=0; j<N; j++) {
					arr[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			
			bfs(new Pos(0,0));
			
			System.out.println("Problem " + num + ": " + sum[N-1][N-1]);
			
			num++;
		}
		
	}
	
	private static void bfs(Pos pos) {
		Queue<Pos> queue = new ArrayDeque<>();
		queue.add(pos);
		sum[pos.x][pos.y] = arr[pos.x][pos.y]; // (0,0)의 sum값
		//visited[pos[0]][pos[1]] = true;
		
		while(!queue.isEmpty()) {
			Pos current = queue.poll();
			
			for(int i=0; i<4; i++) {
				int temp_x = current.x + dx[i];
				int temp_y = current.y + dy[i];
				
				if(temp_x >= 0 && temp_x < N && temp_y >= 0 && temp_y < N) {
					// 한 칸 이동할 때마다 값을 더해주고 최소값으로 갱신하여 sum 배열에 저장한다!
					//sum[temp_x][temp_y] = Math.min(sum[temp_x][temp_y], sum[current.x][current.y] + arr[temp_x][temp_y]);
					
					// 위의 내용을 Math.min() 대신 if문 사용!!
					// 큐에 좌표가 계속해서 들어가면 안되기 때문에 if문으로 조건을 두어 sum 값을 갱신함과 동시에 큐에 삽입!
					// 큐에 삽입 -> 단, 조건!! 현재 sum값에 가중치를 더한 값이 원래 값보다 작은 경우에만 !!
					if(sum[current.x][current.y] + arr[temp_x][temp_y] < sum[temp_x][temp_y]) {
						sum[temp_x][temp_y] = sum[current.x][current.y] + arr[temp_x][temp_y];
						queue.add(new Pos(temp_x, temp_y));
					}
				}
			}
		}
		
	}

}