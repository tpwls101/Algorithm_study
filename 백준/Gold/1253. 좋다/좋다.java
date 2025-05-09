import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * <BJ_1253_좋다>
 * 3중 for문 돌리면 N의 범위가 최대 2,000이므로 시간초과.
 * 따라서 투 포인터를 사용했다.
 * 먼저 수를 오름차순으로 정렬한다.
 * 그리고 양 끝을 포인터로 사용하고, 두 수의 합이 만들고자 하는 수보다 작다면 합을 더 키워야 하므로 start 포인터를 증가시킨다.
 * 반대로 두 수의 합이 만들고자 하는 수보다 크다면 합을 줄여야 하므로 end 포인터를 감소시킨다.
 * 포인터가 내가 찾고자 하는 수를 가리킬 때는 패스한다.
 * 단, 주의할 점은 같은 수가 존재하므로 값이 아니라 인덱스를 비교해야 한다!!
 * 
 * @author YooSejin
 *
 */

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		
		int N = Integer.parseInt(br.readLine());
		
		int[] arr = new int[N];
		
		st = new StringTokenizer(br.readLine());
		for(int i=0; i<N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		
		Arrays.sort(arr);
		
		int answer = 0;
		
		for(int i=0; i<N; i++) {
			int start = 0;
			int end = N-1;
			
			while(start < end) {
				// 주의사항 : arr[start]와 arr[i]의 값이 같은지 비교하면 안된다.
				// 같은 수가 나올 수 있지만 위치가 다르면 다른 수로 간주한다.
				// arr[start]와 arr[i](만드려는 수)가 같은지 비교하면 둘 다 예를 들어 10이라는 수이지만 다른 위치에 있을 수 있다!
				// 즉, 값이 아니라 인덱스로 비교해야 한다!
				// 추가로 start와 end 인덱스를 +/- 한 후에 바로 비교를 수행하면 start와 end가 같은 경우가 생길 수 있으므로 continue를 꼭 이 때 해주어야 한다.
				if(start == i) {
					start++;
					continue;
				} else if(end == i) {
					end--;
					continue;
				}
				
				int sum = arr[start] + arr[end];
				if(sum < arr[i]) start++;
				else if(sum > arr[i]) end--;
				else {
					answer++;
					break;
				}
			}
		}
		
		System.out.println(answer);
	}

}
