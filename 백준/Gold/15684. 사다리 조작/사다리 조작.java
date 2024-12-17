import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * <BJ_15684_사다리조작>
 * 1. 사다리 정보를 어떻게 표현할 것인가?
 * 		- 2차원 배열 사용
 * 		- 연결된 사다리가 없다면 0
 * 		- i번째 사다리가 우측과 연결되어 있다면 1
 * 		- i번째 사다리가 좌측과 연결되어 있다면 2
 * 		- 이렇게 하면 HxN 2차원 배열로 사다리의 연결 상태를 표현할 수 있음
 * 2. 추가 사다리를 어떻게 둘 것인지?
 * 		- 추가할 사다리가 3개를 초과하면 -1 을 출력해야 함
 *		- 따라서 추가할 사다리가 0, 1, 2, 3일 때 모든 경우의 수를 탐색해 자기 자신의 번호로 나오는지 확인해주면 됨
 *		- 추가한 사다리가 0~3일 때를 DFS 종료조건으로 사용
 *
 * <시간 복잡도>
 * 추가 사다리를 놓을 수 있는 총 위치의 수 = P = (N-1)*H
 * 사다리를 놓는 경우의 수는?
 * 추가할 사다리가 0일 때 -> 1
 * 추가할 사다리가 1일 때 -> P = N*H
 * 추가할 사다리가 2일 때 -> pC2 = P*(P-1) / 2! = P^2 = (N*H)^2
 * 추가할 사다리가 3일 때 -> pC3 = P*(P-1)*(P-2) / 3! = P^3 = (N*H)^3
 * 
 * 각 경우의 수마다 자기 자신의 번호로 나오는지 확인해야 함 -> O(N*H)
 * 따라서 O((N*H)^4)의 시간복잡도를 가짐
 * 단, 이는 최악의 경우일 때고 실제 값으로 계산을 해보면 10억 이내로 2초 안에 연산 수행이 가능하다.
 * (1초에 1억번의 연산을 수행하는데 가지치기를 하니 가능한가보다. 시간 복잡도는 아직도 이해가 어렵다ㅜㅜ)
 * 
 * @author YooSejin
 *
 */

public class Main {
	
	static int N; // 세로선의 개수
	static int M; // 가로선의 개수(사다리의 개수)
	static int H; // 세로선마다 가로선을 놓을 수 있는 위치의 개수
	static int[][] arr; // 0(연결x), 1(우측과 연결), 2(좌측과 연결)
	static boolean finish = false;
	static int answer = 0;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        H = Integer.parseInt(st.nextToken());
		
		arr = new int[H+1][N+1];
        
        for(int i=0; i<M; i++) {
        	st = new StringTokenizer(br.readLine());
        	int pos = Integer.parseInt(st.nextToken());
        	int ladderNum = Integer.parseInt(st.nextToken());
        	arr[pos][ladderNum] = 1;
        	arr[pos][ladderNum+1] = 2;
        }
        
        // 사다리를 0~3개까지 놓을 수 있다
        for(int i=0; i<=3; i++) {
        	answer = i;
        	dfs(1, 1, 0);
        	if(finish) break;
        }
		
        System.out.println(finish ? answer : -1);
	}
	
	// (x, y) : 탐색을 시작할 x, y 좌표
	// cnt : 사다리를 놓은 갯수
	static void dfs(int x, int y, int cnt) {
		if(finish) return;
		
		// 사다리가 0~3개만큼 설치되면 사다리가 자기 자신의 번호로 나오는지 확인하기
		if(cnt == answer) {
			if(check()) finish = true;
			return;
		}
		
		// 주의 : i=x, j=y로 하면 앞부분 y좌표를 탐색할 수 없으니 주의!
		for(int i=1; i<=H; i++) {
			for(int j=1; j<N; j++) {
				// 사다리가 연속으로 한 가로선에 놓일 수 없으므로 0인 곳에만 사다리를 설치할 수 있음
				if(arr[i][j] == 0 && arr[i][j+1] == 0) {
					arr[i][j] = 1;
					arr[i][j+1] = 2;
					
					dfs(i, j, cnt+1);
					
					arr[i][j] = 0;
					arr[i][j+1] = 0;
				}
			}
		}
	}
	
	// 모든 i번 사다리가 자기 자신의 번호로 나오는지 확인하는 메서드
	static boolean check() {
		for(int i=1; i<=N; i++) {
			int x = 1;
			int y = i;
			
			while(x <= H) {
				if(arr[x][y] == 1) y++;
				else if(arr[x][y] == 2) y--;
				x++;
			}
			
			// 하나라도 자기 번호로 나오지 않으면 false 리턴
			if(y != i) return false;
		}
		
		return true;
	}

}