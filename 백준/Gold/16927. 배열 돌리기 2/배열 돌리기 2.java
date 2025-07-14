import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * <BJ_16927_배열돌리기2>
 * 구현 문제
 * 
 * 1. 2차원 배열 만들기
 * 2. 배열의 각 테두리 R회 회전시키는 작업 수행
 * 	2-1. R을 테두리의 길이로 나눈 나머지 만큼 회전
 *    	2-2. 테두리 시작점을 따로 빼고 시계방향의 값을 반시계 방향으로 이동하기
 *    	2-3. 배열 테두리 깊이 줄이기
 * 3. 배열 출력하기
 * 
 * 배열 돌리기1 문제와의 차이점은 회전 수(R)이 10억까지 가능하기 때문에 배열을 R번 회전하면 무조건 시간초과가 난다.
 * 따라서 회전수를 줄여줘야 하는데 배열의 테두리를 따라 있는 라인의 원소의 수만큼 회전시키면 다시 원위치로 돌아오게 된다는 점을 이용한다.
 * 이를 식으로 만들면 (N+M)x2 - 4 만큼 회전하면 원위치로 돌아오게 되고
 * 결국 회전수(R)을 (N+M)x2 - 4로 나눠서 나온 나머지만큼만 회전시키면 된다.
 * 위의 식에서 -4를 해주는 이유는 각 꼭짓점이 두번씩 겹치기 때문에 한번 빼준다.
 * 예를 들어보면 6x7 배열에서 50번 회전시키면 50%22=6만큼만 회전시키는 것과 똑같다.
 * 
 * 배열의 원소의 값을 이동시키는 식은 위/아래/오른쪽/왼쪽으로 나누어서 식을 작성하지 않고
 * dx, dy를 사용해 좌표값을 한바퀴 돌리는 방식을 사용했다.
 * 
 * @author YooSejin
 *
 */

public class Main {
	
	static int N;
	static int M;
	static int R;
	static int[][] arr;
	
	static int[] dx = { 0, 1, 0, -1 }; // 우하좌상
	static int[] dy = { 1, 0, -1, 0 };
	
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
        
        // 배열을 한번 회전시키기 위해 (Math.min(N,M)/2)개의 라인을 회전시켜야 한다.
        int min = Math.min(N, M) / 2;
        
        for(int i=0; i<min; i++) { // min개의 라인을 회전시킨다.
        	// R번 회전하는 것은 (R%(라인의 원소의 개수))번 회전하는 것과 같다.
        	int cnt = R % (((N - 2*i)+(M - 2*i)) * 2 - 4);
        	rotate(i, cnt);
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
	
	// idx : 시작 좌표 (idx, idx)
	// cnt : cnt번 회전
	static void rotate(int idx, int cnt) {
		for(int i=0; i<cnt; i++) {
			int x = idx;
			int y = idx;
			int tmp = arr[x][y];
			
			int dir = 0;
			
			// 우하좌상으로 방향을 바꿔가며 테두리 한바퀴 돌면서 값 이동시키기
			while(dir < 4) {
				int nx = x + dx[dir];
				int ny = y + dy[dir];
				
				// 테두리의 범위를 벗어나면 방향 바꾸기
				if(nx < idx || nx >= N-idx || ny < idx || ny >= M-idx) {
					dir++;
					continue;
				}
				
				arr[x][y] = arr[nx][ny];
				x = nx;
				y = ny;
			}
			
			arr[idx+1][idx] = tmp; // 예외처리 꼭 해주기
		}
	}

}
