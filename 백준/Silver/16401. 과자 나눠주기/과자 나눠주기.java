import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * <BJ_16401_과자나눠주기>
 * 막대과자의 최대 길이 구하는 문제
 * 이분탐색
 * 임의로 막대과자의 길이(mid)를 설정해서 조건에 맞는지 확인하기
 * 주의할 점은 모든 조카에게 같은 길이의 막대과자를 줄 수 없다면 0을 출력해야 한다.
 * 모든 과자의 길이를 더한 값이 조카의 수보다 크다면 적어도 1씩은 모두에게 똑같이 나눠줄 수 있다.
 * 따라서 모두에게 똑같이 줄 수 없는 경우는 모든 막대과자 길이의 합이 조카의 수보다 작을 때이다.
 * 
 * @author YooSejin
 *
 */

public class Main {
	
	static int M; // 조카의 수
	static int N; // 과자의 수
	static int[] snack; // 과자의 길이
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        M = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());
		
        snack = new int[N];
        
        int max = 0;
        st = new StringTokenizer(br.readLine());
        for(int i=0; i<N; i++) {
        	snack[i] = Integer.parseInt(st.nextToken());
        	max = Math.max(max, snack[i]);
        }
		
        int left = 1;
        int right = max;
        int mid = 0; // 임의의 막대 과자 길이
        
        while(left <= right) {
        	mid = (left + right) / 2;
        	
        	int cnt = 0; // 줄 수 있는 과자 개수 = 줄 수 있는 조카 수
        	for(int i=0; i<N; i++) {
        		cnt += snack[i] / mid;
        	}
        	
        	if(cnt >= M) { // 더 많은 조카에게 줄 수 있으면 막대 과자의 길이 늘리기
        		left = mid + 1;
        	} else { // 조카에게 다 줄 수 없으면 막대 과자의 길이 줄이기
        		right = mid - 1;
        	}
        }
        
        System.out.println(right);
	}

}
