import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;

/**
 * <BJ_14226_이모티콘>
 * BFS문제
 * 3가지 연산의 경우의 수를 큐에 넣어주면 되는 문제지만, 방문처리가 까다로운 문제다.
 * 
 * boolean[][] visited = new boolean[2001][2001]; // [화면에 있는 이모티콘 수][클립보드에 있는 이모티콘 수]
 * 먼저 크기를 안정적으로 2000으로 설정한다.
 * 이모티콘은 최대 1000개까지 만들 수 있다.
 * 현재 화면에 1000개가 있는데 이를 클립보드에 복사해 붙여넣기 하면 screen은 2000이 되므로 크기를 1000으로 하는 것은 인덱스범위 에러가 날 수 있다.
 * 
 * visited[화면에 있는 이모티콘 수][클립보드에 있는 이모티콘 수]를 방문했다면,
 * 이를 다시 큐에 넣는 것은 중복 처리만 늘어날 뿐이다.
 * 따라서 이미 방문했다면 큐에 추가하지 않는다.
 * 
 * @author YooSejin
 *
 */

public class Main {
	
	static int S; // 만들 이모티콘 수
	static boolean[][] visited = new boolean[2001][2001]; // [화면에 있는 이모티콘 수][클립보드에 있는 이모티콘 수]
	static int answer = 0;
	
	static class Emoji {
		int screen; // 화면에 있는 이모티콘 수
		int clipboard; // 클립보드에 있는 이모티콘 수
		int time; // 걸린 시간
		
		Emoji(int screen, int clipboard, int time) {
			this.screen = screen;
			this.clipboard = clipboard;
			this.time = time;
		}
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		S = Integer.parseInt(br.readLine());
		
		bfs(new Emoji(1, 0, 0));
		
		System.out.println(answer);
	}
	
	static void bfs(Emoji e) {
		Queue<Emoji> queue = new ArrayDeque<>();
		queue.add(e);
		visited[e.screen][e.clipboard] = true;
		
		while(!queue.isEmpty()) {
			Emoji current = queue.poll();
			
			if(current.screen == S) {
				answer = current.time;
				break;
			}
			
			// 1번 연산 : 화면에 있는 이모티콘을 모두 복사해 클립보드에 저장
			// 화면에 이모티콘이 1개 이상 있어야 복사 가능
			if(current.screen > 0) {
				if(!visited[current.screen][current.screen]) {
					queue.add(new Emoji(current.screen, current.screen, current.time + 1));
					visited[current.screen][current.screen] = true;
				}
			}
			
			// 2번 연산 : 클립보드에 있는 모든 이모티콘을 화면에 붙여넣기
			// 현재 클립보드에 이모티콘이 없다면 붙여넣기x
			// 이모티콘은 1000개까지 만들 수 있고 붙여넣었을 때 2000개를 넘을 수 없음
			if(current.clipboard != 0 && current.screen + current.clipboard <= 2000) {
				if(!visited[current.screen + current.clipboard][current.clipboard]) {
					queue.add(new Emoji(current.screen + current.clipboard, current.clipboard, current.time + 1));
					visited[current.screen + current.clipboard][current.clipboard] = true;
				}
			}
			
			// 3번 연산 : 화면에서 이모티콘 하나 삭제
			// 화면에 이모티콘이 없으면 삭제를 못하고 한개인 경우는 삭제하면 이후 복사를 못함
			if(current.screen > 1) {
				if(!visited[current.screen - 1][current.clipboard]) {
					queue.add(new Emoji(current.screen - 1, current.clipboard, current.time + 1));
					visited[current.screen - 1][current.clipboard] = true;
				}
			}
		}
	}

}
