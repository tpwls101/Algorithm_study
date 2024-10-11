import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		while(true) {
			String s = br.readLine();
			if(s.equals("end")) break;
			
			char[][] arr = new char[3][3];
			
			for(int i=0; i<3; i++) {
				for(int j=0; j<3; j++) {
					arr[i][j] = s.charAt(3*i + j);
				}
			}
			
			// X와 O의 개수 체크
			int XCnt = 0;
			int OCnt = 0;
			
			for(int i=0; i<s.length(); i++) {
				if(s.charAt(i) == 'X') {
					XCnt++;
				} else if(s.charAt(i) == 'O') {
					OCnt++;
				}
			}
			
			// X가 O보다 하나 더 많거나 X랑 O의 개수가 같을 때만 가능하다
			// 일단 개수가 안맞으면 무조건 invalid
			if( !(XCnt == OCnt + 1 || XCnt == OCnt) ) {
				System.out.println("invalid");
				continue;
			}
			
			// X랑 O를 번갈아 두어 빙고없이 게임판이 가득 차서 끝난 경우
			// 예외 존재 : XCnt=OCnt+1이고, 게임판이 가득 찼지만, O빙고가 존재하는 경우
//			if(XCnt == OCnt + 1 && dotCnt == 0) {
//				System.out.println("valid");
//				continue;
//			}
			
			// 빙고가 있는지 확인
			int XBingo = 0;
			int OBingo = 0;
			
			// 가로
			for(int i=0; i<3; i++) {
				if(arr[i][0] == 'X' && arr[i][1] == 'X' && arr[i][2] == 'X') {
					XBingo++;
				}
				if(arr[i][0] == 'O' && arr[i][1] == 'O' && arr[i][2] == 'O') {
					OBingo++;
				}
			}
			
			// 세로
			for(int i=0; i<3; i++) {
				if(arr[0][i] == 'X' && arr[1][i] == 'X' && arr[2][i] == 'X') {
					XBingo++;
				}
				if(arr[0][i] == 'O' && arr[1][i] == 'O' && arr[2][i] == 'O') {
					OBingo++;
				}
			}
			
			// 대각선
			if(arr[0][0] == 'X' && arr[1][1] == 'X' && arr[2][2] == 'X') {
				XBingo++;
			}
			if(arr[0][2] == 'X' && arr[1][1] == 'X' && arr[2][0] == 'X') {
				XBingo++;
			}
			if(arr[0][0] == 'O' && arr[1][1] == 'O' && arr[2][2] == 'O') {
				OBingo++;
			}
			if(arr[0][2] == 'O' && arr[1][1] == 'O' && arr[2][0] == 'O') {
				OBingo++;
			}
			
			// X빙고가 존재하는 경우
			if(XBingo > 0) {
				// X빙고가 만들어지면 종료되어 O빙고가 존재할 수 없다
				if(OBingo > 0) {
					System.out.println("invalid");
					continue;
				}
				
				// X의 개수가 O의 개수보다 하나 많을 때 X빙고가 존재할 수 있다
				if(XCnt == OCnt + 1) {
					System.out.println("valid");
					continue;
				}
				// O빙고가 없어도 개수 차이가 맞지 않으면 잘못 끝난 경우다
				else {
					System.out.println("invalid");
					continue;
				}
			}
			
			// X빙고는 존재하지 않고 O빙고만 존재하는 경우
			if(OBingo > 0) {
				// X->O 순서로 잘 맞게 끝난 경우에만 가능
				if(XCnt == OCnt) {
					System.out.println("valid");
					continue;
				} else {
					System.out.println("invalid");
					continue;
				}
			}
			
			// X빙고도 없고 O빙고도 없는데 칸이 다 찬 경우
			if(XCnt + OCnt == 9) {
				if(XCnt == OCnt + 1) {
					System.out.println("valid");
					continue;
				}
			}
			
			System.out.println("invalid");
		}
		
	}

}