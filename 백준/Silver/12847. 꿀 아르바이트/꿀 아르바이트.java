import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * <BJ_12847_꿀아르바이트>
 * 슬라이딩 윈도우 문제
 * n일 중에 m일을 일 할 수 있는데 무조건 연속으로 해야한다.
 * 근데 이중for문을 사용하면 n의 범위가 10만까지이므로 시간초과난다.
 * 따라서 슬라이딩 윈도우를 사용할 것!
 * 
 * 일급의 범위가 100만까지이고 10만일까지 일 할 수 있으므로 이익의 합과 최대값의 타입을 long으로 써야한다!!
 * 
 * @author YooSejin
 *
 */

public class Main {
	
	static int N;
	static int M;
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
		
		long sum = 0;
		long max = 0;
		
		for(int i=0; i<M; i++) {
			sum += arr[i];
		}
		max = Math.max(max, sum);
		
		for(int i=1; i<=N-M; i++) {
			sum -= arr[i-1];
			sum += arr[i+M-1];
			max = Math.max(max, sum);
		}
		
		System.out.println(max);
	}

}
