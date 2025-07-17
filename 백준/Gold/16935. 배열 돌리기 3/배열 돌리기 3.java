import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * <BJ_16935_배열돌리기3>
 * 단순 구현 문제. 다만 각 연산의 경우의 수대로 구현해야 해서 귀찮을 뿐.
 * 가장 주의할 점은 90도 회전할 때 N과 M의 크기가 다른 경우 배열의 모양이 바뀐다는 것이다.
 * 하지만 연산을 여러 번 수행하기 때문에 단순 arr[N][M]을 arr[M][N]으로 바꾸면 안되고
 * N과 M의 값을 바꿔주는 함수를 만들어 수행한다.
 * 
 * @author YooSejin
 *
 */

public class Main {
	
	static int N;
	static int M;
	static int R;
	static int[][] arr;
	
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        R = Integer.parseInt(st.nextToken());
		
        arr = new int[N][M];
        
        for(int i=0; i<N; i++) {
        	st = new StringTokenizer(br.readLine());
        	for(int j=0; j<M; j++) {
        		arr[i][j] = Integer.parseInt(st.nextToken());
        	}
        }
        
        // R번의 연산 수행
        st = new StringTokenizer(br.readLine());
        for(int i=0; i<R; i++) {
        	int num = Integer.parseInt(st.nextToken());
        	switch(num) {
        	case 1:
        		sign1();
        		break;
        	case 2:
        		sign2();
        		break;
        	case 3:
        		sign3();
        		break;
        	case 4:
        		sign4();
        		break;
        	case 5:
        		sign5();
        		break;
        	case 6:
        		sign6();
        		break;
        	default:
        		break;
        	}
        }
        
        StringBuilder sb = new StringBuilder();
        for(int i=0; i<N; i++) {
			for(int j=0; j<M; j++) {
				sb.append(arr[i][j] + " ");
			}
			sb.append("\n");
		}
		
		System.out.println(sb);
	}
	
	// 상하 반전
	static void sign1() {
		for(int i=0; i<N/2; i++) {
			int[] tmp = arr[i];
			arr[i] = arr[N-1-i];
			arr[N-1-i] = tmp;
		}
	}
	
	// 좌우 반전
	static void sign2() {
		for(int i=0; i<M/2; i++) {
			for(int j=0; j<N; j++) {
				int tmp = arr[j][i];
				arr[j][i] = arr[j][M-1-i];
				arr[j][M-1-i] = tmp;
			}
		}
	}
	
	// 오른쪽으로 90도 회전
	static void sign3() {
		// 90도 회전하면 N과 M의 크기가 다른 경우 배열 모양이 바뀜을 주의
		change(); // N과 M 바꿔주기
		int[][] result = new int[N][M];
		
		for(int i=0; i<M; i++) {
			for(int j=0; j<N; j++) {
				result[j][M-1-i] = arr[i][j];
			}
		}
		
		arr = result;
	}
	
	// 왼쪽으로 90도 회전
	static void sign4() {
		change(); // N과 M 바꿔주기
		int[][] result = new int[N][M];
		
		for(int i=0; i<M; i++) {
			for(int j=0; j<N; j++) {
				result[N-1-j][i] = arr[i][j];
			}
		}
		
		arr = result;
	}
	
	static void sign5() {
		int[][] result = new int[N][M];
		
		move(0, 0, 0, M/2, result); // 1 -> 2
		move(0, M/2, N/2, M/2, result); // 2 -> 3
		move(N/2, M/2, N/2, 0, result); // 3 -> 4
		move(N/2, 0, 0, 0, result); // 4 -> 1
		
		arr = result;
	}
	
	static void sign6() {
		int[][] result = new int[N][M];
		
		move(0, 0, N/2, 0, result); // 1 -> 4
		move(N/2, 0, N/2, M/2, result); // 4 -> 3
		move(N/2, M/2, 0, M/2, result); // 3 -> 2
		move(0, M/2, 0, 0, result); // 2 -> 1
		
		arr = result;
	}
	
	// 부분 배열 이동시키는 함수
	static void move(int x1, int y1, int x2, int y2, int[][] result) {
		for(int i=0; i<N/2; i++) {
			for(int j=0; j<M/2; j++) {
				result[x2 + i][y2 + j] = arr[x1 + i][y1 + j];
			}
		}
	}
	
	static void change() {
		int tmp = N;
		N = M;
		M = tmp;
	}

}
