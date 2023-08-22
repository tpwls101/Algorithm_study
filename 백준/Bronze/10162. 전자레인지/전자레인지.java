import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int T = Integer.parseInt(br.readLine()); // 요리시간 (초단위)
		
		int A = 0;
		int B = 0;
		int C = 0;
		
		A += T/300;
		T %= 300;
		
		B += T/60;
		T %= 60;
		
		C += T/10;
		T %= 10;

		if(T != 0) {
			System.out.println(-1);
		} else {			
			System.out.printf("%d %d %d", A, B, C);
		}
		
	}

}