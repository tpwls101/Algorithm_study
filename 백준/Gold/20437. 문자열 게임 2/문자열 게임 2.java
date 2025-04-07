import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * <BJ_20437_문자열게임2>
 * 약간 투 포인터 문제라는 느낌은 왔지만 인덱스 처리를 어떻게 해야할지 고민했음
 * 굳이 start와 end로 접근하지 않아도 상관없음
 * 그리고 10,000 x 10,000이라 괜찮을 거 같지만 알파벳의 숫자를 카운트하지 않으면 시간초과가 남
 * 알파벳의 수가 K보다 작으면 continue 해주자!!
 * 
 * @author YooSejin
 *
 */

public class Main {
	
	static int T; // 문자열 게임의 수
	static String W; // 문자열
	static int K; // 어떤 문자를 정확히 K개 포함해야 함
	static int[] alphabet; // 알파벳의 개수 저장
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		T = Integer.parseInt(br.readLine());
		
		StringBuilder sb = new StringBuilder();
		
		for(int t=0; t<T; t++) {
			W = br.readLine();
			K = Integer.parseInt(br.readLine());
			
			// 예외 처리 : 포함해야 할 문자의 수가 1개이면 최단 길이나 최장 길이는 무조건 1이다.
			// 이를 예외처리 안해주면 밑의 코드에서 j=i+1부터 시작하므로 처리가 안된다.
			if(K == 1) {
				sb.append(1 + " " + 1 + "\n");
				continue;
			}
			
			alphabet = new int[26];
			
			// 알파벳의 개수 저장
			for(int i=0; i<W.length(); i++) {
				alphabet[W.charAt(i) - 'a']++;
			}
			
			int min = Integer.MAX_VALUE;
			int max = -1;
			
			// 투 포인터
			for(int i=0; i<W.length(); i++) {
				// 문자열을 차례대로 돌면서 해당 문자의 개수가 K개보다 적으면 패스
				if(alphabet[W.charAt(i) - 'a'] < K) continue;
				
				int cnt = 1;
				for(int j=i+1; j<W.length(); j++) {
					if(W.charAt(j) == W.charAt(i)) {
						cnt++;
					}
					
					if(cnt == K) {
						min = Math.min(min, j-i+1);
						max = Math.max(max, j-i+1);
						break;
					}
				}
			}
			
			if(min == Integer.MAX_VALUE && max == -1) {
				sb.append(-1 + "\n");
				continue;
			}
			
			sb.append(min + " " + max + "\n");
		}
		
		System.out.println(sb);
	}

}
