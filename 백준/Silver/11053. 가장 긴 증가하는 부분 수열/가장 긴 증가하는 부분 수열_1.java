import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * <BJ_11053_가장긴증가하는부분수열>
 * BJ_11055_가장큰증가하는부분수열과 같은 유형의 문제!!
 * 
 * 일단 DP임을 아는 방법
 * 1. 최적 부분 구조 : 30까지 각 인덱스마다 가장 긴 부분 수열의 길이를 알고 있으면 다음 숫자와 비교해 증가하는지만 확인하면 된다.
 * 2. 그리디 아님 : {100, 10, 20, 30} 처럼 앞에 더 큰 숫자가 올 때를 생각하면 100보다 큰 수를 찾는 것 보다 10 20 30이 더 길이가 길다.
 * 
 * @author YooSejin
 *
 */

public class Main {
	
	static int N;
	static int[] arr;
	static int[] dp;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		
		N = Integer.parseInt(br.readLine());
		
		arr = new int[N];
		dp = new int[N];
		
		st = new StringTokenizer(br.readLine());
		for(int i=0; i<N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		
		dp[0] = 1;
		
		for(int i=1; i<N; i++) {
			dp[i] = 1; // 기준점보다 이전의 수가 다 크다면 증가 수열을 만족하지 않는다. 그러면 부분 수열의 길이는 1로 초기화해주어야 한다.
			for(int j=0; j<N; j++) {
				if(arr[j] < arr[i]) {
					dp[i] = Math.max(dp[j] + 1, dp[i]);
				}
			}
		}
		
		int max = 0;
		for(int i=0; i<N; i++) {
			max = Math.max(max, dp[i]);
		}
		System.out.println(max);
	}

}
