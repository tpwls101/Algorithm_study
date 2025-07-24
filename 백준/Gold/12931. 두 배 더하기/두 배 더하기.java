import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * <BJ_12931_두배더하기>
 * 이런 경우 A->B를 만드는 것보다 B->A(모두 0인 배열)를 만드는 것이 더 좋다.
 * 그리고 모든 수가 0일 때 마무리되도록 하기 위해서 숫자를 하나하나 다 비교하는 것보다 sum 변수를 만들어 합을 이용하면 편하다.
 * sum이 0이 될 때까지 반복하면서 입력받은 배열의 수 중 홀수인 경우의 수를 구해 sum에서 빼주고 answer에는 더해준다.
 * +1 연산을 하나의 수에만 가능하기 때문이다.
 * 모든 수가 짝수라면 x2 연산이 가능하므로 answer++ 해주고(이 때는 한번만 수행) sum을 2로 나눠주고, 각 수도 2로 나눠준다.
 * 해당 연산을 반복하면 최소 연산의 횟수가 나온다.
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
		
		int sum = 0;
		int answer = 0;
		
		st = new StringTokenizer(br.readLine());
		for(int i=0; i<N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
			sum += arr[i];
		}
		
		while(sum != 0) {
			int cnt = 0; // 홀수의 개수
			
			for(int i=0; i<N; i++) {
				if(arr[i] % 2 == 1) {
					arr[i]--;
					cnt++;
				}
			}
			
			if(cnt > 0) { // 배열의 모든 수 중 홀수가 있다는 것
				sum -= cnt;
				answer += cnt;
			} else { // 모든 수가 다 짝수라면
				for(int i=0; i<N; i++) { // 모든 수를 다 2로 나눠주고
					arr[i] /= 2;
				}
				sum /= 2;
				answer++;
			}
		}
		
		System.out.println(answer);
	}

}
