import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BJ_12865_평범한배낭 {
	
	static int N; // 물품의 수
	static int K; // 준서가 버틸 수 있는 무게
	static int[] weight;
	static int[] value;
	static int[][] dp;
	
	public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
		
        weight = new int[N+1];
        value = new int[N+1];
        
        for(int i=1; i<=N; i++) {
        	st = new StringTokenizer(br.readLine());
        	weight[i] = Integer.parseInt(st.nextToken());
        	value[i] = Integer.parseInt(st.nextToken());
        }
		
        dp = new int[N+1][K+1];
        
        for(int i=1; i<=N; i++) { // i번째 물건까지 넣을 수 있고
        	for(int w=1; w<=K; w++) { // w 무게만큼 넣을 수 있다면
        		if(w < weight[i]) { // w가 i번째 물건의 무게보다 작다면 안담는 경우밖에 없음
        			dp[i][w] = dp[i-1][w];
        		} else {
        			dp[i][w] = Math.max(dp[i-1][w], dp[i-1][w-weight[i]] + value[i]); // i번째 물건을 담느냐 안담느냐를 비교
        		}
        	}
        }
        
        System.out.println(dp[N][K]);
	}

}
