import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * <BJ_11660_구간합구하기5>
 * 배열은 N+1 크기로 할 것
 * 식만 잘 짜면 어렵지 않은 문제
 * 규칙을 찾자
 * 
 * 누적합으로 풀어야 되는 이유
 * 이중 for문을 사용하면 N이 1024까지이므로 1024x1024 = 1,000,000 이상이고
 * M개만큼 범위를 구해야 하는데 M의 범위가 10만까지이므로
 * 1,000,000 x 10만 -> 시간초과난다!
 * 
 * @author YooSejin
 *
 */

public class BJ_11660_구간합구하기5 {
	
	static int N; // 표의 크기
	static int M; // 합을 구해야 하는 횟수
	static int arr[][]; // NxN 표
	static int sum[][]; // 누적합 배열
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		arr = new int[N+1][N+1];
		sum = new int[N+1][N+1];
		
		for(int i=1; i<=N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=1; j<=N; j++) {
				arr[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		// 누적합 구하기
		for(int i=1; i<=N; i++) {
			for(int j=1; j<=N; j++) {
				sum[i][j] = sum[i][j-1] + sum[i-1][j] - sum[i-1][j-1] + arr[i][j];
			}
		}
		
		// 누적합 배열 확인
//		for(int i=1; i<=N; i++) {
//			for(int j=1; j<=N; j++) {
//				System.out.print(sum[i][j] + " ");
//			}
//			System.out.println();
//		}
		
		for(int i=0; i<M; i++) {
			st = new StringTokenizer(br.readLine());
			int x1 = Integer.parseInt(st.nextToken());
			int y1 = Integer.parseInt(st.nextToken());
			int x2 = Integer.parseInt(st.nextToken());
			int y2 = Integer.parseInt(st.nextToken());
			
			int answer = sum[x2][y2] - sum[x1-1][y2] - sum[x2][y1-1] + sum[x1-1][y1-1];
			System.out.println(answer);
		}
		
	}

}
