import java.io.*;
import java.util.*;

/**
등수를 어떻게 출력할 것인가?

시간 줄이기 포인트 : sysout 대신 StringBuilder를 사용하니 1초 넘던 테케를 없앨 수 있었다! 다 0.x초로!
*/

public class Main {
    
    static int N; // 참가자의 수
    static int arr[][]; // 각 대회에서 참가자들의 점수를 저장할 배열
    static int[] score; // 각 점수별로 몇 명이 있는지 저장할 배열
    static int[] dp; // 등수를 출력하기 위한 dp (dp[i] : 내림차순으로 i번째까지 누적된 사람 수)
    static int[] sum; // 점수의 합을 저장할 배열
    
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());

        arr = new int[3][N];
        sum = new int[N];

        // 입력값 받기
        for(int i=0; i<3; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j=0; j<N; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
                sum[j] += arr[i][j];
            }
        }

        StringBuilder sb = new StringBuilder();
        
        for(int i=0; i<3; i++) {

            // 배열 초기화
            score = new int[1001];
            dp = new int[1001];
            
            // 각 점수를 받은 사람이 몇 명인지 저장
            for(int j=0; j<N; j++) {
                score[arr[i][j]]++;
            }
    
            // DP에 지금까지 누적된 사람의 수 저장
            // 즉, xx점 이상의 점수를 받은 사람 수
            // 예를 들어, dp[80]이면 만점부터 80점까지 누적된 사람의 수
            dp[1000] = score[1000];
            for(int j=999; j>=0; j--) {
                dp[j] = dp[j+1] + score[j];
            }

            // 등수 출력
            // 등수 = 나보다 점수가 큰 사람의 수 + 1
            for(int j=0; j<N; j++) {
                int scr = arr[i][j];
                if(scr == 1000) {
                    sb.append("1 ");
                    continue;
                }
                sb.append(dp[scr+1] + 1 + " ");
            }
            sb.append("\n");
            
        }

        // 참가자의 최종 등수 계산
        score = new int[3001];
        dp = new int[3001];

        for(int i=0; i<N; i++) {
            score[sum[i]]++;
        }

        dp[3000] = score[3000];
        for(int i=2999; i>=0; i--) {
            dp[i] = dp[i+1] + score[i];
        }

        for(int i=0; i<N; i++) {
            int scr = sum[i];
            if(scr == 3000) {
                sb.append("1 ");
                continue;
            }
            sb.append(dp[scr+1] + 1 + " ");
        }

        System.out.println(sb);
    }
}
