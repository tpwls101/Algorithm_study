import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	
	static int N; // 체스판의 크기
	static int K; // 말의 개수
	static int[][] chess; // 체스판(0:흰색, 1:빨간색, 2:파란색)
	static List<Chessman>[][] chessman; // 체스말의 현재 위치를 나타냄
	static List<Node> position = new ArrayList<>(); // K개의 말의 위치를 저장
	static boolean finish = false;
	
	// 동서북남
	static int[] dx = { 0, 0, -1, 1 };
	static int[] dy = { 1, -1, 0, 0 };
	
	static class Chessman {
		int num; // 말 번호
		int dir; // 방향
		
		Chessman(int num, int dir) {
			this.num = num;
			this.dir = dir;
		}
	}
	
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
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
		
        chess = new int[N][N];
        chessman = new ArrayList[N][N];
        for(int i=0; i<N; i++) {
        	for(int j=0; j<N; j++) {
        		chessman[i][j] = new ArrayList<>();
        	}
        }
		
        for(int i=0; i<N; i++) {
        	st = new StringTokenizer(br.readLine());
        	for(int j=0; j<N; j++) {
        		chess[i][j] = Integer.parseInt(st.nextToken());
        	}
        }
        
        for(int i=0; i<K; i++) {
        	st = new StringTokenizer(br.readLine());
        	int x = Integer.parseInt(st.nextToken()) - 1;
        	int y = Integer.parseInt(st.nextToken()) - 1;
        	int dir = Integer.parseInt(st.nextToken()) - 1; // 0:동, 1:서, 2:북, 3:남
        	
        	chessman[x][y].add(new Chessman(i, dir));
        	position.add(new Node(x, y));
        }
        
        int count = 0; // 게임 턴 수
        
        while(count <= 1000) {
        	if(finish) break;
        	
        	count++;
        	
        	// 한 턴에 K개의 말 차례대로 이동
        	for(int i=0; i<K; i++) {
        		// 말이 4개 이상 쌓이면 즉시 게임 종료
        		if(finish) break;
        		
        		Node cur = position.get(i); // i번째 말의 현재 위치
//        		System.out.println("현재 x좌표 = " + cur.x + ", 현재 y좌표 = " + cur.y);
        		
        		Chessman current = null; // 현재 체스말
        		int index = 0; // 현재 말의 인덱스
        		
//        		System.out.println("현재 좌표의 크기 : " + chessman[cur.x][cur.y].size());
//        		for(Chessman cm : chessman[cur.x][cur.y]) {
//        			System.out.println("체스말 번호 : " + cm.num);
//        		}
        		
        		for(int j=0; j<chessman[cur.x][cur.y].size(); j++) {
        			Chessman tmp = chessman[cur.x][cur.y].get(j);
        			if(tmp.num == i) {
//        				System.out.println("@@@@@");
        				current = tmp;
        				index = j; // j번째 이후의 체스말은 함께 이동
        			}
        		}
        		
        		int nx = cur.x + dx[current.dir];
        		int ny = cur.y + dy[current.dir];
        		
//        		System.out.println("다음칸 색깔 : " + chess[nx][ny]);
        		
        		if(isRange(nx, ny)) {
        			if(chess[nx][ny] == 0) { // 흰 칸
        				white(index, cur.x, cur.y, nx, ny);
//        				System.out.println("하얀 칸으로 이동 완료");
        			} else if(chess[nx][ny] == 1) { // 빨간 칸
//        				System.out.println("빨간칸");
        				red(index, cur.x, cur.y, nx, ny);
//        				System.out.println("빨간 칸으로 이동 완료");
        			} else { // 파란 칸
        				blue(index, cur.x, cur.y);
//        				System.out.println("다음 칸이 파란 칸인 관계로 반대 방향으로 이동 또는 제자리에 유지");
        			}
        		} else {
        			blue(index, cur.x, cur.y);
//        			System.out.println("다음 칸이 범위를 벗어나므로 반대 방향으로 이동 또는 제자리에 유지");
        		}
//        		System.out.println();
        	}
        	
//        	System.out.println("=== K개의 말 이동 후 확인 ===");
//        	System.out.println(chessman[3][0].get(0).num + "번 체스말의 방향 : " + chessman[3][0].get(0).dir);
        	
//        	System.out.println("count = " + count);
        }
        
        System.out.println(count > 1000 ? -1 : count);
	}
	
	static void white(int index, int x, int y, int nx, int ny) {
//		System.out.println("=== white 실행 ===");
//		System.out.println("index = " + index);
//		System.out.println("현재 x좌표 = " + cur.x + ", 현재 y좌표 = " + cur.y);
//		System.out.println("nx = " + nx + ", ny = " + ny);
//		System.out.println("현재 좌표의 size = " + chessman[cur.x][cur.y].size());
		
		List<Chessman> list = new ArrayList<>();
		
		// 다음 칸에 체스말 추가
		int size = chessman[x][y].size();
		for(int i=index; i<size; i++) {
//			System.out.println("i = " + i);
//			System.out.println("현재 x좌표 = " + cur.x + ", 현재 y좌표 = " + cur.y);
//			System.out.println("현재 좌표의 size = " + chessman[cur.x][cur.y].size());
			
			Chessman tmp = chessman[x][y].get(i);
//			System.out.println("체스말 번호 = " + tmp.num);
			chessman[nx][ny].add(tmp);
			
			list.add(tmp);
			// 위치 갱신
//			position.get(tmp.num).x = nx;
//			position.get(tmp.num).y = ny;
		}
		
		// 기존 칸의 체스말 제거
		for(int j=size-1; j>=index; j--) {
			chessman[x][y].remove(j);
		}
		
		// 위치 갱신
		for(Chessman cm : list) {
			position.get(cm.num).x = nx;
			position.get(cm.num).y = ny;
		}
		
		// 말이 4개 이상 쌓이면 게임 종료
//		System.out.println("check");
		check(nx, ny);
	}
	
	static void red(int index, int x, int y, int nx, int ny) {
//		System.out.println("=== red 실행 ===");
//		System.out.println("index = " + index);
		
		List<Chessman> list = new ArrayList<>();
		
		// 다음 칸에 체스말 거꾸로 추가 + 기존 칸의 체스말 제거
		int size = chessman[x][y].size();
		for(int j=size-1; j>=index; j--) {
			Chessman tmp = chessman[x][y].get(j);
//			System.out.println("체스말 번호 : " + tmp.num);
			chessman[nx][ny].add(tmp);
//			System.out.println(tmp.num + "번 체스말 다음 칸으로 이동 완료");
			list.add(tmp);
			chessman[x][y].remove(j);
//			System.out.println("기존 위치의 체스말 제거");
			// 위치 갱신
//			position.get(tmp.num).x = nx;
//			position.get(tmp.num).y = ny;
		}
		
		// 위치 갱신
		for(Chessman cm : list) {
			position.get(cm.num).x = nx;
			position.get(cm.num).y = ny;
		}
		
		// 말이 4개 이상 쌓이면 게임 종료
		check(nx, ny);
	}
	
	static void blue(int index, int x, int y) {
//		System.out.println("index = " + index);
//		System.out.println("현재 x좌표 = " + x + ", 현재 y좌표 = " + y);
		
		int size = chessman[x][y].size();
//		System.out.println(size);
		
		for(int j=index; j<size; j++) {
//			System.out.println("j = " + j);
			Chessman tmp = chessman[x][y].get(j);
			
			// 이동 방향 반대로 바꾸기
			if(tmp.dir % 2 == 0) {
				tmp.dir += 1;
			} else {
				tmp.dir -= 1;
			}
//			System.out.println("방향 = " + tmp.dir);
			
			int nx = x + dx[tmp.dir];
			int ny = y + dy[tmp.dir];
//			System.out.println("nx = " + nx + ", ny = " + ny);
			
			// 범위를 벗어나거나 파란색이면 이동하지 않는다
			if(!isRange(nx, ny) || chess[nx][ny] == 2) {
//				System.out.println("범위를 벗어나거나 파란색이면 이동하지 않는다");
//				continue;
				break;
			}
			
			// 이동할 수 있다면 이동
			if(chess[nx][ny] == 0) {
				white(j, x, y, nx, ny);
				break; // 주의!! -> 다음 칸이 파란색이라 방향을 바꿔 다음 칸으로 이동할 때 흰색이나 빨간색이면 현재 체스말 위도 전부 이동하므로 다시 for문을 돌면 안된다. break로 빠져나와야 함!
			} else if(chess[nx][ny] == 1) {
				red(j, x, y, nx, ny);
				break;
			}
		}
	}
	
	// 말이 4개 이상 쌓였는지 확인
	static void check(int x, int y) {
//		System.out.println("=== check 함수 ===");
//		System.out.println("현재 칸에 있는 체스말 번호 확인");
//		for(int i=0; i<chessman[x][y].size(); i++) {
//			System.out.print(chessman[x][y].get(i).num + " ");
//		}
//		System.out.println();
		
		if(chessman[x][y].size() >= 4) {
			finish = true;
		}
	}
	
	static boolean isRange(int x, int y) {
		if(x >= 0 && x < N && y >= 0 && y < N) {
			return true;
		}
		return false;
	}

}
