import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * <BJ_2138_전구와스위치> - 중복되는 코드를 함수화시킨 버전
 * 앞에서부터 만들고자 하는 전구의 상태를 만들면 그게 최선의 선택이 되는 그리디 문제!!
 * 
 * <힌트>
 * 1. 첫 번째 전구의 스위치를 눌렀을 때와 누르지 않았을 때로 구분해야 함
 * 2. i번째를 통해 i-1번째를 최종 결정 할 수 있음
 * 
 * @author YooSejin
 *
 */

public class Main {
	
	static int N; // 전구와 스위치의 개수
	static String[] current;
	static String[] want;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		N = Integer.parseInt(br.readLine());
		current = br.readLine().split("");
		want = br.readLine().split("");
		
		String[] A = current.clone(); // 첫 번째 스위치를 눌렀을 때
		String[] B = current.clone(); // 첫 번째 스위치를 안눌렀을 때

		int Acnt = 1; // 스위치 누른 횟수
		int Bcnt = 0; // 스위치 누른 횟수
		
		// 첫 번째 스위치 누른 후의 결과로 변경
		for(int i=0; i<2; i++) {
			if(A[i].equals("0")) A[i] = "1";
			else if(A[i].equals("1")) A[i] = "0";
		}
		
		Acnt = check(A, Acnt);
		Bcnt = check(B, Bcnt);
		
		int answer = Math.min(Acnt, Bcnt);
		if(answer == Integer.MAX_VALUE) System.out.println(-1);
		else System.out.println(answer);
	}
	
	static int check(String[] arr, int cnt) {
		// i번째를 통해 i-1번째를 최종 결정 할 수 있음
		for(int i=1; i<N; i++) {
			if(!arr[i-1].equals(want[i-1])) {
				cnt++;
				for(int j=-1; j<=1; j++) {
					if(i+j >= N) break;
					if(arr[i+j].equals("0")) arr[i+j] = "1";
					else if(arr[i+j].equals("1")) arr[i+j] = "0";
				}
			} else {
				continue;
			}
		}
		
		// 단 마지막 전구는 원하는 상태와 같은지 확인해줘야 함
		if(!arr[N-1].equals(want[N-1])) { // 원하는대로 만들지 못하면 -1 출력
			cnt = Integer.MAX_VALUE;
		}
		
		return cnt;
	}

}