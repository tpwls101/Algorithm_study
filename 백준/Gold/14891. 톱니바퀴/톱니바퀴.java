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
 * @author YooSejin
 *
 */

public class Main {
	
	static int[][] arr = new int[5][8]; // 톱니바퀴의 상태를 저장할 배열 (N극:0 / S극:1)
	static int[][] arr2 = new int[5][8]; // 회전시킨 후의 톱니바퀴 상태를 저장
	
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
			boolean dir = true; // 회전시킨 방향 (1:시계 방향(true) / -1:반시계 방향(false))
			if(Integer.parseInt(st.nextToken()) == -1) {
				dir = false;
			}
			
			// 해당 톱니 회전
			revolve(num, dir);
			
			// 오른쪽 방향의 톱니 확인
			boolean tempDir = dir;
			for(int a=num; a<4; a++) {
				if(arr[a][2] != arr[a+1][6]) { // 극이 다르면 반대 방향으로 회전
					tempDir = !tempDir;
					revolve(a+1, tempDir);
				} else { // 극이 같으면 회전하지 않고  그 다음 톱니바퀴도 회전하지 않음
					// 회전하지 않은 원래 톱니 상태를 arr2에 저장해야 함
					for(int j=a+1; j<=4; j++) {
						arr2[j] = Arrays.copyOf(arr[j], arr[j].length);
					}
					break;
				}
			}
			
			// 왼쪽 방향의 톱니 확인
			tempDir = dir;
			for(int b=num; b>1; b--) {
				if(arr[b][6] != arr[b-1][2]) { // 극이 다르면 반대 방향으로 회전
					tempDir = !tempDir;
					revolve(b-1, tempDir);
				} else { // 극이 같으면 회전하지 않고  그 다음 톱니바퀴도 회전하지 않음
					// 회전하지 않은 원래 톱니 상태를 arr2에 저장해야 함
					for(int j=b-1; j>0; j--) {
						arr2[j] = Arrays.copyOf(arr[j], arr[j].length);
					}
					break;
				}
			}
			
			// 회전시킨 결과를 다시 arr 배열에 copy -> 다음 회전을 위해
			for(int j=0; j<=4; j++) {
				arr[j] = Arrays.copyOf(arr2[j], arr2[j].length);
			}
			
			// 한번 회전한 후 확인
//			for(int x=1; x<=4; x++) {
//				for(int y=0; y<8; y++) {
//					System.out.print(arr[x][y]);
//				}
//				System.out.println();
//			}
		}
		
		// 점수 계산
		int score = 0;
		for(int i=1; i<=4; i++) {
			if(arr[i][0] == 0) { // N극이면 0점
				continue;
			} else { // S극이면 다른 점수 부여
				switch(i) {
				case 1:
					score += 1;
					break;
				case 2:
					score += 2;
					break;
				case 3:
					score += 4;
					break;
				case 4:
					score += 8;
					break;
				}
			}
		}
		
		System.out.println(score);
	}
	
	// 톱니바퀴를 회전시키는 메서드
	static void revolve(int num, boolean dir) {
		//System.out.println("회전시킬 톱니 번호 : " + num + ", 회전 방향 : " + dir);
		
		if(dir) { // 시계 방향
			int temp = arr[num][7];
			for(int i=7; i>0; i--) {
				arr2[num][i] = arr[num][i-1];
			}
			arr2[num][0] = temp;
		} else { // 반시계 방향
			int temp = arr[num][0];
			for(int i=0; i<7; i++) {
				arr2[num][i] = arr[num][i+1];
			}
			arr2[num][7] = temp;
		}
	}

}