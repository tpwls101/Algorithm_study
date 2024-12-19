import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * <BJ_17779_게리맨더링2>
 * 문제가 복잡해보이지만 사실 문제에서 주어진 조건대로 그대로 풀면 되는 문제!
 * 4중 for문으로 기준점 (x,y)와  경계의 길이 d1, d2를 정하고
 * 경계선을 구분해 각 선거구의 인구 합을 구해주면 된다.
 * 
 * 주의사항 : 각 선거구의 인구 합을 구할 때, 2번 선거구와 같은 경우에는 -> 방향으로 합을 구하는게 아니라 <- 방향으로 구해야 한다.
 * 안그러면 2번 선거구가 아닌 5번 선거구의 인구를 더하게 되므로 주의!
 * 
 * @author YooSejin
 *
 */

public class Main {
	
	static int N; // 재현시의 크기
	static int[][] arr; // 인구수 저장
	static int totalSum = 0; // 전체 인구수
	static int answer = Integer.MAX_VALUE;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;
        
        N = Integer.parseInt(br.readLine());
		
        arr = new int[N][N];
        
        for(int i=0; i<N; i++) {
        	st = new StringTokenizer(br.readLine());
        	for(int j=0; j<N; j++) {
        		arr[i][j] = Integer.parseInt(st.nextToken());
        		totalSum += arr[i][j];
        	}
        }
		
        // 기준점과 경계의 길이 정하기
        for(int x=0; x<N; x++) {
        	for(int y=0; y<N; y++) {
        		for(int d1=1; d1<N; d1++) {
        			for(int d2=1; d2<N; d2++) {
        				if(x + d1 + d2 >= N) continue;
        				if(y - d1 < 0 || y + d2 >= N) continue;
        				
        				divide(x, y, d1, d2); // 5개의 선거구 나누기
        			}
        		}
        	}
        }
        
        System.out.println(answer);
	}
	
	static void divide(int x, int y, int d1, int d2) {
		boolean[][] border = new boolean[N][N]; // 경계선을 나타내는 배열
		
		for(int i=0; i<=d1; i++) {
			border[x+i][y-i] = true; // 1번 경계선
			border[x+d2+i][y+d2-i] = true; // 4번 경계선
		}
		
		for(int i=0; i<=d2; i++) {
			border[x+i][y+i] = true; // 2번 경계선
			border[x+d1+i][y-d1+i] = true; // 3번 경계선
		}
		
		// 각 선거구의 인구 구하기
		int[] people = new int[5];
		
		// 1번 선거구
		for(int i=0; i<x+d1; i++) {
			for(int j=0; j<=y; j++) {
				if(border[i][j]) break;
				people[0] += arr[i][j];
			}
		}
		
		// 2번 선거구
		for(int i=0; i<=x+d2; i++) {
			for(int j=N-1; j>y; j--) {
				if(border[i][j]) break;
				people[1] += arr[i][j];
			}
		}
		
		// 3번 선거구
		for(int i=x+d1; i<N; i++) {
			for(int j=0; j<y-d1+d2; j++) {
				if(border[i][j]) break;
				people[2] += arr[i][j];
			}
		}
		
		// 4번 선거구
		for(int i=x+d2+1; i<N; i++) {
			for(int j=N-1; j>=y-d1+d2; j--) {
				if(border[i][j]) break;
				people[3] += arr[i][j];
			}
		}
		
		// 5번 선거구
		people[4] = totalSum;
		for(int i=0; i<4; i++) {
			people[4] -= people[i]; // 전체 인구수에서 각 선거구의 인구수를 빼면 5번 선거구 인구수
		}
		
		Arrays.sort(people);
		answer = Math.min(answer, people[4] - people[0]);
	}

}