import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * <BJ_17615_볼모으기>
 * 투 포인터거나 저번에 푼 BJ_1522_문자열교환(슬라이딩윈도우)와 비슷한 문제인가 했는데
 * 그리디 문제였다.
 * 그리고 레드와 블루 중 어느 것을 움직일 것인가 -> 이는 앞의 몇개만 가지고는 확인할 수 없다.
 * 처음에는 R보다 B가 많아도 이후에는 R이 훨씬 많아 R을 움직이는게 최소가 아닐 수 있다.
 * 따라서 R을 왼쪽으로 혹은 오른쪽으로, B를 왼쪽으로 혹은 오른쪽으로 움직이는 모든 경우의 수 (4가지)를 만들어 비교해야 한다.
 * 
 * @author YooSejin
 *
 */

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int N = Integer.parseInt(br.readLine());
		String s = br.readLine();
		
		int firstBallCnt = 0;
		int redCnt = 0;
		int blueCnt = 0;
		int answer = Integer.MAX_VALUE;
		
		// 빨간공과 파란공의 개수 세기
		for(int i=0; i<N; i++) {
			if(s.charAt(i) == 'R') redCnt++;
			if(s.charAt(i) == 'B') blueCnt++;
		}
		
		// 빨간공을 왼쪽으로
		for(int i=0; i<N; i++) {
			if(s.charAt(i) == 'R') {
				firstBallCnt++;
			} else {
				break;
			}
		}
		answer = Math.min(answer, redCnt - firstBallCnt);
		
		// 빨간공을 오른쪽으로
		firstBallCnt = 0;
		for(int i=N-1; i>=0; i--) {
			if(s.charAt(i) == 'R') {
				firstBallCnt++;
			} else {
				break;
			}
		}
		answer = Math.min(answer, redCnt - firstBallCnt);
		
		// 파란공을 왼쪽으로
		firstBallCnt = 0;
		for(int i=0; i<N; i++) {
			if(s.charAt(i) == 'B') {
				firstBallCnt++;
			} else {
				break;
			}
		}
		answer = Math.min(answer, blueCnt - firstBallCnt);
		
		// 파란공을 오른쪽으로
		firstBallCnt = 0;
		for(int i=N-1; i>=0; i--) {
			if(s.charAt(i) == 'B') {
				firstBallCnt++;
			} else {
				break;
			}
		}
		answer = Math.min(answer, blueCnt - firstBallCnt);
		
		System.out.println(answer);
	}

}
