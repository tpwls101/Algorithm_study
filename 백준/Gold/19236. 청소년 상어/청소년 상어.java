import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;


/**
 * <BJ_19236_청소년상어>
 * DFS, 백트래킹
 * 백트래킹을 할 때는 빠져 나오면서 값들을 원래 값으로 돌려놓아야 하는데
 * 배열이나 물고기 리스트들이 바뀌는게 많기 때문에 다음 DFS로 넘기는 값은 전부 복사한 새로운 값을 사용한다.
 * 
 * arr 배열에서는 물고기 번호를 관리
 * fishes 리스트에서는 항상 16마리의 물고기가 있고, isAlive로 잡아먹혔는지 관리. 인덱스 유지. (x, y) 좌표 관리 잘해야 함.
 * 
 * @author YooSejin
 *
 */

public class Main {
	
	static int maxSum = 0;
	
	static int[] dx = { -1, -1, 0, 1, 1, 1, 0, -1 };
	static int[] dy = { 0, -1, -1, -1, 0, 1, 1, 1 };
	
	static class Fish {
		int x, y, num, dir;
		boolean isAlive = true;
		
		Fish(int x, int y, int num, int dir, boolean isAlive) {
			this.x = x;
			this.y = y;
			this.num = num;
			this.dir = dir;
			this.isAlive = isAlive;
		}
	}
	
	static class Shark {
		int x, y, dir, eatSum;
		
		Shark(int x, int y, int dir, int eatSum) {
			this.x = x;
			this.y = y;
			this.dir = dir;
			this.eatSum = eatSum;
		}
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;
        
        int[][] arr = new int[4][4];
        List<Fish> fishes = new ArrayList<>();
        
        // 입력값
		for(int i=0; i<4; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0; j<4; j++) {
				int num = Integer.parseInt(st.nextToken());
				int dir = Integer.parseInt(st.nextToken()) - 1;
				
				fishes.add(new Fish(i, j, num, dir, true));
				arr[i][j] = num;
			}
		}
		
		// 물고기는 번호가 작은 순부터 이동하므로 정렬
		Collections.sort(fishes, (o1, o2) -> o1.num - o2.num);
		
		// 상어는 (0, 0)에 있는 물고기를 먹고 시작, 번호는 -1 사용
		Fish fish = fishes.get(arr[0][0] - 1);
		Shark shark = new Shark(0, 0, fish.dir, fish.num);
		fish.isAlive = false;
		arr[0][0] = -1;
		
		dfs(arr, fishes, shark);
		
		System.out.println(maxSum);
	}
	
	// 재귀로 상어가 이동할 수 없을 때까지 반복한다.
	static void dfs(int[][] arr, List<Fish> fishes, Shark shark) {
		// 잡아먹은 양의 최댓값 구하기
		maxSum = Math.max(maxSum, shark.eatSum);
		
		// 모든 물고기는 순서대로 이동
		fishes.forEach(e -> move(e, arr, fishes));
		
		// 상어 이동
		for(int dist=1; dist<4; dist++) {
			int nx = shark.x + dx[shark.dir] * dist;
			int ny = shark.y + dy[shark.dir] * dist;
			
			// 상어는 물고기가 있는 칸으로만 이동 가능
			if(nx >= 0 && nx < 4 && ny >= 0 && ny < 4 && arr[nx][ny] > 0) {
				// arr 배열과 fishes 리스트는 변경되는 값이 많으므로 복사해서 다음 재귀에서 사용
				int[][] copyArr = copyArr(arr);
				List<Fish> copyFishes = copyFishes(fishes);
				
				// 물고기 잡아먹고 dfs
				copyArr[shark.x][shark.y] = 0;
				copyArr[nx][ny] = -1;
				
				Fish fish = copyFishes.get(arr[nx][ny] - 1); // 이동할 좌표에 있는 물고기
				Shark newShark = new Shark(fish.x, fish.y, fish.dir, shark.eatSum + fish.num);
				fish.isAlive = false;
				
				dfs(copyArr, copyFishes, newShark);
			}
		}
	}
	
	// 빈 칸이나 다른 물고기가 있는 칸으로만 이동 가능 (= 상어가 없는 칸)
	// 이동할 수 없으면 될 때까지 45도 반시계 방향으로 회전
	static void move(Fish fish, int[][] arr, List<Fish> fishes) {
		if(!fish.isAlive) return;
		
		for(int i=0; i<8; i++) {
			int dir = (fish.dir + i) % 8;
			int nx = fish.x + dx[dir];
			int ny = fish.y + dy[dir];
			
			// 이동가능한 경우 이동시키고 리턴
			if(nx >= 0 && nx < 4 && ny >= 0 && ny < 4 && arr[nx][ny] >= 0) {
				if(arr[nx][ny] == 0) { // 빈 칸일 때
					arr[fish.x][fish.y] = 0;
					fish.x = nx;
					fish.y = ny;
				} else { // 물고기가 있는 칸일 때
					Fish temp = fishes.get(arr[nx][ny] - 1);
					temp.x = fish.x;
					temp.y = fish.y;
					arr[fish.x][fish.y] = temp.num;
					
					fish.x = nx;
					fish.y = ny;
				}
				
				arr[nx][ny] = fish.num;
				fish.dir = dir;
				return;
			}
		}
	}
	
	static int[][] copyArr(int[][] arr) {
		int[][] copy = new int[4][4];
		
		for(int i=0; i<4; i++) {
			copy[i] = arr[i].clone();
		}
		
		return copy;
	}
	
	// 참조값을 공유하면 모든 값이 변경되므로 깊은 복사해서 사용 (새로운 객체로 리스트 생성)
	static List<Fish> copyFishes(List<Fish> fishes) {
		List<Fish> copy = new ArrayList<>();
		fishes.forEach(e -> copy.add(new Fish(e.x, e.y, e.num, e.dir, e.isAlive)));
		return copy;
	}

}
