import java.io.*;
import java.util.*;

/**
그리디
*/

public class Main {

    static int N; // 강의 개수
    static int[][] arr; // 강의 시작 시간과 종료시간을 저장할 배열
    static int answer = 0; // 최대 강의 수
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;

        N = Integer.parseInt(br.readLine());

        arr = new int[N][2];
        
        for(int i=0; i<N; i++) {
            st = new StringTokenizer(br.readLine());
            arr[i][0] = Integer.parseInt(st.nextToken());
            arr[i][1] = Integer.parseInt(st.nextToken());
        }

        Arrays.sort(arr, (o1, o2) -> o1[1] - o2[1]); // 끝나는 시간을 기준으로 오름차순 정렬

        int currentTime = 0;
        for(int i=0; i<N; i++) {
            if(currentTime <= arr[i][0]) {
                answer++;
                currentTime = arr[i][1];
            }
        }

        System.out.println(answer);
    }
}
