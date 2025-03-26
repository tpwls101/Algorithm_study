import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * <BJ_2512_예산>
 * 임의의 상한선을 정한 뒤 모든 지방에 만족하는지 일일이 for문 돌리면
 * 10^4 x 10^5 = 10^9 이므로 시간초과난다.
 * 따라서 이분탐색을 이용해 상한선을 정한 후 조건에 맞는지 탐색해야 한다.
 * 
 * @author YooSejin
 *
 */

public class Main {
	
	static int N; // 지방의 수
	static int[] req; // 예산 요청 금액
	static int M; // 주어진 총 예산
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		
		N = Integer.parseInt(br.readLine());
		
		req = new int[N];

		int sum = 0;
		st = new StringTokenizer(br.readLine());
		for(int i=0; i<N; i++) {
			req[i] = Integer.parseInt(st.nextToken());
			sum += req[i];
		}
		
		M = Integer.parseInt(br.readLine());
		
		if(sum > M) { // 모든 예산 요청을 배정할 수 없으면 상한선을 정하고
			System.out.println(binarySearch());
		} else { // 모든 예산 요청을 배정해 줄 수 있으면 요청 금액 그대로 배정한다.
			Arrays.sort(req);
			System.out.println(req[N-1]); // 배정된 예산들 중 최댓값 구하기
		}
	}
	
	// 상한선 구하기
	static int binarySearch() {
		int left = 0;
		int right = M;
		int mid = 0; // 임의의 상한선
		
		while(left <= right) {
			mid = (left + right) / 2;
			
			int sum = 0;
			for(int i=0; i<N; i++) {
				if(mid >= req[i]) sum += req[i];
				else sum += mid;
			}
			
			if(sum <= M) { // 배정할 예산을 더한 값보다 주어진 예산이 더 크면 상한선 올리기
				left = mid + 1;
			} else { // 배정할 예산을 더한 값이 주어진 예산보다 크면 상한선 내리기
				right = mid - 1;
			}
		}
		
		return right;
	}

}
