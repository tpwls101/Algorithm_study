import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * <BJ_21921_블로그>
 * X일로 길이가 고정되어 있으므로 슬라이딩 윈도우 문제임을 알 수 있다.
 * 
 * @author YooSejin
 *
 */

public class Main {

	public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        int N = Integer.parseInt(st.nextToken());
        int X = Integer.parseInt(st.nextToken());
		
        int[] arr = new int[N];
        
        st = new StringTokenizer(br.readLine());
        for(int i=0; i<N; i++) {
        	arr[i] = Integer.parseInt(st.nextToken());
        }
        
        int sum = 0;
        int max = 0; // 최대 방문자 수
        int[] visitCnt = new int[N-X+1]; // 방문자 수 저장
        
        // 초기화
        for(int i=0; i<X; i++) {
        	sum += arr[i];
        }
        max = sum;
        visitCnt[0] = sum;
        
        for(int i=0; i<N-X; i++) {
        	sum -= arr[i];
        	sum += arr[i+X];
        	visitCnt[i+1] = sum;
        	max = Math.max(max, sum);
        }
        
        int cnt = 0; // 기간의 개수
        for(int i=0; i<N-X+1; i++) {
        	if(visitCnt[i] == max) {
        		cnt++;
        	}
        }
		
        System.out.println(max == 0 ? "SAD" : max + "\n" + cnt);
	}

}
