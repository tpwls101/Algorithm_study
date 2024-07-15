import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 누적합 문제 Tip!!
 * (S~E까지의 합) = (1~E까지의 합) - (1~S-1까지의 합)
 * 
 * 주의사항 : 입력값의 범위가 10만까지이므로 이중 for문 돌리면 시간초과!!
 * 
 * @author YooSejin
 *
 */

public class Main {

	static int N; // 수의 개수
	static int M; // 합을 구해야 하는 횟수
	static int[] arr; // 수를 저장할 배열, 그리고 누적합을 저장할 배열
	
	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		arr = new int[N+1];
		
		st = new StringTokenizer(br.readLine());
		for(int i=1; i<=N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		
		// 배열에 누적합 세팅
		for(int i=1; i<=N; i++) {
			arr[i] = arr[i-1] + arr[i];
		}
		
		for(int i=0; i<M; i++) {
			st = new StringTokenizer(br.readLine());
			int s = Integer.parseInt(st.nextToken());
			int e = Integer.parseInt(st.nextToken());
			
			System.out.println(arr[e] - arr[s-1]);
		}

	}

}
