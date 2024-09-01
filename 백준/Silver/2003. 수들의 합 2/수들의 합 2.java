import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * <BJ_2003_수들의합2>
 * 누적합
 * N의 범위가 10,000까지이므로 이중 for문 돌려도 괜찮다!
 * 
 * @author YooSejin
 *
 */

public class Main {

	static int N; // N개의 수
	static int M; // i번째 수부터 j번째 수까지의 합이 M
	static int[] arr; // 누적합을 저장할 배열
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		arr = new int[N+1];
		
		st = new StringTokenizer(br.readLine());
		for(int i=1; i<=N; i++) {
			arr[i] = arr[i-1] + Integer.parseInt(st.nextToken());
		}
		
		int count = 0;
		for(int i=1; i<=N; i++) {
			for(int j=i; j<=N; j++) {
				if(arr[j] - arr[i-1] == M) {
					count++;
				}
			}
		}
		
		System.out.println(count);
	}

}