import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * <BJ_1522_문자열교환>
 * 브루트포스 + 슬라이딩윈도우 문제
 * 먼저 문자열의 처음과 끝이 이어져있고, 문자열이 모두 a인 부분 문자열을 만들어야 함에 주의한다.
 * 즉, a의 개수가 부분 문자열의 길이가 되고
 * 부분 문자열에서 b의 개수가 몇 개인지 확인하면 그게 a로 교환해야 하는 횟수이다.
 * 따라서 고정 크기(a의 개수 = 부분 문자열 크기)만큼 슬라이딩 윈도우를 진행하며
 * b의 개수를 카운트해 최소로 갱신해주면 최소 교환 횟수가 나온다.
 * 주의할 점은 전체 문자열의 길이를 벗어난 인덱스는 다시 앞에서부터 확인하도록 처리해줘야 한다. (문자열이 이어져 있으므로)
 * 
 * @author YooSejin
 *
 */

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		String s = br.readLine();
		
		// a의 개수 세기
		int aCnt = 0;
		for(int i=0; i<s.length(); i++) {
			if(s.charAt(i) == 'a') {
				aCnt++;
			}
		}
		
		int answer = Integer.MAX_VALUE;
		for(int i=0; i<s.length(); i++) {
			int bCnt = 0;
			for(int j=i; j<i+aCnt; j++) {
				int idx = j % s.length();
				if(s.charAt(idx) == 'b') {
					bCnt++;
				}
			}
			answer = Math.min(answer, bCnt);
		}
		
		System.out.println(answer);
	}

}
