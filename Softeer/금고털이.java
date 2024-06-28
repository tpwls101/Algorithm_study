import java.io.*;
import java.util.*;

/**
그리디 + 정렬
*/

public class Main {

    static int W; // 배낭의 무게
    static int N; // 귀금속의 종류
    static int arr[][]; // 금속의 무게와 무게당 가격을 저장할 배열
    static int maxPrice = 0; // 배낭에 담을 수 있는 가장 비싼 가격
    
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        W = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());

        arr = new int[N][2];

        for(int i=0; i<N; i++) {
            st = new StringTokenizer(br.readLine());
            arr[i][0] = Integer.parseInt(st.nextToken());
            arr[i][1] = Integer.parseInt(st.nextToken());
        }

        // 무게 당 가격을 기준으로 내림차순 정렬
        Arrays.sort(arr, (o1, o2) -> (o2[1] - o1[1]));

        // 정렬 후 확인
        // for(int i=0; i<N; i++) {
        //     System.out.println(arr[i][0] + " " + arr[i][1]);
        // }

        for(int i=0; i<N; i++) {
            if(arr[i][0] >= W) {
                maxPrice += W * arr[i][1];
                break;
            } else {
                maxPrice += arr[i][0] * arr[i][1];
                W -= arr[i][0];
            }
        }
        
        System.out.println(maxPrice);
    }
}
