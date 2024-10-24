import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * <BJ_21610_마법사상어와비바라기>
 * nx, ny의 좌표 구하는 공식에서 헤맸다 ㅜㅜ
 * int nx = (N + i + dx[d] * s) % N;
 * int ny = (N + j + dy[d] * s) % N;
 * 여기까지 왔는데 (s % N) 처리를 못해줬다 ..!
 * 
 * 두번째 실수
 * 구름이 전부 이동한 후, 비가 내려 물이 1증가하면 arr[][]이 0 -> 1로 바껴 대각선 cnt에 들어갈 수도 있다
 * 따라서, 한칸씩 이동시켜 물을 주고(+1) 대각선을 카운트하면 안된다!!
 * 
 * @author YooSejin
 *
 */

public class Main {
	static int N; // NxN 격자
	static int M; // 구름 이동 명령 수
	static int[][] arr; // 물의 양을 저장할 배열
	static boolean[][] visited; // 원래 구름의 위치 확인용
	static boolean[][] moved; // 구름 이동 후 구름의 위치 확인용
	
	static int[] dx = { 0, -1, -1, -1, 0, 1, 1, 1 };
	static int[] dy = { -1, -1, 0, 1, 1, 1, 0, -1 };
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		arr = new int[N][N];
		visited = new boolean[N][N];
		moved = new boolean[N][N];
		
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0; j<N; j++) {
				arr[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		// 처음 비바라기를 시전하면 왼쪽 아래에 구름이 생긴다
		visited[N-1][0] = true;
		visited[N-1][1] = true;
		visited[N-2][0] = true;
		visited[N-2][1] = true;
		
		// M번의 명령 수행
		for(int order=0; order<M; order++) {
			st = new StringTokenizer(br.readLine());
			int d = Integer.parseInt(st.nextToken()) - 1; // 방향
			int s = Integer.parseInt(st.nextToken()); // 이동 칸 수
			
			// 1. 구름 이동 (d방향으로 s칸만큼)
			for(int i=0; i<N; i++) {
				for(int j=0; j<N; j++) {
					if(visited[i][j]) {
						// i : 원래 좌표 / dx[d] : 방향 / (s%N) : 칸 수
						int nx = (N + i + dx[d] * (s%N)) % N;
						int ny = (N + j + dy[d] * (s%N)) % N;
						
						visited[i][j] = false;
						moved[nx][ny] = true;
						
						// 2. 각 구름에서 비가 내리고 물의 양 1 증가
						arr[nx][ny] += 1;
					}
				}
			}
			
			// 3. 구름 사라짐 (하지만  5번에서 구름이 사라진 칸을 제외하고 구름을 생성하기 위해 일단 남겨둠)
			
			// 4. 비 내린 곳에서 물복사버그 마법
			// 대각선에 물이 있는 칸의 개수 구하기 (dx, dy 인덱스 : 1 3 5 7 번)
			for(int i=0; i<N; i++) {
				for(int j=0; j<N; j++) {
					if(moved[i][j]) {
						int cnt = 0;
						for(int k=0; k<4; k++) {
							int crossX = i + dx[2*k + 1];
							int crossY = j + dy[2*k + 1];
							
							// 단, 이동과 다르게 경계를 넘어가면 안된다
							if(crossX >= 0 && crossX < N && crossY >= 0 && crossY < N) {
								if(arr[crossX][crossY] > 0) { // 물이 있는 칸만 센다
									cnt++;
								}
							}
						}
						arr[i][j] += cnt; // 물이 있는 칸의 수만큼 물의 양 증가시키기
					}
				}
			}
			
			// 5. 구름이 사라진 칸을 제외하고, 물의 양이 2 이상인 모든 칸에 구름이 생긴다 -> 물의 양은 2 감소
			for(int i=0; i<N; i++) {
				for(int j=0; j<N; j++) {
					if(!moved[i][j]) { // 구름이 사라진 칸 제외
						if(arr[i][j] >= 2) {
							visited[i][j] = true;
							arr[i][j] -= 2;
						}
					} else { // 3번 수행 : 구름 제거
						moved[i][j] = false;
					}
				}
			}
		}
		
		// M번의 구름 이동이 끝난 후 바구니에 들어있는 물의 양의 합은?
		int sum = 0;
		for(int i=0; i<N; i++) {
			for(int j=0; j<N; j++) {
				sum += arr[i][j];
			}
		}
		
		System.out.println(sum);
	}
}