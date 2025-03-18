import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * <BJ_1072_게임>
 * 타입에 주의하자!!!
 * int percent = win * 100 / total;
 * 수식을 이렇게 짜면 입력값의 범위가 10억까지이므로 x100을 했을 때 범위를 벗어나버린다.
 * 1) 따라서 percent와 win에 long 타입을 써주던가
 * 2) 정석대로 int percent = (int)((double)win / total * 100); 써주는 것이 좋다.
 * => 1번 방법이 계산이 더 정확하다고 한다!!
 * 
 * + 엣지케이스에 유의하자!!
 * 확률이 0일때와 100일 때를 고려해보자.
 * 확률이 0일 때는 그 뒤로 무조건 이기니까 상관이 없다. 점차 승률이 오름.
 * 다만 확률이 무조건 100이면 게임을 몇 판을 더해도 100이다.
 * 또한 확률이 99인 경우, 게임을 아무리 많이해도 절대 승률이 100이 될 수 없으므로 예외 처리 해줘야 한다.
 * 
 * 시간초과 주의!!
 * 처음에 푼 것처럼 반복문 돌려서 확률이 변하면 출력해도 되지만
 * 그렇게 하면 입력값의 범위가 10억까지라 최악의 경우 10억의 승리횟수까지 반복문을 실행해야 한다.(?)
 * 시간초과 날 수 있으니 이분탐색을 이용해야 한다.
 * 
 * @author YooSejin
 *
 */

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		long total = Integer.parseInt(st.nextToken());
		long win = Integer.parseInt(st.nextToken());
		long percent = win * 100 / total; // 소수점은 버림
		
		// 승률이 99퍼 이상이면 아무리 게임을 많이해도 절대 100이 될 수 없다.
		// 승률이 100이면 게임을 몇판을 해도 100이다.
		if(percent >= 99) {
			System.out.println(-1);
			return;
		}
		
		int left = 0;
		int right = 1000000000;
		int answer = -1;
		
		while(left <= right) {
			int mid = (left + right) / 2; // 추가 게임 횟수
			long tmp = (win + mid) * 100 / (total + mid); // 추가로 게임을 했을 때 승률
			
			if(percent >= tmp) {
				answer = mid + 1;
				left = mid + 1; // 임시 승률이 더 낮으면 게임 횟수 늘림
			} else {
				right = mid - 1; // 임시 승률이 더 높으면 최소가 될 때까지 게임 횟수 줄임
			}
		}
		
		System.out.println(answer);
	}

}
