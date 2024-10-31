import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * <BJ_2559_수열> - 투 포인터 사용 방법
 * 단순 for문 돌려서 합을 구하고 max값을 찾으면 시간초과 나는 문제
 * N의 범위는 10만까지
 * 5만개의 합을 5만번 구한다 생각하면 -> 5만 x 5만 = 25억 (시간초과)
 * 
 * 1. 누적합을 이용한다!
 * N의 범위가 10만이므로 누적합 저장하는데 최대 10만
 * 그리고 연속된 합의 최댓값을 구하는데 다시 최대 10만
 * 시간 내에 충분히 가능하다!
 * 
 * 주의사항 : max의 초기값은 Integer.MIN_VALUE로 지정해야 한다.
 * 온도가 마이너스도 있기 때문에 처음 최댓값이 음수이고, max를 0으로 초기화하면 max가 0이 되기 때문!
 * 이는 K일 동안의 합이 아니다!
 * 
 * 2. 슬라이딩 윈도우
 * 부분 배열의 길이가 고정적이므로 앞의 값은 빼주고 뒤에 값은 더해주면 부분 배열의 합이 나온다.
 * 
 * 3. 투 포인터
 * start = 0, end = start+K-1
 * start와 end의 위치를 바꿔주며 합을 구하면 된다.
 * 
 * @author YooSejin
 *
 */

public class BJ_2559_수열4 {
	
	static int N; // 전체 날짜의 수
	static int K; // 연속적인 날짜의 수
	static int[] arr; // 측정한 온도를 저장하기 위한 배열
	static int max = Integer.MIN_VALUE; // 연속적인 K일의 온도의 합의 최댓값
	
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
        
        // 투포인터 -> 슬라이딩 윈도우 방식과 같게 진행하면 된다(투포인터의 부분 배열의 길이는 가변적이지만 여기서는 길이가 K로 고정되어 있기 때문)
        // 앞에 값은 빼주고 뒤에 값은 더해준다
        int start = 0;
        int end = start + K - 1;
        
        int sum = 0;
        for(int i=start; i<=end; i++) {
        	sum += arr[i];
        }
        max = Math.max(max, sum);
        
        for(int i=0; i<N-K; i++) {
        	start++;
        	end++;
        	sum -= arr[start-1];
        	sum += arr[end];
        	max = Math.max(max, sum);
        }
        
		System.out.println(max);
	}

}
