import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * <BJ_2559_수열> - 누적합 사용 방법
 * 단순 for문 돌려서 합을 구하고 max값을 찾으면 시간초과 나는 문제
 * N의 범위는 10만까지
 * 5만개의 합을 5만번 구한다 생각하면 -> 5만 x 5만 = 25억 (시간초과)
 * 
 * 누적합을 이용한다!
 * N의 범위가 10만이므로 누적합 저장하는데 최대 10만
 * 그리고 연속된 합의 최댓값을 구하는데 다시 최대 10만
 * 시간 내에 충분히 가능하다!
 * 
 * 주의사항 : max의 초기값은 Integer.MIN_VALUE로 지정해야 한다.
 * 온도가 마이너스도 있기 때문에 처음 최댓값이 음수이고, max를 0으로 초기화하면 max가 0이 되기 때문!
 * 이는 K일 동안의 합이 아니다!
 * 
 * @author YooSejin
 *
 */

public class Main {
	
	static int N; // 전체 날짜의 수
	static int K; // 연속적인 날짜의 수
	static int[] arr; // 누적합을 저장할 배열
	static int max = Integer.MIN_VALUE; // 연속적인 K일의 온도의 합의 최댓값
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
		
        arr = new int[N];
        
        // 누적합 구하기
        st = new StringTokenizer(br.readLine());
        arr[0] = Integer.parseInt(st.nextToken());
        for(int i=1; i<N; i++) {
        	int num = Integer.parseInt(st.nextToken());
        	arr[i] = arr[i-1] + num;
        }
        
        // 연속된 K일의 합의 최댓값 구하기
		max = Math.max(max, arr[K-1]);
		for(int i=K; i<N; i++) {
			max = Math.max(max, arr[i] - arr[i-K]);
		}
		
		System.out.println(max);
	}

}