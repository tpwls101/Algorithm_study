import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * <BJ_2239_스도쿠> - 완전탐색! (DFS + 백트래킹)
 * 9x9 스도쿠이므로 81개의 각 칸에 대해 완전탐색! -> DFS
 * 
 * 0이 있는 자리에 1~9까지의 숫자를 하나씩 넣어보고 쓸 수 있는 숫자인지 확인!
 * 가로, 세로, 3x3 사각형에서 해당 숫자를 사용하고 있는지 확인하여
 * 이미 사용중이라 쓸 수 없다면 false를 반환하고 사용 가능하면 true를 반환한다!
 * 
 * @author YooSejin
 *
 */

public class Main {
	
	public static int[][] arr = new int[9][9];
	public static boolean flag;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		// 입력받기
		for(int i=0; i<9; i++) {
			String s = br.readLine();
			for(int j=0; j<9; j++) {
				arr[i][j] = s.charAt(j) - '0';
			}
		}
		
		dfs(0);
		
	}
	
	// 81개의 각 칸을 0인지 아닌지 판별하여 0이라면 1~9까지의 숫자가 들어갈 수 있는지 확인하는 함수
	// cnt : 9x9 중 cnt번째 칸
	public static void dfs(int cnt) {
		// 기저조건
		if(cnt == 81) {
            
            // 출력
		    for(int i=0; i<9; i++) {
		    	for(int j=0; j<9; j++) {
			    	System.out.print(arr[i][j]);
			    }
			    System.out.println();
		    }
            System.exit(0);
            
			//flag = true;
			return;
		}
		
		int r = cnt / 9; // 행
		int c = cnt % 9; // 열
		
		if(arr[r][c] != 0) { // 0이 아닌 숫자가 써있으면
			dfs(cnt+1); // 다음 칸 탐색
			
		} else { // 0이면 어떤 숫자를 넣을지 탐색
			for(int num=1; num<=9; num++) {
				// arr[r][c]에 num을 넣을 수 없으면 다음 숫자 확인
				if(!isValid(r, c, num)) continue;
				
				// arr[r][c]에 num을 넣을 수 있으면 arr[r][c]를 num으로 바꿔주고 다음 칸 탐색
				arr[r][c] = num;
				//System.out.println(cnt + " " + arr[r][c]);
				dfs(cnt+1);
				
				//if(flag) return;
				// cnt+1 칸을 확인했을 때 더 이상 진행할 수 없을 때
				// arr[r][c]에 num을 사용할 수 없으므로 다시 0으로 리셋
				arr[r][c] = 0;
			}
		}
		
	}
	
	// r행 c열에 num 숫자를 넣을 수 있는지 확인하는 메서드
	public static boolean isValid(int r, int c, int num) {
		// 가로 또는 세로에 내가 넣으려는 숫자가 이미 있다면 false 반환
		for(int i=0; i<9; i++) {
			if(arr[r][i] == num || arr[i][c] == num) {
				return false;
			}
		}
		
		// 3x3 사각형에 내가 넣으려는 숫자가 이미 있다면 false 반환
		int sr = (r/3) * 3; // 3x3 사각형의 시작 행
		int sc = (c/3) * 3; // 3x3 사각형의 시작 열
		for(int i=sr; i<sr+3; i++) {
			for(int j=sc; j<sc+3; j++) {
				if(arr[i][j] == num) {
					return false;
				}
			}
		}
		
		// 가로, 세로, 3x3 사각형을 확인했는데 num을 사용할 수 있다면 true 반환
		return true;
	}

}
