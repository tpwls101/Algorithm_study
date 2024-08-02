import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * <BJ_1094_막대기>
 * 
 * <방법1>
 * 1. Xcm를 만드는데 필요한 길이보다 현재 막대의 길이가 더 길면 반은 잘라 버린다.
 * 2. 현재 막대의 길이가 Xcm 보다 작으면 막대를 사용하고(count++), 필요한 만큼(X-stick) 더 구한다.
 * 
 * <방법2>
 * 막대의 길이가 그리 길지 않으니 사용할 수 있는 막대의 길이를 배열로 미리 만들어 사용한다.
 * 
 * <방법3>
 * 비트마스킹
 * Integer.bitCount() 함수 사용 ㄷㄷㄷ 
 * -> 이진수에서 1의 개수를 세어주는 함수
 * ex. 23(10111) -> 4 출력
 * 
 * @author YooSejin
 *
 */

public class BJ_1094_막대기_방법3 {
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int X = Integer.parseInt(br.readLine()); // 만들고자 하는 막대기의 길이 (또는 필요한 막대의 길이)
		
		System.out.println(Integer.bitCount(X));
	}

}
