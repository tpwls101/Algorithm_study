import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * <BJ_2003_수들의합2_방법2>
 * 투 포인터 사용 방식
 * 1. 두 포인터를 배열의 첫 번째 원소의 인덱스에 둔다.
 * 2. 두 포인터 사이에 있는 원소의 합이 M보다 작으면 끝 포인터를 증가시킨다.
 * 3. 두 포인터 사이에 있는 원소의 합이 M보다 크거나 같으면 시작 포인터를 증가시킨다.
 * 
 * 시간 복잡도 : O(N)
 * 
 * @author YooSejin
 *
 */
public class BJ_2003_수들의합2_방법2 {
	
	static int N; // N개의 수
	static int M; // i번째 수부터 j번째 수까지의 합이 M
	static int[] arr;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		arr = new int[N];
		
		st = new StringTokenizer(br.readLine());
		for(int i=0; i<N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		
		int start = 0; // 시작 포인터
		int end = 0; // 끝 포인터
		int count = 0;
		
		while(end < N) {
			int sum = 0;
			
			for(int i=start; i<=end; i++) {
				sum += arr[i];
			}
			
			if(sum == M) {
				count++;
				start++;
			} else if (sum < M) {
				end++;
			} else {
				start++;
			}
		}
		
		System.out.println(count);
	}

}
