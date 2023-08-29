import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(st.nextToken()); // 집의 수 (2~1000)
		
		int[][] cost = new int[N][3]; // 집을 칠하는 비용을 저장할 배열
		
		// 집을 칠하는 비용 입력받기
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0; j<3; j++) {
				cost[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		for(int r=1; r<N; r++) {
			for(int c=0; c<3; c++) {
				cost[r][c] += Math.min(cost[r-1][(c+1)%3], cost[r-1][(c+2)%3]);
			}
		}
		
		int answer = 0;
		answer = Math.min(cost[N-1][0], Math.min(cost[N-1][1], cost[N-1][2]));
		System.out.println(answer);
		
	}

}