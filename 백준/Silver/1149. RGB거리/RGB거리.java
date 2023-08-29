import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * <BJ_1149_RGB거리>
 * 집2에 빨간색을 칠한다면 집1에서는 초록색과 파란색을 사용할 수 있다.
 * 초록색과 파란색 중 최소 비용을 가지고 있는 것을 집2의 빨간색을 칠하는 비용에 더해준다.
 * 그러면 집2에는 집2까지 칠하는 최소비용이 저장된다.
 * 이런식으로 N번째 집까지 모두 더해주게 되면 N번 집에는 모든 집을 칠했을 때의 최소비용이 저장되고, 3개의 비용 중 최소값을 비교해 출력해주면 된다.
 * 
 * 메모리 : 14492kb, 실행시간 : 140ms
 * 
 * @author 유세진
 *
 */

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
		
		// 
		for(int r=1; r<N; r++) { // 집1은 더할 값이 없으니 제외
			for(int c=0; c<3; c++) {
				// 
				cost[r][c] += Math.min(cost[r-1][(c+1)%3], cost[r-1][(c+2)%3]);
			}
		}
		
		int answer = 0;
		answer = Math.min(cost[N-1][0], Math.min(cost[N-1][1], cost[N-1][2]));
		System.out.println(answer);
		
	}

}
