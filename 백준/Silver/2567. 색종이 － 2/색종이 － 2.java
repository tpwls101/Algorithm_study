import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	
	static int[] dx = { 0, 1, 0, -1 };
	static int[] dy = { 1, 0, -1, 0 };
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		
		boolean[][] arr = new boolean[101][101];
		
		int N = Integer.parseInt(br.readLine()); // 색종이의 수
		
		// 색종이 칠하기
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());
			
			for(int a=x; a<x+10; a++) {
				for(int b=y; b<y+10; b++) {
					arr[a][b] = true;
				}
			}
		}
		
		// 둘레 구하기
		int count = 0;
		for(int i=1; i<=100; i++) {
			for(int j=1; j<=100; j++) {
				// 색칠되어 있는 칸 기준으로 상하좌우 탐색
				if(arr[i][j]) {
					for(int k=0; k<4; k++) {
						int nx = i + dx[k];
						int ny = j + dy[k];
						
						// next칸이 색칠되지 않은 칸이거나 도화지를 벗어나면 둘레로 카운트
						if(!arr[nx][ny] || nx < 0 || nx >= 100 || ny < 0 || ny >= 100) {
							count++;
						}
					}
				}
			}
		}
		
		System.out.println(count);
	}

}