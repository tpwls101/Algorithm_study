import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * <BJ_20922_겹치는건싫어>
 * 투포인터 문제
 * 투포인터는 맨 앞과 맨 뒤에 시작할수도, 둘 다 맨 앞에서 시작할 수도 있다.
 * 
 * 이중 for문 돌리면 N이 20만까지이므로 O(N^2) -> 시간초과!
 * 
 * @author YooSejin
 *
 */

public class Main {
	
	static int N; // 수열의 길이
	static int K; // 같은 원소는 K개 이하까지만 가능
	static int[] arr;
	static int[] num = new int[100001];
	static int answer = 0;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		
		arr = new int[N];
		
		st = new StringTokenizer(br.readLine());
		for(int i=0; i<N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		
		int start = 0;
		int end = 0;
		
		int len = 0;
		while(end < N) {
			if(num[arr[end]]+1 > K) {
				num[arr[start]]--;
				len--;
				start++;
				continue;
			}
			num[arr[end]]++;
			len++;
			end++;
			
			answer = Math.max(answer, len); // 최장 연속 부분 수열의 길이 갱신
		}
		
		System.out.println(answer);
	}

}