import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	
	static int N; // 격자의 크기
	static int[][] sand; // 모래의 양
	static boolean[][] visited;
	static int answer = 0; // 격자 밖으로 나간 모래의 양
	
	// 좌하우상
	static int[] dx = { 0, 1, 0, -1 };
	static int[] dy = { -1, 0, 1, 0 };
	
	// 위에서부터 2, 10, 7, 1, 5, 10, 7, 1, 2, a 순서
	static int[][] dsx = {{ -2, -1, -1, -1, 0, 1, 1, 1, 2, 0 }, // 좌
						  { 0, 1, 0, -1, 2, 1, 0, -1, 0, 1 }, // 하
						  { 2, 1, 1, 1, 0, -1, -1, -1, -2, 0 }, // 우
						  { 0, -1, 0, 1, -2, -1, 0, 1, 0, -1 }}; // 상
	static int[][] dsy = {{ 0, -1, 0, 1, -2, -1, 0, 1, 0, -1 }, // 좌
			   			  { -2, -1, -1, -1, 0, 1, 1, 1, 2, 0 }, // 하
			   			  { 0, 1, 0, -1, 2, 1, 0, -1, 0, 1 }, // 우
			   			  { 2, 1, 1, 1, 0, -1, -1, -1, -2, 0 }}; // 상
	static int[] rate = { 2, 10, 7, 1, 5, 10, 7, 1, 2 };
	
	// 하우상좌
//	static int[] dx = { 1, 0, -1, 0 };
//	static int[] dy = { 0, 1, 0, -1 };
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;
        
        N = Integer.parseInt(br.readLine());
		
        sand = new int[N][N];
        visited = new boolean[N][N];
        
        for(int i=0; i<N; i++) {
        	st = new StringTokenizer(br.readLine());
        	for(int j=0; j<N; j++) {
        		sand[i][j] = Integer.parseInt(st.nextToken());
        	}
        }
		
        int x = N/2;
        int y = N/2;
        int dir = 0;
        visited[x][y] = true;
        
//        while(x != 0 || y != 0) {
        while(true) {
        	if(x == 0 && y ==0) break;
        	
//        	System.out.println("방향 : " + dir);
        	int nx = x + dx[dir];
        	int ny = y + dy[dir];
//        	System.out.println("nx=" + nx + ", ny=" + ny);
        	
        	// 다음 방향으로 꺾을 수 있으면 꺾고, 그게 아니라면 꺾을 수 있을 때까지 같은 방향으로 직진
        	if(!visited[nx][ny]) {
        		spreadSand(nx, ny, dir); // 모래 흩날리기
        		visited[nx][ny] = true;
        		dir = (dir+1) % 4;
        		x = nx;
        		y = ny;
        	} else {
        		dir = (dir-1+4) % 4;
        	}
        	
//        	for(int i=0; i<N; i++) {
//        		for(int j=0; j<N; j++) {
//        			System.out.print((visited[i][j] ? 1 : 0) + " ");
//        		}
//        		System.out.println();
//        	}
//        	System.out.println();
        	
//        	for(int i=0; i<N; i++) {
//        		for(int j=0; j<N; j++) {
//        			System.out.print(sand[i][j] + " ");
//        		}
//        		System.out.println();
//        	}
//        	System.out.println();
        	
        }
        
        System.out.println(answer);
	}
	
	// (x,y) 좌표의 모든 모래가 퍼짐
	static void spreadSand(int x, int y, int dir) {
		int spreadSum = 0; // 퍼진 모래 양의 합
		
		for(int i=0; i<9; i++) {
			int nx = x + dsx[dir][i];
			int ny = y + dsy[dir][i];
			
			int amount = (int)(sand[x][y] * rate[i] * 0.01);
			
			if(isRange(nx, ny)) {
				sand[nx][ny] += amount;
			} else { // 모래가 격자 밖으로 나갔을 때
				answer += amount;
			}
			spreadSum += amount; // 격자 안이든 밖이든 퍼진 모래는 제외하고 알파를 구해야 한다
		}
		
		// 알파 처리
		// 알파로 이동하는 모래의 양은 비율이 적혀있는 칸으로 이동하지 않은 남은 모래의 양
		// 즉, 격자 밖으로 나간 모래의 양도 제외해야 한다!!! (놓친 포인트)
		int nx = x + dsx[dir][9];
		int ny = y + dsy[dir][9];
		if(isRange(nx, ny)) {
			sand[nx][ny] += sand[x][y] - spreadSum;
		} else {
			answer += sand[x][y] - spreadSum;
		}
		
		sand[x][y] = 0; // 모래가 사방으로 흩뿌려지고 (x,y)좌표에는 남지 않는다
	}
	
	static boolean isRange(int x, int y) {
		if(x >= 0 && x < N && y >= 0 && y < N) {
			return true;
		}
		return false;
	}

}