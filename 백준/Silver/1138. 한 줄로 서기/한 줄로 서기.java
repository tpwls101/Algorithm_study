import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * <BJ_1138_한줄로서기>
 * 작은 번호부터 앞에 몇 명의 칸을 비워둬야 하는지 카운트하면서 자리를 찾는다.
 * 이 때 아직 정답 배열에 값이 들어가지 않아야(즉, 0이여야) 자기 자신보다 큰 수이므로 카운트하고
 * 자기 자신보다 큰 사람의 자리를 비워두고 내 차례가 온다면 자리에 들어간다.
 * 단, 자리가 비어있어야 들어갈 수 있으므로 0일 때에만 자리를 가지고, 0이 아니라면 다음 자리로 밀려난다.
 * 
 * 그렇게 어려워 보이진 않지만 정확히 로직을 짜야하는 문제였다.
 * 
 * 맨 앞에서부터 차례대로 카운트를 안하고, answer[num]이 0인지만 판단한 후 그 뒤에다가 값을 넣게 되면
 * 나보다 큰 사람의 수가 정확하지 않을 수 있음을 주의해야 한다. (예제4로 생각하면 쉬움 - 5번 사람이 4번 인덱스에 들어가게 됨(7번 사람 자리))
 * 
 * @author YooSejin
 *
 */

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
	        		if(answer[j] == 0) cnt++;
	        	}
	        }
			
	        for(int i=0; i<N; i++) {
	        	System.out.print(answer[i] + " ");
	        }
	}

}
