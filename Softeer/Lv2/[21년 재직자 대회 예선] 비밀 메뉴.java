import java.io.*;
import java.util.*;

public class Main {

    static int M; // 비밀 메뉴 조작법의 버튼 수
    static int N; // 사용자가 누른 버튼 수
    static int K; // 버튼은 1~K까지의 수만 가능
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        M = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        String secretMenu = "";
        st = new StringTokenizer(br.readLine());
        for(int i=0; i<M; i++) {
            secretMenu += st.nextToken();
        }

        String userButton = "";
        st = new StringTokenizer(br.readLine());
        for(int i=0; i<N; i++) {
            userButton += st.nextToken();
        }

        if(userButton.contains(secretMenu)) {
            System.out.println("secret");
        } else {
            System.out.println("normal");
        }
    }
}
