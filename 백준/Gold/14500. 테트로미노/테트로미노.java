import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * <BJ_14500_테트로미노> - DFS
 * 풀이법 왕신세계
 * 각 도형별, 각 도형의 회전/대칭일 때를 미리 만들어둬야 하는건가 생각도 했는데
 * 그냥 상하좌우 완탐을 돌리면 ㅜ 모양 빼고 모든 도형의 경우의 수가 나온다!!! (회전, 대칭시킨 모양까지 포함해서)
 * cnt가 4이면 확인해주기만 하면 된다!
 * 
 * 그렇다면 ㅜ 모양은 어떻게 해결하느냐?
 * cnt가 2일 때, 즉 2번째 칸일 때, 3번째 탐색을 시작하는 위치를 3번째 탐색 위치가 아니라 2번째 칸에서 다시 한번 탐색하도록 추가해준다!
 * 그러면 가운데에서 오른쪽 방향으로도 탐색하고 아래/위로도 탐색을 해 ㅜ/ㅗ 모양을 만들 수 있다!
 * 
 * @author YooSejin
 *
 */

public class Main {
	
	static int N; // 세로 크기
	static int M; // 가로 크기
	static int[][] arr; // 종이에 적힌 수를 저장할 배열
	static boolean[][] visited;
	static int max = 0; // 테트로미노가 놓인 칸에 적힌 수들의 합의 최댓값
	
	static int[] dx = { 0, 1, 0, -1 };
	static int[] dy = { 1, 0, -1, 0 };
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		arr = new int[N][M];
		visited = new boolean[N][M];
		
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0; j<M; j++) {
				arr[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		for(int i=0; i<N; i++) {
			for(int j=0; j<M; j++) {
				visited[i][j] = true;
				dfs(i, j, arr[i][j], 1);
				visited[i][j] = false;
			}
		}
		
		System.out.println(max);
	}
	
	static void dfs(int x, int y, int sum, int cnt) {
		if(cnt == 4) {
			max = Math.max(max, sum);
			return;
		}
		
		for(int i=0; i<4; i++) {
			int nx = x + dx[i];
			int ny = y + dy[i];
			
			if(nx >= 0 && nx < N && ny >= 0 && ny < M) {
				if(!visited[nx][ny]) {
					
					// 두번째 칸에서 한번 더 탐색
					if(cnt == 2) {
						visited[nx][ny] = true;
						dfs(x, y, sum + arr[nx][ny], cnt+1); // 3번째 칸 위치에서 상하좌우 탐색을 하는 것이 아니라 두번째 칸 위치에서 한번 더 탐색!
						visited[nx][ny] = false;
					}
					
					visited[nx][ny] = true;
					dfs(nx, ny, sum + arr[nx][ny], cnt+1);
					visited[nx][ny] = false;
				}
			}
		}
	}

}
