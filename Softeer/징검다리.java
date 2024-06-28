import java.io.*;
import java.util.*;

/**
주의할 점 : 돌을 꼭 가장 첫 번째 것부터 밟을 필요가 없다!!
유형 : 최장 증가 부분 수열, DP
*/

public class Main {

    static int N; // 돌의 개수
    static int arr[]; // 돌의 높이를 저장할 배열
    static int dp[]; // 각 인덱스마다 최장 증가 부분 수열의 길이를 저장
    
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        arr = new int[N];
        dp = new int[N];
        Arrays.fill(dp, 1); // dp[] 1로 초기화 (최장 증가 부분 수열의 길이)

        st = new StringTokenizer(br.readLine());
        for(int i=0; i<N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        for(int k=0; k<N; k++) {
            for(int i=0; i<k; i++) {
                // 더 큰 수를 만나면 최장 증가 부분 수열의 길이 업데이트
                if(arr[i] < arr[k]) {
                    dp[k] = Math.max(dp[k], dp[i]+1);
                }
            }
        }

        int answer = 0;
        for(int i=0; i<N; i++) {
            answer = Math.max(answer, dp[i]);
        }

        System.out.println(answer);
    }
}
