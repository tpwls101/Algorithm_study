import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * <D2_14510_나무높이>
 * 1. 키가 가장 큰 나무의 키 구하기 (maxHeight)
 * 2. 나무의 높이와 maxHeight의 차 구하기
 * 3. 차에서 나누면 키 +2 가능한 날의 수를 구할 수 있고 나머지 연산을 쓰면 키 +1이 필요한 날의 수를 구할 수 있음
 * 4. 모든 나무의 키가 maxHeight랑 같아지도록 할 수 있는 최소 날짜 수 구하기
 * 		- cnt1과 cnt2가 같은 경우는 그냥 두 수를 더해주면 됨
 * 		- cnt1이 더 크면 1을 2로 바꿔줄 수 없으므로 식 계산
 * 		- cnt2가 더 크면 2를 1로 바꿔줄 수 있으니 균형을 맞춰서 경우의 수에 따라 계산
 *
 * @author 유세진
 *
 */

public class Solution {
	
	static int N; // 나무의 개수
	static int[] tree; // 나무 높이를 저장할 배열
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int T = Integer.parseInt(br.readLine()); // 테스트케이스 수
		
		for(int tc=1; tc<=T; tc++) {
			N = Integer.parseInt(br.readLine());
			
			tree = new int[N];
			
			int maxHeight = 0; // 키가 가장 큰 나무의 키
			st = new StringTokenizer(br.readLine());
			for(int i=0; i<N; i++) {
				tree[i] = Integer.parseInt(st.nextToken());
				maxHeight = Math.max(maxHeight, tree[i]);
			}
			
			int cnt1 = 0; // 키 +1이 필요한 날의 수
			int cnt2 = 0; // 키 +2 가능한 날의 수
			for(int i=0; i<N; i++) {
				cnt2 += (maxHeight - tree[i]) / 2;
				cnt1 += (maxHeight - tree[i]) % 2;
			}
			//System.out.println(cnt1 + " " + cnt2);
			
			int answer = 0; // 모든 나무의 키가 maxHeight랑 같아지도록 할 수 있는 최소 날짜 수
			if(cnt1 > cnt2) {
				answer = cnt2*2 + (cnt1-cnt2)*2 - 1; // -1 : 마지막에 +1 하면 +2는 필요없음
			} else if(cnt1 < cnt2) {
				while(cnt2 > cnt1+1) {
					cnt2--;
					cnt1 += 2;
				}
				//System.out.println(cnt1 + " " + cnt2);
				
				// 3가지 경우의 수 생김
				if(cnt1 >= cnt2) {
					answer = cnt1 + cnt2;
				} else {
					answer = cnt1 + cnt2 + 1;
				}
				
			} else { // cnt1 == cnt2
				answer = cnt1 + cnt2;
			}
			
			System.out.printf("#%d %d\n", tc, answer);
		}
		
	}

}
