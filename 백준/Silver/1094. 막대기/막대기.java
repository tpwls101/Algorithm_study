import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * <BJ_1094_막대기>
 * 1. Xcm를 만드는데 필요한 길이보다 현재 막대의 길이가 더 길면 반은 잘라 버린다.
 * 2. 현재 막대의 길이가 Xcm 보다 작으면 막대를 사용하고(count++), 필요한 만큼(X-stick) 더 구한다.
 * 
 * @author YooSejin
 *
 */

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int X = Integer.parseInt(br.readLine()); // 만들고자 하는 막대기의 길이 (또는 필요한 막대의 길이)
		int stick = 64; // 현재 막대의 길이
		int count = 0; // 사용된 막대의 개수
		
		while(X > 0) {
			// 현재 막대가 필요한 길이보다 길면 -> 계속 반 나눠준다.
			if(stick > X) {
				stick /= 2;
			} 
			// 현재 막대의 길이가 만들고자 하는 막대의 길이보다 작거나 같으면
			// -> Xcm를 만드는데 사용할 수 있으므로 막대의 길이를 빼 필요한 막대의 길이를 계속 줄여준다.
			// 현재 막대의 길이를 빼는 것은 사용한만큼 빼는 것이므로 한 개의 막대를 사용한 것! -> count 1 증가
			else {
				X -= stick;
				count++;
			}
		}
		
		System.out.println(count);
	}

}