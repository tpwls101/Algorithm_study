import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;

public class Main {
    
    // N에 대하여 각 0과 1이 호출된 횟수를 저장
    public static int[][] dp = new int[41][2]; // 0 <= 입력값 <= 40
    
	public static void main(String[] args) throws IOException {
		// 피보나치 함수 (재귀, Top-down 방식)
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		// 주의 - dp가 static 변수이므로 for문 안에서 매번 초기화될 필요 없음, 미리 초기화!
		dp[0][0] = 1; // N이 0일 때 0을 호출한 횟수
        dp[0][1] = 0; // N이 0일 때 1을 호출한 횟수
        dp[1][0] = 0; // N이 1일 때 0을 호출한 횟수
        dp[1][1] = 1; // N이 1일 때 1을 호출한 횟수
        
        // -1로 초기화, 탐색을 했는지 안했는지 확인하기 위해
        for(int a=2; a<=40; a++) {
        	for(int b=0; b<=1; b++) {
        		dp[a][b] = -1;
        	}
        }
		
		int T = Integer.parseInt(br.readLine()); // 테스트케이스의 개수
        
        for(int i=0; i<T; i++) {
            int N = Integer.parseInt(br.readLine()); // N번째 피보나치 수
            fibonacci(N);
            System.out.println(dp[N][0] + " " + dp[N][1]);
            
            /*
            StringBuilder sb = new StringBuilder();
            sb.append(dp[N][0] + " " + dp[N][1]);
            System.out.println(sb);
            */
        }
        
	}
	
	public static int[] fibonacci(int N) { // 자료형 주의 -> int형 배열
		// N에 대하여 탐색을 아직 하지 않은 경우
        if(dp[N][0] == -1 || dp[N][1] == -1) {
        	// 각 N에 대한 0 호출 횟수와 1 호출 횟수를 재귀호출한다.
        	dp[N][0] = fibonacci(N-1)[0] + fibonacci(N-2)[0];
        	dp[N][1] = fibonacci(N-1)[1] + fibonacci(N-2)[1];
        }
        
		return dp[N];
    }
}