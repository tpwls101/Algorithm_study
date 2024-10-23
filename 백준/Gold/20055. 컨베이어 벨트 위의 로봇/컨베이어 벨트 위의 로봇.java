import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * <BJ_20055_컨베이어벨트위의로봇>
 * 맨 처음에 로봇을 하나 올리고 시작한다는 말이 없다!!!!
 * 내가 임의로 당연히 올릴 것이라 생각하고 코드를 짠 것 ㅜㅜ
 * 문제를 제대로 읽자!!
 * 
 * @author YooSejin
 *
 */

public class Main {
	
	static int N; // 컨베이어 벨트의 길이
	static int K; // 내구도가 0인 칸의 개수가 K개 이상이면 종료
	static int[][] arr; // 내구도
	static boolean[][] visited;
	static int level = 1; // 단계
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		
		arr = new int[2][N];
		visited = new boolean[2][N];
		
		st = new StringTokenizer(br.readLine());
		for(int i=0; i<N; i++) {
			arr[0][i] = Integer.parseInt(st.nextToken());
		}
		for(int i=N-1; i>=0; i--) {
			arr[1][i] = Integer.parseInt(st.nextToken());
		}
		
		// 초기에 로봇 하나 올리기 (x) -> 문제에 로봇을 올리고 시작한다는 말은 없다. 임의 해석 금지!!!
		// 1. 한 칸 회전
		// 2. 로봇 이동 (다음 칸에 로봇이 없고, 내구도가 1 이상일 때에만 가능)
		// 3. 로봇 새로 올리기 (단, 내구도가 0이 아니라면)
		// 4. 내구도가 0인 칸의 개수 확인 -> K개 이상이면 종료
		
		// 맨 처음 로봇 올리기
//		arr[0][0] -= 1;
//		visited[0][0] = true;
		
		while(true) {
			// 1. 한 칸 회전
			rotate();
			getOff();
			
			// 2. 로봇 이동 (다음 칸에 로봇이 없고, 내구도가 1 이상일 때에만 가능)
			for(int i=N-2; i>=0; i--) {
				if(visited[0][i]) { // 차례대로 로봇이 있다면
					// 다음 칸에 로봇이 없고, 내구도가 1 이상이라면 이동
					if(!visited[0][i+1] && arr[0][i+1] >= 1) {
						arr[0][i+1] -= 1;
						visited[0][i+1] = true;
						visited[0][i] = false;
					}
				}
			}
			getOff();
			
			// 3. 로봇 새로 올리기 (단, 내구도가 0이 아니라면)
			if(arr[0][0] != 0) {
				arr[0][0] -= 1;
				visited[0][0] = true;
			}
			
			// 4. 내구도가 0인 칸의 개수 확인 -> K개 이상이면 종료
			int zeroCnt = 0; // 내구도가 0인 칸의 개수
			for(int i=0; i<2; i++) {
				for(int j=0; j<N; j++) {
					if(arr[i][j] == 0) {
						zeroCnt++;
					}
				}
			}
			if(zeroCnt >= K) break;
			
			// 종료되지 않는다면 다음 단계 수행
			level++;
		}
		
		System.out.println(level);
	}
	
	// 벨트 회전 (로봇도 함께 회전한다)
	static void rotate() {
		int temp = arr[1][0];
		boolean vtemp = visited[1][0];
		
		for(int i=0; i<N-1; i++) {
			arr[1][i] = arr[1][i+1];
			visited[1][i] = visited[1][i+1];
		}
		arr[1][N-1] = arr[0][N-1];
		visited[1][N-1] = visited[0][N-1];
		
		for(int i=N-1; i>0; i--) {
			arr[0][i] = arr[0][i-1];
			visited[0][i] = visited[0][i-1];
		}
		arr[0][0] = temp;
		visited[0][0] = vtemp;
	}
	
	// 로봇은 내리는 위치에 도달하면 언제든지 즉시 내린다
	static void getOff() {
		if(visited[0][N-1]) {
			visited[0][N-1] = false;
		}
	}

}