import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

/**
 * <BJ_18428_감시피하기>
 * 브루트포스 + 백트래킹
 * 장애물을 놓을 수 있는 모든 경우의 수를 확인하고, 학생을 만나는 경우에는 false를 리턴해서 가지치기
 * 
 * 1. 입력값 받으면서 선생님의 위치 List에 저장
 * 2. 빈칸 중 장애물을 놓을 위치 3곳 고르기 -> 조합
 * 		2-1. 선생님의 위치 for문 돌리면서 상하좌우로 학생을 볼 수 있는지 확인
 * 		2-2. 장애물이 있으면 다음 방향 확인. 장애물 없고 학생을 보게 되면 false 리턴.
 * 3. 모든 학생이 감시를 피하는 경우 즉, true가 리턴되면 다른 경우의 수는 확인할 필요가 없고 "YES" 출력
 * 
 * @author YooSejin
 *
 */

public class Main {
	
	static int N;
	static String[][] arr;
	static List<Node> teacher;
	static Node[] obstacle;
	static String[][] copy;
	static boolean possible = false;
	
	static int[] dx = { -1, 0, 1, 0 };
	static int[] dy = { 0, 1, 0, -1 };
	
	static class Node {
		int x;
		int y;
		
		Node(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		
		N = Integer.parseInt(br.readLine());
		
		arr = new String[N][N];
		teacher = new ArrayList<>();
		obstacle = new Node[3];
		copy = new String[N][N];
		
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0; j<N; j++) {
				arr[i][j] = st.nextToken();
				if(arr[i][j].equals("T")) {
					teacher.add(new Node(i, j));
				}
			}
		}
		
		comb(0, 0); // 빈칸 중 장애물을 놓을 위치 세 곳 선정
		
		System.out.println(possible ? "YES" : "NO");
	}
	
	static void comb(int cnt, int start) {
		if(cnt == 3) {
			if(check()) possible = true;
			return;
		}
		
		for(int i=start; i<N*N; i++) {
			int x = i / N;
			int y = i % N;
			
			if(arr[x][y].equals("X")) {
				obstacle[cnt] = new Node(x, y);
				comb(cnt+1, i+1);
				if(possible) return; // 이미 감시를 피할 수 있다고 판정하면 다음을 확인할 필요 없음
			}
		}
	}
	
	static boolean check() {
		// 원본 배열 복사
		for(int i=0; i<N; i++) {
			copy[i] = arr[i].clone();
		}
		
		// 장애물 위치 표시
		for(int i=0; i<3; i++) {
			copy[obstacle[i].x][obstacle[i].y] = "O";
		}
		
		for(int i=0; i<teacher.size(); i++) {
			Node t = teacher.get(i);
			
			for(int dir=0; dir<4; dir++) {
				for(int k=1; k<N; k++) {
					int nx = t.x + dx[dir]*k;
					int ny = t.y + dy[dir]*k;
					
					// 범위 벗어나면 다음 방향 탐색
					if(!isRange(nx, ny)) break;
					
					if(copy[nx][ny].equals("O")) break; // 장애물이 있다면 다음 방향 탐색
					if(copy[nx][ny].equals("S")) return false; // 선생님이 학생을 보면 감시 피하기 실패. false 리턴.
				}
			}
		}
		
		return true; // 모든 선생님이 학생을 보지 못하면 true 리턴. 감시 피하기 성공.
	}
	
	static boolean isRange(int x, int y) {
		if(x >= 0 && x < N && y >= 0 && y < N) {
			return true;
		}
		return false;
	}

}
