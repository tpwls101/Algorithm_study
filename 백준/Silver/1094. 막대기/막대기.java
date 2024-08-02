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
 * @author YooSejin
 *
 */

public class Main {
	
	// 막대의 길이가 그리 길지 않으니 사용할 수 있는 막대의 길이를 배열로 미리 만들어 사용한다.
	static int[] length = { 64, 32, 16, 8, 4, 2, 1 };
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int X = Integer.parseInt(br.readLine()); // 만들고자 하는 막대기의 길이 (또는 필요한 막대의 길이)
		int count = 0; // 사용된 막대의 개수
		
		while(X > 0) {
			for(int i=0; i<length.length; i++) {
				// 막대가 만돌고자 하는 X보다 길이가 짧으면 사용
				if(length[i] <= X) {
					X -= length[i];
					count++;
				}
			}
		}
		
		System.out.println(count);
	}

}