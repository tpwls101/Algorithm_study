import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * <BJ_17090_미로탈출하기>
 * DP와 DFS를 활용한 문제이다.
 * 처음에는 어렵지 않아 보였으나 방문배열 처리로 인한 시간초과가 문제였다.
 * 먼저 매 칸마다 새롭게 방문배열을 할당해 사용했으나, 그렇게 되면 메모리도 계속 할당해야 하고, 매번 최대 25만번 boolean 값 할당하고, 25만번 탐색을 진행하야 하므로 시간초과난다.
 * 따라서 방문배열을 전역 처리해 사용함으로써 불필요한 메모리 할당과 중복 탐색을 방지해야 한다.
 * 
 * 풀이 방법은 일단 모든 칸마다 가능한지 확인해야 하므로 브루트포스로 확인한다.
 * 그리고 (0,0)부터 탐색한다고 가정했을 때 지나간 길을 다음 칸에서 탐색할 때 똑같이 지나갈 수 있다.
 * 왜냐하면 각 칸마다 갈 수 있는 길이 정해져 있기 때문이다.
 * 따라서 위에서부터 모든 칸을 탐색하면 같은 길을 중복해서 지나가므로 DP를 활용한다.
 * 처음에는 (N-1, M-1)부터 탐색해 DP값을 저장했으나, dfs 리턴하면서 모든 칸에 dp값이 저장되기 때문에 (0,0)부터 해도 상관없다.
 * 
 * 탈출 가능 여부는 각 칸마다 DFS를 돌려 다음 좌표로 넘어간다.
 * 그리고 미로를 벗어났거나 혹은 이미 탈출이 가능한 칸이라면 탈출 가능 여부(possible)을 true로 처리하고 리턴해준다.
 * 반면 이미 탈출할 수 없는 칸이거나, 이미 방문했던 칸을 지나게 되면 같은 길을 반복해서 지나가므로 탈출할 수 없게 되기 때문에 그냥 리턴해준다.
 * 
 * @author YooSejin
 *
 */

public class Main {

	static int N;
	static int M;
	static char[][] arr;
	static int[][] dp; // 0:초기화값, 1:탈출가능, 2:탈출불가능
	static boolean[][] visited;
	static boolean possible; // 탈출 가능 여부
	static int answer = 0;
	
	static int[] dx = { -1, 0, 1, 0 }; // 상우하좌
	static int[] dy = { 0, 1, 0, -1 };
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		arr = new char[N][M];
		dp = new int[N][M];
		visited = new boolean[N][M];
		
		for(int i=0; i<N; i++) {
			String str = br.readLine();
			for(int j=0; j<M; j++) {
				arr[i][j] = str.charAt(j);
			}
		}
		
		for(int i=N-1; i>=0; i--) {
			for(int j=M-1; j>=0; j--) {
				possible = false;
				dfs(i, j);
			}
		}
		
		System.out.println(answer);
	}
	
	static void dfs(int x, int y) {
		// 미로를 탈출했거나 이미 탈출이 가능한 칸인 경우
		if(!isRange(x, y) || dp[x][y] == 1) {
			answer++;
			possible = true;
			return;
		}
		
		if(dp[x][y] == 2) return; // 이미 탈출할 수 없는 칸이므로 리턴
		if(visited[x][y]) return; // 이미 방문한 칸을 다시 방문하면 무한루프에 빠지므로 리턴
		
		visited[x][y] = true;
		
		int dir = -1;
		if(arr[x][y] == 'U') dir = 0;
		else if(arr[x][y] == 'R') dir = 1;
		else if(arr[x][y] == 'D') dir = 2;
		else if(arr[x][y] == 'L') dir = 3;
		
		int nx = x + dx[dir];
		int ny = y + dy[dir];
		
		dfs(nx, ny);
		
		visited[x][y] = false;
		
		// 시간 단축 위해
		// main 함수에서 시작칸인 (i,j)만 dp배열 처리하는 게 아니라, 지나간 모든 칸을 처리하면 더 시간을 단축할 수 있음
		if(possible) dp[x][y] = 1;
		else dp[x][y] = 2;
	}
	
	static boolean isRange(int x, int y) {
		if(x >= 0 && x < N && y >= 0 && y < M) {
			return true;
		}
		return false;
	}

}
