import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * <BJ_17266_어두운굴다리>
 * 대략 시간초과 예상은 했지만 한번 풀어봄
 * 역시나 시간초과
 * 이분탐색으로 풀자
 * 
 * 매 높이마다 arr 배열을 다시 세팅하는건 시간초과난다.
 * 이전 가로등이 비춘 최대 위치와 현재 가로등의 최소 위치를 비교해서 빈 곳없이 비추고 있는지 확인해야 한다.
 * 
 * @author YooSejin
 *
 */

public class Main {
	
	static int N; // 굴다리의 길이
	static int M; // 가로등의 개수
	static int[] position; // 가로등의 위치
	static int[] arr;
	static int answer = 0; // 가로등의 최소 높이
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;
		
		N = Integer.parseInt(br.readLine());
		M = Integer.parseInt(br.readLine());
		
		arr = new int[N];
		position = new int[M];
		
		st = new StringTokenizer(br.readLine());
		for(int i=0; i<M; i++) {
			position[i] = Integer.parseInt(st.nextToken());
		}
		
		// 가로등의 높이가 가능한 범위
		int left = 1;
		int right = N;
		int mid = 0;
		
		while(left <= right) {
			mid = (left + right) / 2; // 가로등의 높이
			
			if(check(mid)) {
				answer = mid;
				right = mid - 1;
			} else {
				left = mid + 1;
			}
		}
		
		System.out.println(answer);
	}
	
	static boolean check(int height) {
		int prev = 0; // 이전 가로등이 비춘 위치(가장 오른쪽에 있는)
		
		for(int i=0; i<M; i++) {
			// 가로등이 비추는 최소 위치가 이전 최대 위치보다 작거나 같으면 빈 곳없이 비추고 있다는 뜻
			if(position[i] - height <= prev) {
				prev = position[i] + height;
			} else {
				return false;
			}
		}
		
		// 마지막 가로등의 오른쪽도 확인해줘야 함
		if(prev >= N) return true;
		else return false;
	}
	
}