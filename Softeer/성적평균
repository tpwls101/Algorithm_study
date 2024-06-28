import java.io.*;
import java.util.*;

public class Main {

    static int N; // 학생 수
    static int K; // 구간 수
    static int grade[]; // 학생들의 성적을 저장할 배열
    static int start; // 구간 시작 지점
    static int end; // 구간 마지막 지점

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        grade = new int[N];

        st = new StringTokenizer(br.readLine());
        for(int i=0; i<N; i++) {
            grade[i] = Integer.parseInt(st.nextToken());
        }

        for(int i=0; i<K; i++) {
            st = new StringTokenizer(br.readLine());
            start = Integer.parseInt(st.nextToken());
            end = Integer.parseInt(st.nextToken());
            
            int sum = 0;
            for(int index=start-1; index<end; index++) {
                sum += grade[index];
            }
            double avg = (double)sum / (end - start + 1);
            System.out.println(String.format("%.2f", avg));
        }
        
    }
}
