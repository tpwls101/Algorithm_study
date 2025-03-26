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
 * 대략 이분탐색을 어떻게 이용해야할지에 대해서는 조금 알겠지만 -> 구하고자 하는 값을 임의로 두고(mid) 조건에 맞는지 확인
 * 아직 최대/최소를 구하고 while문을 빠져나오는 부분이 명확하게 감이 안잡힌다.
 * 이 문제의 경우에는 상한선의 최댓값을 구해야 하므로
 * 예산의 합보다 주어진 예산이 크거나 같다면 상한선을 올리도록 했다.
 * 다만 이미 임의의 값 mid가 최대 상한선인 경우(127)에도 left = mid + 1 하며 상한선을 올리기 때문에 left는 128이 된다.
 * 다시 상한선이 올라갔다 내려온다한들 left가 127보다 큰 128이기 때문에 mid값은 127이 나올 수 없다.
 * 따라서 mid-1을 해 준 right 값을 상한선으로 리턴하면 최대 상한선 값을 구할 수 있다!
 * 이해하기 어렵지만 직접 임의의 상한선과 left, right 값을 찍어보면 이해하는데 도움이 될 것이다.
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
