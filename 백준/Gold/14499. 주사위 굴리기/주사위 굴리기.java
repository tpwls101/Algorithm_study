import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * <BJ_14499_주사위굴리기>
 * 
 *   2							   0
 * 4 1 3   dice 배열의 인덱스로 표현하면   1 2 3
 *   5                             4
 *   6                             5
 * 
 * 생각보다 어렵지 않은 문제!
 * 주사위를 각각 동서남북 방향으로 굴릴 때 어떻게 변하는지를 직접 구현해주고
 * 굴리기만 하면 된다.
 * 
 * @author YooSejin
 *
 */

public class Main {
	
	static int N; // 지도의 세로 크기
	static int M; // 지도의 가로 크기
	static int x; // 주사위 x좌표
	static int y; // 주사위 y좌표
	static int K; // 명령의 개수
	static int[][] map; // 지도
	static int[] dice; // 주사위 (처음엔 모든 면에 0이 적혀있다)
	
	// 동서북남
	static int[] dx = { 0, 0, -1, 1 };
	static int[] dy = { 1, -1, 0, 0 };
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		x = Integer.parseInt(st.nextToken());
		y = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		
		map = new int[N][M];
		dice = new int[6];
		
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0; j<M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		st = new StringTokenizer(br.readLine());
		for(int order=0; order<K; order++) {
			int dir = Integer.parseInt(st.nextToken());
			
			int nx = x + dx[dir-1];
			int ny = y + dy[dir-1];
			
			// 주사위가 지도 범위를 벗어나지 않는 경우에만 이동
			if(nx >= 0 && nx < N && ny >= 0 && ny < M) {
				roll(dir);
				
				if(map[nx][ny] == 0) { // 이동한 칸에 쓰여 있는 수가 0이면
					map[nx][ny] = dice[5]; // 주사위의 바닥면에 쓰여 있는 수가 칸에 복사됨
				} else { // 이동한 칸에 쓰여 있는 수가 0이 아니라면
					dice[5] = map[nx][ny]; // 칸에 쓰여 있는 수가 주사위의 바닥면으로 복사되고
					map[nx][ny] = 0; // 칸에 쓰여 있는 수는 0이 됨
				}
				
				System.out.println(dice[2]);
				
				x = nx;
				y = ny;
			}
		}
		
	}
	
	static void roll(int dir) {
		int temp = 0;
		
		switch(dir) {
		case 1: // 동쪽
			temp = dice[3];
			dice[3] = dice[2];
			dice[2] = dice[1];
			dice[1] = dice[5];
			dice[5] = temp;
			break;
		case 2: // 서쪽
			temp = dice[1];
			dice[1] = dice[2];
			dice[2] = dice[3];
			dice[3] = dice[5];
			dice[5] = temp;
			break;
		case 3: // 북쪽
			temp = dice[0];
			dice[0] = dice[2];
			dice[2] = dice[4];
			dice[4] = dice[5];
			dice[5] = temp;
			break;
		case 4: // 남쪽
			temp = dice[5];
			dice[5] = dice[4];
			dice[4] = dice[2];
			dice[2] = dice[0];
			dice[0] = temp;
			break;
		}
	}

}