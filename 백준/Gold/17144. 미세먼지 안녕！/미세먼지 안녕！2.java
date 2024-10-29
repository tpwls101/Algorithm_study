import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	
	static int R;
	static int C;
	static int T; // T초
	static int[][] arr;
	static int[][] copy;
	
	static int[] dx = { 0, 1, 0, -1 };
	static int[] dy = { 1, 0, -1, 0 };
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		T = Integer.parseInt(st.nextToken());
		
		arr = new int[R][C];
		copy = new int[R][C];
		
		for(int i=0; i<R; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0; j<C; j++) {
				arr[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		for(int time=0; time<T; time++) {
			spread(); // 1. 미세먼지 확산
			clean(); // 2. 공기청정기 작동
			
			for(int i=0; i<R; i++) {
				arr[i] = copy[i].clone();
			}
		}
		
		// 남아있는 미세먼지의 양 구하기
		int sum = 0;
		for(int i=0; i<R; i++) {
			for(int j=0; j<C; j++) {
				if(arr[i][j] > 0) {
					sum += arr[i][j];
				}
			}
		}
		
		System.out.println(sum);
	}
	
	static void spread() {
		// 확산된 후의 미세먼지를 저장하기 위해 배열 하나 복사
		for(int i=0; i<R; i++) {
			copy[i] = arr[i].clone();
		}
		
		for(int i=0; i<R; i++) {
			for(int j=0; j<C; j++) {
				if(arr[i][j] > 0) { // 미세먼지가 있으면 확산 (0은 빈칸, -1은 공기청정기니 제외)
					int cnt = 0; // 확산하는 칸의 개수
					for(int k=0; k<4; k++) {
						int nx = i + dx[k];
						int ny = j + dy[k];
						
						if(nx >= 0 && nx < R && ny >= 0 && ny < C && arr[nx][ny] != -1) {
							copy[nx][ny] += arr[i][j] / 5;
							cnt++;
						}
					}
					copy[i][j] -= (arr[i][j] / 5) * cnt;
				}
			}
		}
	}
	
	static void clean() {
		int x = 0; // 공기청정기 위쪽의 행 위치
		for(int i=0; i<R; i++) {
			if(arr[i][0] == -1) {
				x = i;
				break;
			}
		}
		
		// 공기청정기 위쪽은 반시계 방향으로 순환
		for(int i=x-1; i>0; i--) {
			copy[i][0] = copy[i-1][0];
		}
		for(int i=0; i<C-1; i++) {
			copy[0][i] = copy[0][i+1];
		}
		for(int i=0; i<x; i++) {
			copy[i][C-1] = copy[i+1][C-1];
		}
		for(int i=C-1; i>1; i--) {
			copy[x][i] = copy[x][i-1];
		}
		copy[x][1] = 0;
		
		// 공기청정기 아래쪽은 시계 방향으로 순환
		for(int i=x+2; i<R-1; i++) {
			copy[i][0] = copy[i+1][0];
		}
		for(int i=0; i<C-1; i++) {
			copy[R-1][i] = copy[R-1][i+1];
		}
		for(int i=R-1; i>x+1; i--) {
			copy[i][C-1] = copy[i-1][C-1];
		}
		for(int i=C-1; i>1; i--) {
			copy[x+1][i] = copy[x+1][i-1];
		}
		copy[x+1][1] = 0;
	}

}
