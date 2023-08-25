import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * <BJ_17143_낚시왕>
 * 칸에 상어는 최대 한마리, 두마리 이상 있으면 크기가 가장 큰 상어만 남음
 * 낚시왕이 잡은 상어 크기의 합 구하기!
 * 
 * !!! 놓친 부분 !!!
 * 1. 입력받은 arr 배열에서 상어를 이동시키고 저장하면 for문을 돌면서 이동된 상어까지 다시 이동시키게 된다.
 * 		이를 해결하기 위해 temp 배열을 만들어 이동시킨 상어의 위치를 저장하고 arr에 다시 저장해주었다.
 * 2. 1초 동안 낚시왕과 상어 모두 이동을 하게 된다. 낚시왕이 해당 열에서 잡을 상어가 없더라도 상어는 이동을 시켜주어야 한다.
 * 		즉, 상어를 잡고 이동을 시키는게 아니라 낚시왕이 한 칸씩 움직일 때마다 상어도 이동!
 * 
 * 메모리 : 
 * 
 * @author 유세진
 *
 */

public class Main {
	
	static class Shark {
		int r; // 상어의 위치(행)
		int c; // 상어의 위치(열)
		int s; // 속력
		int d; // 이동 방향 (1:위, 2:아래, 3:오른쪽, 4:왼쪽)
		int z; // 크기
		
		public Shark(int r, int c, int s, int d, int z) {
			super();
			this.r = r;
			this.c = c;
			this.s = s;
			this.d = d;
			this.z = z;
		}
	}
	
	static int R;
	static int C;
	static Shark[][] arr; // 입력값을 저장한 배열
	static Shark[][] temp; // 상어가 이동한 후의 위치를 저장할 배열
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		R = Integer.parseInt(st.nextToken()); // RxC 격자판 (행)
		C = Integer.parseInt(st.nextToken()); // RxC 격자판 (열)
		int M = Integer.parseInt(st.nextToken()); // 상어의 수
		
		arr = new Shark[R+1][C+1]; // RxC 격자판 (낚시왕의 위치는 (0,0))
		
		for(int i=0; i<M; i++) {
			st = new StringTokenizer(br.readLine());
			int r = Integer.parseInt(st.nextToken()); // 상어의 위치(행)
			int c = Integer.parseInt(st.nextToken()); // 상어의 위치(열)
			int s = Integer.parseInt(st.nextToken()); // 속력
			int d = Integer.parseInt(st.nextToken()); // 이동 방향 (1:위, 2:아래, 3:오른쪽, 4:왼쪽)
			int z = Integer.parseInt(st.nextToken()); // 크기
			
			arr[r][c] = new Shark(r, c, s, d, z);
		}
		
// 		// 입력 확인
//		for(int i=0; i<=R; i++) {
//			for(int j=0; j<=C; j++) {
//				if(arr[i][j] != null) {
//					System.out.print(1 + " ");
//				} else {
//					System.out.print(0 + " ");
//				}
//			}
//			System.out.println();
//		}
//		System.out.println();
		
		
		// 1. 낚시왕이 오른쪽으로 한 칸 이동
		// 2. 낚시왕이 있는 열에 있는 상어 중에서 땅과 제일 가까운 상어 잡기 (상어를 잡으면 잡은 상어는 격자판에서 사라짐)
		// 3. 상어 이동
		
		int answer = 0; // 낚시왕이 잡은 상어 크기의 합
		
