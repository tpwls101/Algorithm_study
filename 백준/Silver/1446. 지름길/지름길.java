import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;
import java.util.StringTokenizer;

/**
 * <BJ_1446_지름길>
 * 처음에 문제 유형에 대해 고민했음
 * 일단 그리디는 아니고, DFS도 약간 애매한 느낌
 * 근데 거리의 최솟값을 구하는 문제이므로 DP로 접근
 * i까지의 거리의 최솟값을 갱신해 저장하자
 * 
 * @author YooSejin
 *
 */

public class Main {
	
	static int N; // 지름길의 개수
	static int D; // 고속도로의 길이
	static int[][] arr; // 지름길 정보
	static int[] dp;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		D = Integer.parseInt(st.nextToken());
		
		arr = new int[N][3];
		
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0; j<3; j++) {
				arr[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		dp = new int[D+1];
		
		for(int i=0; i<=D; i++) {
			dp[i] = i; // i까지 갈 수 있는 최소 거리를 i로 초기화
		}
		
		for(int i=1; i<=D; i++) {
			// 앞에서 지름길로 왔을 수 있으니 i-1까지의 최소 거리에 +1한 값과 비교해 최소로 거리 갱신
			dp[i] = Math.min(dp[i], dp[i-1] + 1);
			
			// i로 오는 지름길이 있다면 최소 거리 비교해 갱신
			for(int j=0; j<N; j++) {
				if(arr[j][1] == i) {
					dp[i] = Math.min(dp[i], dp[arr[j][0]] + arr[j][2]);
				}
			}
		}
		
		System.out.println(dp[D]);
	}

}
