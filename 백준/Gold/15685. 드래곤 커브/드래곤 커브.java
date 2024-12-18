import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

/**
 * <BJ_15685_드래곤커브>
 * Q. 드래곤 커브를 어떻게 그릴 것인가?
 * 하나의 드래곤 커브를 그릴 때의 방향을 차례대로 리스트에 저장한다.
 * 이 때, 현재까지 리스트에 저장된 방향을 뒤에서부터 꺼내 반시계 방향으로 돌리면 다음의 방향을 알 수 있다!! (힌트)
 * 
 * @author YooSejin
 *
 */

public class Main {
	
	static int N; // 드래곤 커브의 개수
	static boolean[][] arr = new boolean[101][101]; // x,y좌표가 0~100까지 가능하므로 각 좌표를 배열로 생성
	static int answer = 0;
	
	static int[] dx = { 0, -1, 0, 1 };
	static int[] dy = { 1, 0, -1, 0 };
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;
        
        N = Integer.parseInt(br.readLine());
        
        for(int i=0; i<N; i++) {
        	st = new StringTokenizer(br.readLine());
        	int y = Integer.parseInt(st.nextToken());
        	int x = Integer.parseInt(st.nextToken());
        	int d = Integer.parseInt(st.nextToken()); // 방향
        	int g = Integer.parseInt(st.nextToken()); // 세대
        	
        	makeDragonCurve(x, y, d, g);
        }
		
        // 네 꼭짓점이 모두 드래곤 커브의 일부인 정사각형의 개수 구하기 (완탐)
		for(int i=0; i<100; i++) {
			for(int j=0; j<100; j++) {
				if(arr[i][j] && arr[i][j+1] && arr[i+1][j] && arr[i+1][j+1]) {
					answer++;
				}
			}
		}
		
		System.out.println(answer);
	}
	
	static void makeDragonCurve(int x, int y, int d, int g) {
		arr[x][y] = true; // 시작점
		
		// g세대까지 드래곤 커브를 그릴 때의 방향을 리스트에 저장
		List<Integer> directionList = new ArrayList<>();
		directionList.add(d);
		
		for(int i=0; i<g; i++) {
			for(int j=directionList.size()-1; j>=0; j--) {
				int nextDir = (directionList.get(j) + 1) % 4; // 반시계 방향
				directionList.add(nextDir);
			}
		}
		
		// 방향대로 드래곤 커브 그리기
		for(int dir : directionList) {
			int nx = x + dx[dir];
			int ny = y + dy[dir];
			arr[nx][ny] = true;
			x = nx;
			y = ny;
		}
	}

}