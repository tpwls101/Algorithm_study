import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * <BJ_14891_톱니바퀴>
 * 
 * 풀이 팁?
 * 회전 방향을 바꿀 때 if문으로 확인한 후 바꾸기 싫어서 boolean 타입 사용했음
 * 
 * 오른쪽, 왼쪽 방향마다 주어진 톱니 회전 방향으로 초기화해야 한다.
 * 극이 같아 회전하지 않아도 원래 톱니 상태를 arr2 배열에 저장해야 하고,
 * 그 다음 톱니도 회전하지 않으므로 여기까지 arr2 배열에 모두 저장해야 한다.
 * 
 * <풀이2> - 더 간단한 풀이!
 * 톱니바퀴는 4개로 고정되어 있으니까 각 톱니바퀴의 회전 정보를 먼저 저장하고 (1:시계방향/-1:반시계방향/0:회전x)
 * 회전 방향에 따라 회전시켜주면
 * 굳이 배열을 두 개 만들 필요없고 더 간단하다!
 * 
 * @author YooSejin
 *
 */

public class Main {
	
	static int[][] arr = new int[5][8]; // 톱니바퀴의 상태를 저장할 배열 (N극:0 / S극:1)
	static int[] direction; // 각 톱니바퀴의 회전 정보를 저장할 배열
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		
		for(int i=1; i<=4; i++) {
			String s = br.readLine();
			for(int j=0; j<8; j++) {
				arr[i][j] = Integer.parseInt(s.substring(j, j+1));
			}
		}
		
		int K = Integer.parseInt(br.readLine()); // 회전 횟수
		
		for(int i=0; i<K; i++) {
			st = new StringTokenizer(br.readLine());
			int num = Integer.parseInt(st.nextToken()); // 회전시킨 톱니바퀴의 번호
			int dir = Integer.parseInt(st.nextToken()); // 회전시킨 방향 (1:시계 방향 / -1:반시계 방향)
			
			// 각 톱니바퀴의 회전 정보를 저장
			direction = new int[5]; // 초기화 필수!!
			direction[num] = dir;
			checkDirection(num);
			
			// 톱니 회전
			for(int j=1; j<=4; j++) {
				revolve(j, direction[j]);
			}
		}
		
		// 점수 계산
		int score = 0;
		if(arr[1][0] == 1) score += 1;
		if(arr[2][0] == 1) score += 2;
		if(arr[3][0] == 1) score += 4;
		if(arr[4][0] == 1) score += 8;
		
		System.out.println(score);
	}
	
	// 각 톱니바퀴의 회전 정보를 저장하는 메서드
	static void checkDirection(int num) {
		// 오른쪽에 위치한 톱니바퀴
		for(int i=num+1; i<=4; i++) {
			// 극이 다르면 이전 톱니와 반대 방향으로 회전
			if(arr[i][6] != arr[i-1][2]) {
				direction[i] = -direction[i-1];
			}
			// 극이 같으면 회전하지 않음 + 그 뒤에 톱니바퀴도 회전하지 않음
			else {
				break;
			}
		}
		
		// 왼쪽에 위치한 톱니바퀴
		for(int i=num-1; i>0; i--) {
			// 극이 다르면 이전 톱니와 반대 방향으로 회전
			if(arr[i][2] != arr[i+1][6]) {
				direction[i] = -direction[i+1];
			}
			// 극이 같으면 회전하지 않음 + 그 뒤에 톱니바퀴도 회전하지 않음
			else {
				break;
			}
		}
	}
	
	// 톱니바퀴를 회전시키는 메서드
	static void revolve(int num, int dir) {
		if(dir == 1) { // 시계 방향
			int temp = arr[num][7];
			for(int i=7; i>0; i--) {
				arr[num][i] = arr[num][i-1];
			}
			arr[num][0] = temp;
		} else if(dir == -1) { // 반시계 방향
			int temp = arr[num][0];
			for(int i=0; i<7; i++) {
				arr[num][i] = arr[num][i+1];
			}
			arr[num][7] = temp;
		}
	}

}
