import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/**
 * <BJ_2302_극장좌석> - 내가 푼 풀이
 * 항상 N이 1일 때를 주의하자!!!
 * 아니면 애초에 dp 크기를 전체 범위만큼 선언해주기!!
 * 
 * 시간 잡아먹은 부분 : N=1일 때 처리. 2번이 고정석인 경우는 고려했는데 1번이 고정석인 경우는 고려못함.
 * 1번과 2번이 모두 고정석인 경우 vip 리스트에서 1,2를 모두 제거해버리면 3번 자리 계산이 제대로 안됨을 간과함.
 * 반례 -> 5 2 1 2 -> 정답 : 3 / 출력값 : 5
 * 
 * @author YooSejin
 *
 */

public class Main {
	
	static int N; // 좌석의 개수
	static int M; // 고정석의 개수
	static List<Integer> vip;
	static long[] dp;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		N = Integer.parseInt(br.readLine());
		M = Integer.parseInt(br.readLine());
		
		vip = new ArrayList<>();
		dp = new long[41];
		
		for(int i=0; i<M; i++) {
			int seatNum = Integer.parseInt(br.readLine());
			vip.add(seatNum);
		}
		
		// dp[i] : 좌석이 i개일 때 가능한 경우의 수
		dp[1] = 1;
		
		// 1번이나 2번이 고정석이면, 혹은 둘 다 고정석이면 가능한 경우는 하나 -> 12
		// 2번 자리가 고정석인 경우 3번 자리를 계산할 때 자리 바꿈이 안되므로 확인하기 위해 vip 리스트에서 제거하면 안됨
		if(vip.size() != 0 && vip.get(0) == 1 && vip.get(1) == 2) {
			dp[2] = 1;
			vip.remove(Integer.valueOf(1));
		} else if(vip.size() != 0 && vip.get(0) == 1) {
			dp[2] = 1;
			vip.remove(Integer.valueOf(1));
		} else if(vip.size() != 0 && vip.get(0) == 2) {
			dp[2] = 1;
		} else { // 둘 다 고정석이 아니면 두가지 경우 가능 -> 12/21
			dp[2] = 2;
		}
		
		for(int i=3; i<=40; i++) {
			// 고정석과 고정석 다음 좌석은 왼쪽 좌석과 자리를 바꿀 수 없다.
			if(vip.size() != 0 && vip.get(0) == i) {
				dp[i] = dp[i-1];
				continue;
			} else if(vip.size() != 0 && vip.get(0) + 1 == i) {
				dp[i] = dp[i-1];
				vip.remove(0);
				continue;
			}
			dp[i] = dp[i-1] + dp[i-2]; // 좌석이 i-1개일 때 가능한 경우에 i번 좌석 추가 + 좌석이 i-2개일 때 가능한 경우에 i와 i-1 좌석을 바꿔서 추가
		}
		
		System.out.println(dp[N]);
	}

}
