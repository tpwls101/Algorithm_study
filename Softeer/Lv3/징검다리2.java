import java.io.*;
import java.util.*;

/**
주의할 점 : 돌을 꼭 가장 첫 번째 것부터 밟을 필요가 없다!!
유형 : 최장 증가 부분 수열, DP

dp에는 i번째 인덱스에서 끝나는 최장 증가 부분 수열의 길이를 저장한다.
주의할 점은 i번째 인덱스까지의 최장 증가 부분 수열의 길이가 아니다.
예를 들어
arr[] = { 3 1 5 6 7 2 10 } 이라고 하면
dp[] =  { 1 1 2 3 4 2 5 } 인데
arr[i]의 값이 2인 부분을 보면
2로 끝나는 최장 증가 부분 수열의 길이를 저장한 것이다. 
{ 1 2 } -> 따라서 최장 증가 부분 수열의 길이는 2
*/

public class Main {

    static int N; // 돌의 개수
    static int arr[]; // 돌의 높이를 저장할 배열
    static int dp[]; // i번째 인덱스에서 끝나는 최장 증가 부분 수열의 길이를 저장
    
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

        // i번째 인덱스에서 끝나는 최장 증가 부분 수열의 길이 구하기
        for(int i=0; i<N; i++) {
            for(int k=0; k<i; k++) {
                // 더 큰 수를 만나면 최장 증가 부분 수열의 길이 업데이트
                if(arr[k] < arr[i]) {
                    dp[i] = Math.max(dp[i], dp[k]+1);
                }
            }
        }

        // dp의 마지막 인덱스에 가장 큰 값이 들어있는 것이 아니다.
        // i번째 인덱스에서 끝나는, 즉, 부분 수열이 arr[i]로 끝날 때 최장 증가 부분 수열의 길이를 저장한 것이므로 dp를 돌면서 max 값을 찾아준다.
        int max = 0;
        for(int i=0; i<N; i++) {
            max = Math.max(max, dp[i]);
        }

        System.out.println(max);
    }
}
