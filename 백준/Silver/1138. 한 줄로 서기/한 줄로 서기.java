import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	
	static int N; // 사람의 수
	static int[] answer;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;
        
        N = Integer.parseInt(br.readLine());
		
        answer = new int[N];
        
        st = new StringTokenizer(br.readLine());
        for(int i=0; i<N; i++) {
        	int num = Integer.parseInt(st.nextToken()); // 자기보다 키가 큰 사람의 수
        	
        	int cnt = 0;
        	for(int j=0; j<N; j++) {
        		if(cnt == num) {
        			if(answer[j] == 0) {
        				answer[j] = i+1;
        				break;
        			}
        		}
        		if(answer[j] == 0 || answer[j] > i+1) cnt++;
        	}
        }
		
        for(int i=0; i<N; i++) {
        	System.out.print(answer[i] + " ");
        }
	}

}