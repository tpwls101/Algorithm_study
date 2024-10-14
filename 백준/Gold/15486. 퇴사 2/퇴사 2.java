import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * <BJ_15486_퇴사2>
 * 상담이 끝난 후 돈을 받는다!
 * 1일차, 2일차, ... , N일차 차례대로 상담을 한다고 가정했을 때 Ti일 후 수입이 생긴다.
 * 따라서 dp[N+Ti] = Math.max(dp[N+Ti], max + Pi)
 * -> dp 배열에 수입 저장하고 최대로 계속 갱신하기!
 * 
 * 단, 주의할 점 !
 * 마지막 날에도 일을 할 수 있으니(T가 1인 경우)
 * dp[N+1]까지 만들어야 한다.
 * 1 ~ N+1일 사용할 것이므로 0포함 -> N+2
 * 
 * @author YooSejin
 *
 */

public class Main {

	static int N; // 퇴사 전까지 남은 날
	static int[] time; // 상담 시간을 저장할 배열
	static int[] price; // 금액을 저장할 배열	
	static int[] dp; // N일차에 생기는 최대 이익을 갱신해 저장
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		
		N = Integer.parseInt(br.readLine());
		
		time = new int[N+2];
		price = new int[N+2];
		dp = new int[N+2]; // 마지막 날 Ti가 1이여서 일을 해 담날 수입이 생기는 것을 고려 (0번은 사용)
		
		for(int i=1; i<=N; i++) {
			st = new StringTokenizer(br.readLine());
			time[i] = Integer.parseInt(st.nextToken());
			price[i] = Integer.parseInt(st.nextToken());
		}
		
		int max = 0; // i일까지의 최대 수익
		
		for(int i=1; i<=N+1; i++) {
			// i일까지의 최대 수익 갱신
			if(dp[i] > max) {
//				System.out.println(i + "일 : dp[i]=" + dp[i] + " / max=" + max);
				max = dp[i];
//				System.out.println("max = " + max);
			}
			
			int day = i + time[i]; // i일차에 상담을 하고 수익이 생기는 날
			if(day > N+1) continue;
//			dp[day] = Math.max(dp[day], dp[i] + price[i]); // dp[i]가 아니라 i일까지의 최대 수익에 수입을 더해야 함!
			dp[day] = Math.max(dp[day], max + price[i]);
		}
		
//		for(int i=0; i<dp.length; i++) {
//			System.out.print(dp[i] + " ");
//		}
		
		System.out.println(dp[N+1]);
	}
	
}
