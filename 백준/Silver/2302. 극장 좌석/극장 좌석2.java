import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/**
 * <BJ_2302_극장좌석> - 더 효율적인 풀이
 * 항상 N이 1일 때를 주의하자!!!
 * 아니면 애초에 dp 크기를 전체 범위만큼 선언해주기!!
 * 
 * 시간 잡아먹은 부분 : N=1일 때 처리. 2번이 고정석인 경우는 고려했는데 1번이 고정석인 경우는 고려못함.
 * 1번과 2번이 모두 고정석인 경우 vip 리스트에서 1,2를 모두 제거해버리면 3번 자리 계산이 제대로 안됨을 간과함.
 * 반례 -> 5 2 1 2 -> 정답 : 3 / 출력값 : 5
 * 
 * <더 간단한 풀이>
 * 고정석을 기준으로 구간을 나눠 가능한 경우의 수를 곱해주면 된다.
 * dp[0] = 1로 초기화해야 한다.
 * 반례 주의 -> 2 1 2 -> 그러면 answer *= dp[N - beforeSeat]; 을 실행할 때 answer에 dp[0](0)을 곱해 답이 1이 아니라 0이 된다.
 * 
 * @author YooSejin
 *
 */

public class Main {
	
	static int N; // 좌석의 개수
	static int M; // 고정석의 개수
	static int[] dp;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		N = Integer.parseInt(br.readLine());
		M = Integer.parseInt(br.readLine());
		
		dp = new int[41];
		
		// dp[i] : 좌석이 i개일 때 가능한 경우의 수
		// 일단 고정좌석을 고려하지 않고 계산한다.
		dp[0] = 1; // 주의!! -> answer에 dp[0]을 곱해주는 경우가 있기 때문
		dp[1] = 1;
		dp[2] = 2;
		
		for(int i=3; i<=40; i++) {
			dp[i] = dp[i-1] + dp[i-2];
		}
		
		// vip 좌석을 기준으로 구간을 나눠 가능한 경우의 수를 곱해준다.
		int answer = 1;
		int beforeSeat = 0;
		for(int i=0; i<M; i++) {
			int vip = Integer.parseInt(br.readLine());
			answer *= dp[vip - beforeSeat - 1];
			beforeSeat = vip;
		}
		answer *= dp[N - beforeSeat];
		
		System.out.println(answer);
	}

}
