import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * <BJ_17822_원판돌리기>
 * Tip) 원판의 모양대로 배열을 생성하지 않아도 톱니바퀴 문제처럼 그냥 배열에 저장하면 된다.
 * 
 * @author YooSejin
 *
 */

public class Main {
	
	static int N; // 원판의 개수
	static int M; // 원판에 적힌 정수의 개수
	static int T; // 원판 회전 횟수
	static int[][] arr;
	static int remain; // 원판에 남아있는 수의 개수
	static int answer = 0; // 원판에 적힌 수의 합
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        T = Integer.parseInt(st.nextToken());
        
        arr = new int[N+1][M]; // x번 원판 번호의 배수인 원판을 회전시켜야 하므로 계산을 위해 N+1 사용
        remain = N*M;
        
        for(int i=1; i<=N; i++) {
        	st = new StringTokenizer(br.readLine());
        	for(int j=0; j<M; j++) {
        		arr[i][j] = Integer.parseInt(st.nextToken());
        	}
        }
        
        // T번 회전
        for(int t=0; t<T; t++) {
        	st = new StringTokenizer(br.readLine());
        	int x = Integer.parseInt(st.nextToken()); // 번호가 x의 배수인 원판을 회전시킴
        	int d = Integer.parseInt(st.nextToken()); // 방향(0:시계방향, 1:반시계방향)
        	int k = Integer.parseInt(st.nextToken()); // k칸 회전
        	
        	// 번호가 x의 배수인 원판 회전시키기
        	for(int i=x; i<=N; i+=x) {
        		rotate(i, d, k);
        	}
        	
        	// 확인
//        	for(int i=1; i<=N; i++) {
//            	for(int j=0; j<M; j++) {
//            		System.out.print(arr[i][j] + " ");
//            	}
//            	System.out.println();
//            }
        	
        	// 원판에 수가 남아있으면 인접하면서 수가 같은 것 찾아 지우기
//        	if(remain > 0) {
            	erase();
//        	}
        }
        
        // 원판에 적힌 수의 합 구하기
        for(int i=1; i<=N; i++) {
        	for(int j=0; j<M; j++) {
        		answer += arr[i][j];
        	}
        }
        System.out.println(answer);
	}
	
	// 원판 번호, 방향, 회전 칸 수
	static void rotate(int num, int d, int k) {
		int[] temp = new int[M];
		
		if(d == 0) { // 시계 방향으로 회전
			for(int i=0; i<M; i++) {
				temp[(i+k) % M] = arr[num][i];
			}
			
			for(int i=0; i<M; i++) {
				arr[num][i] = temp[i];
			}
		} else if(d == 1) { // 반시계 방향으로 회전
			for(int i=0; i<M; i++) {
				temp[(M-k+i) % M] = arr[num][i];
			}
			
			for(int i=0; i<M; i++) {
				arr[num][i] = temp[i];
			}
		}
	}
	
	static void erase() {
		int[][] original = new int[N+1][M];
		for(int i=0; i<=N; i++) {
			original[i] = arr[i].clone();
		}
		
		int count = 0; // 인접하면서 같은 수가 있는 경우
		
		// 양 옆 확인
		for(int i=1; i<=N; i++) {
			for(int j=0; j<M-1; j++) {
				if(original[i][j] == original[i][j+1] && original[i][j] != 0) {
					arr[i][j] = 0;
					arr[i][j+1] = 0;
					count++;
				}
			}
			
			if(original[i][M-1] == original[i][0] && original[i][M-1] != 0) {
				arr[i][M-1] = 0;
				arr[i][0] = 0;
				count++;
			}
		}
		
		// 위아래 아래
		for(int i=0; i<M; i++) {
			for(int j=1; j<N; j++) {
				if(original[j][i] == original[j+1][i] && original[j][i] != 0) {
					arr[j][i] = 0;
					arr[j+1][i] = 0;
					count++;
				}
			}
		}
		
		// 인접하면서 같은 수가 없으면 원판에 적힌 수의 평균을 구해 처리
		if(count == 0) {
			int sum = 0;
			int cnt = 0;
			
			for(int i=1; i<=N; i++) {
				for(int j=0; j<M; j++) {
					if(original[i][j] != 0) {
						sum += original[i][j];
						cnt++;
					}
				}
			}
			
			double avg = (double)sum / cnt;
			//System.out.println("평균 : " + avg);
			
			for(int i=1; i<=N; i++) {
				for(int j=0; j<M; j++) {
					if(original[i][j] != 0) {
						if(original[i][j] > avg) {
							arr[i][j] -= 1;
						} else if(original[i][j] < avg) {
							arr[i][j] += 1;
						}
					}
				}
			}
		}
	}

}
