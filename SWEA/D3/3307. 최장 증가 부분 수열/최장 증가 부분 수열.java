import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int T = Integer.parseInt(br.readLine()); // 테스트케이스 수
		
		for(int tc=1; tc<=T; tc++) {
			int N = Integer.parseInt(br.readLine()); // 수열의 길이
			
			int[] arr = new int[N]; // 수열을 저장할 배열
			
			StringTokenizer st = new StringTokenizer(br.readLine());
			for(int i=0; i<N; i++) {
				arr[i] = Integer.parseInt(st.nextToken());
			}
			
			// 최장 증가 부분 수열의 길이 구하기
			int[] LIS = new int[N]; // 최장 증가 부분 수열의 길이를 저장할 배열

			for(int i=0; i<N; i++) {
				LIS[i] = 1; // 처음 부분 수열의 길이 1로 초기화
				for(int j=0; j<=i-1; j++) {
					// 1. 현재 체크하는 원소 왼쪽에서 자기보다 작은 수 찾기 (증가수열이니까!)
					if(arr[j] < arr[i] && LIS[i] < LIS[j] + 1) {
						// 2. 1번 애들 중 LIS가 가장 큰 값에 +1 더해주기
						LIS[i] = LIS[j] + 1;
					}
				}
				//System.out.println("LIS[" + i + "] = " + LIS[i]);
			}
			
			int answer = Integer.MIN_VALUE;
			for(int i=0; i<N; i++) {
				answer = Math.max(answer, LIS[i]);
			}
			System.out.printf("#%d %d\n", tc, answer);
			
			
			
//			int[] LIS = new int[N]; // 최장 증가 부분 수열의 길이를 저장할 배열
//			int answer = 0;
//			
//			for(int i=0; i<N; i++) {
//				int max = 0;
//				
//				for(int j=0; j<=i-1; j++) {
//					// 1. 현재 체크하는 원소 왼쪽에서 자기보다 작은 수 찾기 (증가수열이니까!)
//					if(arr[j] < arr[i]) {
//						// 2. 1번 애들 중에 LIS가 가장 큰 값 구하기
//						max = Math.max(max, LIS[j]);
//					}
//				}
//				
//				// 3. LIS가 가장 큰 값에 +1 해준 값이 최장 증가 부분 수열의 길이
//				LIS[i] = max + 1;
//				answer = Math.max(answer, LIS[i]);
//			}
//			
//			System.out.printf("#%d %d\n", tc, answer);
			
		}
		
	}

}