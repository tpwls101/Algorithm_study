import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

	public static void main(String[] args) throws IOException { // br.readLine() 은 반드시 IOException 던져주기
		String answer = "";
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in)); // 선언
		while (true) {
			String line = br.readLine(); // 입력
			if (line.equals("end"))
				break;
			answer += test(line) ? "valid\n" : "invalid\n";
		}

		System.out.println(answer);
	}

	static public boolean test(String line) {
		int xCnt = 0, oCnt = 0, xBingo = 0, oBingo = 0;

		for (int i = 0; i < line.length(); i++) {
			if (line.charAt(i) == 'X')
				xCnt++;
			else if (line.charAt(i) == 'O')
				oCnt++;
		}

		// X빙고도 안되고 O빙고도 안된 경우, 칸이 다 안 찬 경우(다 찼을 수도 있음) -> 잘못 끝난 경우!
		if ((xCnt - oCnt != 1) && (oCnt - xCnt != 0))
			return false;

		// 빙고가 있다면 카운트
		// 빙고 확인 (가로)
		for (int i = 0; i < 9; i += 3) {
			if (line.charAt(i) == '.')
				continue;
			if (line.charAt(i) == line.charAt(i + 1) && line.charAt(i + 1) == line.charAt(i + 2)) {
				if (line.charAt(i) == 'X')
					xBingo++;
				else
					oBingo++;
			}
		}

		// 빙고 확인 (세로)
		for (int i = 0; i < 3; i++) {
			if (line.charAt(i) == '.')
				continue;
			if (line.charAt(i) == line.charAt(i + 3) && line.charAt(i + 3) == line.charAt(i + 6)) {
				if (line.charAt(i) == 'X')
					xBingo++;
				else
					oBingo++;
			}
		}

		// 빙고 확인 (대각선)
		if (line.charAt(4) != '.') {
			if (line.charAt(4) == line.charAt(0) && line.charAt(4) == line.charAt(8)
					|| line.charAt(4) == line.charAt(2) && line.charAt(4) == line.charAt(6)) {
				if (line.charAt(4) == 'X')
					xBingo++;
				else
					oBingo++;
			}
		}

		if (xBingo > 0) { // X빙고가 존재하는 경우
			if (oBingo > 0)
				return false; // X빙고가 생기면 O빙고는 생길 수 없음
			if (xCnt - oCnt == 1)
				return true; // 번갈아 가면서 알맞게 끝난 경우
			else
				return false; // O빙고가 없어도 개수 차이가 맞지 않으면 잘못 끝난 경우
		}

		if (oBingo > 0) { // X빙고는 존재하지 않고 O빙고만 존재하는 경우
			if (oCnt - xCnt == 0)
				return true; // 번갈아 가면서 알맞게 끝난 경우
			else
				return false; // X빙고가 없어도 개수 차이가 맞지 않으면 잘못 끝난 경우
		}

		if (xCnt + oCnt == 9) { // X빙고도 없고 O빙고도 없는데 칸이 다 찬 경우
			// if(xCnt - oCnt == 1)
			return true;
		}

		return false;
	}

}
