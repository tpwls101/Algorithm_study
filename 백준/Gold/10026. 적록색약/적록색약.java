import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * <BJ_10026_적록색약>
 * Flood Fill (= Seed Fill) : 다차원 배열의 어떤 칸과 연결된 영역을 찾는 알고리즘.
 *                                 주어진 시작점으로부터 연결된 영역을 찾는 알고리즘.
 *                                 (영역 채우는 알고리즘)
 *                                 ex. 땅과 바다 -> 섬 개수 구하기
 * 
 * -> BFS 사용 -> 4방 탐색해서 같은 색이면 하나의 구역으로 취급
 * 
 * bfs 호출할 때 사용되는 r과 c는 고정된 값(처음 bfs를 호출한 좌표)
 * 하지만 temp_x, temp_y를 결정하는 현재 값의 x좌표, y좌표는 계속 바뀐다! -> 클래스 사용
 * 
 * 
 * @author 유세진
 *
 */

public class Main {
	
	// 상좌하우
	static int[] dx = { -1, 0, 1, 0 }; // 행
	static int[] dy = { 0, -1, 0, 1 }; // 열
		
	static int N; // 그리드의 크기
    static String[][] grid; // NxN 그리드
    static boolean[][] visited; // 방문체크
    static int answer1 = 0; // 적록색약이 아닌 사람이 봤을 때의 구역의 개수
    static int answer2 = 0; // 적록색약인 사람이 봤을 때의 구역의 개수
	
	static class Color {
		int x; // x좌표
		int y; // y좌표
		String color; // 색깔
		
		public Color(int x, int y, String color) {
			this.x = x;
			this.y = y;
			this.color = color;
		}
	}
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        
        N = Integer.parseInt(br.readLine()); // 그리드의 크기
        
        grid = new String[N][N]; // NxN 그리드
        visited = new boolean[N][N]; // 방문체크
        
        // 그림 입력받기
        for(int i=0; i<N; i++) {
            String[] s = br.readLine().split("");
            for(int j=0; j<N; j++) {
                grid[i][j] = s[j];
            }
        }
        
        // 적록색약이 아닌 사람이 봤을 때의 구역의 개수 구하기
        for(int i=0; i<N; i++) {
            for(int j=0; j<N; j++) {
                if(!visited[i][j]) {
                    bfs(new Color(i, j, grid[i][j])); // x좌표, y좌표, 색깔 전달
                    answer1++; // 한 구역에 대한 bfs가 끝났으므로 구역 카운트 증가
                }
            }
        }
        
        // 적록색약인 사람이 봤을 때의 구역의 개수 구하기
        for(int i=0; i<N; i++) {
        	for(int j=0; j<N; j++) {
        		if(grid[i][j].equals("R")) { // 그리드에서 빨간색을
        			grid[i][j] = "G"; // 모두 초록색으로 바꿔주어 처리 -> 그리드에는 초록색과 파란색밖에 없음
        		}
        	}
        }
        visited = new boolean[N][N]; // 다시 초기화
        for(int i=0; i<N; i++) {
            for(int j=0; j<N; j++) {
                if(!visited[i][j]) {
                	bfs(new Color(i, j, grid[i][j])); // x좌표, y좌표, 색깔 전달
                	answer2++;
                }
            }
        }
        
        System.out.println(answer1 + " " + answer2);
    }
    
    // 적록색약이 아닌 사람이 봤을 때의 구역의 개수 구하기
    private static void bfs(Color color) {
    	Queue<Color> queue = new ArrayDeque<>();
    	queue.offer(color);
    	visited[color.x][color.y] = true;
    	
    	while(!queue.isEmpty()) {
    		Color current = queue.poll(); // 현재 큐의 원소
    		
    		int temp_x;
    		int temp_y;
    		
    		// 4방 탐색
    		for(int i=0; i<4; i++) {
    			temp_x = current.x + dx[i];
    			temp_y = current.y + dy[i];
    			
    			// 그리드의 범위 안에 있을 때만 탐색
    			if(temp_x >= 0 && temp_x < N && temp_y >= 0 && temp_y < N) {
    				// 4방 탐색해서 방문안했고 현재 큐의 원소와 같은 색이면
    				if(!visited[temp_x][temp_y] && grid[temp_x][temp_y].equals(current.color)) {
    					queue.offer(new Color(temp_x, temp_y, color.color)); // 큐에 삽입
    					visited[temp_x][temp_y] = true; // 방문여부 true로
    				}
    			}
    		}
    	}
        
    	// while문이 끝나면 한 구역 생성 완료
    	
    }

}