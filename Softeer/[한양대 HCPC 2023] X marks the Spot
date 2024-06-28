import java.io.*;
import java.util.*;

/**
‼ Java로 문자열 구현 시 시간초과를 항상 염두에 둘 것 ‼
‼ 문자열 이어붙이기 → ‘+’ 보다는 StringBuilder를 사용하자 ‼
*/

public class Main {

    static int N; // 문자열 쌍의 개수
    static String arr[][];
    
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        arr = new String[N][2];

        for(int i=0; i<N; i++) {
            String[] s = br.readLine().split(" ");
            arr[i][0] = s[0].toUpperCase();
            arr[i][1] = s[1].toUpperCase();
        }

        StringBuilder sb = new StringBuilder();
        for(int i=0; i<N; i++) {
            int position = arr[i][0].indexOf('X'); // X의 위치 인덱스
            String c = arr[i][1].substring(position, position + 1);
            sb.append(c);
        }

        System.out.println(sb);
    }
}