		for(int col =1; col<=C; col++) { // 1. 낚시왕이 오른쪽으로 한 칸 이동
			for(int row=1; row<=R; row++) {
				if(arr[row][col] != null) { // 2. col번째 열에서 가장 가까운 상어를 만나면
					answer += arr[row][col].z; // 잡아서 크기를 더해주기
					arr[row][col] = null; // 잡으면 격자판에서 사라지니까 null 처리
					
					// 상어 확인
					//System.out.println("오리지널");
//					for(int i=1; i<=R; i++) {
//						for(int j=1; j<=C; j++) {
//							if(arr[i][j] != null) {
//								System.out.print(1 + " ");
//							} else {
//								System.out.print(0 + " ");
//							}
//						}
//						System.out.println();
//					}
//					System.out.println();
					
					break;
				}
			}
			
			temp = new Shark[R+1][C+1]; // 이동한 상어의 위치를 저장할 배열
			
			// 3. 상어 이동 (낚시왕이 해당 열에서 잡을 상어가 없더라도 상어는 이동을 시켜주어야 한다!)
			moveShark();
			arr = temp;
			
//			// 상어 확인
//			System.out.println("이동 후");
//			for(int i=1; i<=R; i++) {
//				for(int j=1; j<=C; j++) {
//					if(arr[i][j] != null) {
//						System.out.print(1 + " ");
//					} else {
//						System.out.print(0 + " ");
//					}
//				}
//				System.out.println();
//			}
//			System.out.println();
		}
		
		System.out.println(answer);
	}
	
	// 상어 이동 메서드
	private static void moveShark() {
		
		for(int i=1; i<=R; i++) {
			for(int j=1; j<=C; j++) {
				if(arr[i][j] != null) { // 격자판에 상어가 있으면 이동
					Shark shark = arr[i][j];
					arr[i][j] = null; // 이동하므로 상어가 원래 있던 자리는 null
					
					if(shark.d == 1) { // 상어가 위로 이동
						int flag = -1; // 반대 방향으로 이동하기 위해 만든 플래그
						for(int move=1; move<=shark.s; move++) {
							if(shark.r == 1) {
								flag = 1;
								shark.d = 2;
							}
							if(shark.r == R) {
								flag = -1;
								shark.d = 1;
							}
							shark.r += flag;
						}
					}
					
					else if(shark.d == 2) { // 상어가 아래로 이동
						int flag = 1; // 반대 방향으로 이동하기 위해 만든 플래그
						for(int move=1; move<=shark.s; move++) {
							if(shark.r == 1) {
								flag = 1;
								shark.d = 2;
							}
							if(shark.r == R) {
								flag = -1;
								shark.d = 1;
							}
							shark.r += flag;
						}
					}
					
					else if(shark.d == 3) { // 상어가 오른쪽으로 이동
						int flag = 1; // 반대 방향으로 이동하기 위해 만든 플래그
						for(int move=1; move<=shark.s; move++) {
							if(shark.c == 1) {
								flag = 1;
								shark.d = 3;
							}
							if(shark.c == C) {
								flag = -1;
								shark.d = 4;
							}
							shark.c += flag;
						}
					}
					
					else { // 상어가 왼쪽으로 이동
						int flag = -1; // 반대 방향으로 이동하기 위해 만든 플래그
						for(int move=1; move<=shark.s; move++) {
							if(shark.c == 1) {
								flag = 1;
								shark.d = 3;
							}
							if(shark.c == C) {
								flag = -1;
								shark.d = 4;
							}
							shark.c += flag;
						}
					}
					
					// 이동한 좌표가 비어있으면 그냥 상어 저장
					if(temp[shark.r][shark.c] == null) {
						temp[shark.r][shark.c] = new Shark(shark.r, shark.c, shark.s, shark.d, shark.z);
						//System.out.println(shark.r + " " + shark.c);
						//System.out.println("상어 이동 성공");
					} else { // 이동한 좌표에 이미 상어가 있다면 한 칸에 두마리 이상 있을 수 없으니 크기가 큰 상어만 남긴다
						if(shark.z > temp[shark.r][shark.c].z) { // 원래 있던 상어보다 이동시킨 상어가 크기가 더 크면
							temp[shark.r][shark.c] = new Shark(shark.r, shark.c, shark.s, shark.d, shark.z); // 크기가 더 큰 새로운 상어 저장
						}
						// 원래 있던 상어가 더 크다면 그냥 그대로 둠
					}
					
//					// temp의 상어 확인
//					//System.out.println("temp 확인");
//					for(int a=1; a<=R; a++) {
//						for(int b=1; b<=C; b++) {
//							if(temp[a][b] != null) {
//								System.out.print(1 + " ");
//							} else {
//								System.out.print(0 + " ");
//							}
//						}
//						System.out.println();
//					}
//					System.out.println();
					
				}
			}
		}
		
	}

}