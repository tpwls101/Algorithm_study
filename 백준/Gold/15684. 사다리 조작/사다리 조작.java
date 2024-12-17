import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

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
        
        // 확인
//        for(int i=0; i<H+1; i++) {
//        	for(int j=0; j<N+1; j++) {
//        		System.out.print(arr[i][j] + " ");
//        	}
//        	System.out.println();
//        }
//        System.out.println();
        
        // 사다리를 0~3개까지 놓을 수 있다
        for(int i=0; i<=3; i++) {
//        	System.out.println("사다리 " + i + "개 설치");
        	answer = i;
        	dfs(1, 1, 0);
        	if(finish) break;
//        	System.out.println("사다리 " + i + "개 설치할 때 확인 완료");
//        	System.out.println();
        }
		
        System.out.println(finish ? answer : -1);
	}
	
	// (x, y) : 탐색을 시작할 x, y 좌표
	// cnt : 사다리를 놓은 갯수
	static void dfs(int x, int y, int cnt) {
		if(finish) return;
		
		// 사다리가 0~3개만큼 설치되면 사다리가 자기 자신의 번호로 나오는지 확인하기
		if(cnt == answer) {
//			System.out.println("확인");
//			for(int i=0; i<H+1; i++) {
//	        	for(int j=0; j<N+1; j++) {
//	        		System.out.print(arr[i][j] + " ");
//	        	}
//	        	System.out.println();
//	        }
			
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
//			System.out.println(i + "번 사다리는 " + y + "로 나옴");
			
			// 하나라도 자기 번호로 나오지 않으면 false 리턴
			if(y != i) return false;
		}
//		System.out.println();
		
		return true;
	}

}