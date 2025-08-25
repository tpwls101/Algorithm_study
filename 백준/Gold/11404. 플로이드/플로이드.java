import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * <BJ_11404_플로이드>
 * 플로이드워셜 알고리즘
 * 모든 도시의 쌍(A, B)에 대한 최소 비용을 구해야 하므로 플로이드워셜 알고리즘 문제이다.
 * 단, 주의할 점은 시작 도시와 도착 도시를 연결하는 노선이 여러 개일 수 있으니 2차원 배열에 입력값을 받을 때부터 최소값으로 넣어줘야 한다.
 * 또 최소값을 갱신하는 과정에서 arr[i][k]와 arr[k][j]가 INF인 경우, arr[i][k] + arr[k][j] 연산을 수행하면 오버플로우 나므로 arr의 자료형을 long 타입을 써줘야한다.
 * 
 * 1. NxN 크기의 2차원 배열을 INF로 초기화시킨다. (최솟값으로 갱신하기 위해)
 * 2. 입력값을 받으며 A에서 B로 가는 비용을 배열에 넣어준다. 단, 기존 값과 비교해 최소값으로 저장한다.
 * 3. 3중 for문을 돌려 i에서 k를 거쳐 j로 가는 최소 비용을 갱신한다. (k=중간점, i=행, j=열)
 * 4. 모든 도시의 쌍에 대한 최소 비용을 담고있는 2차원 배열을 출력한다. i에서 j로 갈 수 없는 경우(INF인 경우)에는 0을 출력한다.
 * 
 * @author YooSejin
 *
 */

public class Main {
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		
		int N = Integer.parseInt(br.readLine());
		int M = Integer.parseInt(br.readLine());
		
		long[][] arr = new long[N+1][N+1];
		for(int i=0; i<=N; i++) {
			Arrays.fill(arr[i], Integer.MAX_VALUE); // 1. 최댓값으로 초기화
			arr[i][i] = 0;
		}
		
		// 2. 입력값 받으면서 가중치 저장
		for(int i=0; i<M; i++) {
			st = new StringTokenizer(br.readLine());
			int start = Integer.parseInt(st.nextToken());
			int dest = Integer.parseInt(st.nextToken());
			int cost = Integer.parseInt(st.nextToken());
			
			arr[start][dest] = Math.min(arr[start][dest], cost);
		}
		
		// 3. 3중 for문 돌려 최솟값 갱신
		for(int k=1; k<=N; k++) { // 중간지점
			for(int i=1; i<=N; i++) { // 행
				for(int j=1; j<=N; j++) { // 열
					arr[i][j] = Math.min(arr[i][j], arr[i][k] + arr[k][j]);
				}
			}
		}
		
		StringBuilder sb = new StringBuilder();
		for(int i=1; i<=N; i++) {
			for(int j=1; j<=N; j++) {
				sb.append(arr[i][j] == Integer.MAX_VALUE ? "0 " : arr[i][j] + " ");
			}
			sb.append("\n");
		}
		
		System.out.println(sb);
	}

}
