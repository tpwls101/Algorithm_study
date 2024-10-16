import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * <BJ_1039_교환2>
 * 연산을 수행했을 때 나올 수 있는 수와 cnt를 큐에 저장
 * cnt가 K와 같으면 그 수들 중에서 최댓값을 출력
 * 그 말은 즉, 연산을 K번 수행한 수 중 가장 큰 수라는 말
 * 
 * @author YooSejin
 *
 */

public class Main {

	static int N; // 0으로 시작하면 안됨
	static int K; // 연산을 수행해야 하는 횟수
	static int len;
	static boolean[][] visited;
	static int max = -1; // 연산을 K번 했을 때 나올 수 있는 수의 최댓값
	
	static class Trade {
		int num;
		int cnt; // 연산을 수행한 횟수
		
		Trade(int num, int cnt) {
			this.num = num;
			this.cnt = cnt;
		}
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		
		len = String.valueOf(N).length();
		
		// N의 범위가 100만까지임
		// 그리고 연산 회차별로 방문배열을 초기화 할 수 없어서 몇 번째 연산 중인지 배열 추가
		visited = new boolean[1000001][K+1];
		
		bfs(new Trade(N, 0));
		
		System.out.println(max);
	}
	
	static void bfs(Trade trade) {
		Queue<Trade> queue = new ArrayDeque<>();
		queue.add(trade);
		visited[trade.num][trade.cnt] = true;
		
		while(!queue.isEmpty()) {
			Trade current = queue.poll();
			
			// cnt가 K인 수들 모두 비교해서 최댓값 찾기
			if(current.cnt == K) {
				max = Math.max(max, current.num);
				continue;
			}
			
			// 모든 경우에 대해 swap
			for(int i=0; i<len-1; i++) {
				for(int j=i+1; j<len; j++) {
					int next = swap(current.num, i, j);
					
					// 바꾼 수가 0으로 시작하지 않고, 이미 큐에 넣은 수가 아니라면
					// 같은 회차에서 이미 큐에 넣은 값을 또 넣을 필요는 없음
					if(next != -1 && !visited[next][current.cnt + 1]) {
						queue.add(new Trade(next, current.cnt + 1));
						visited[next][current.cnt + 1] = true;
					}
				}
			}
		}
	}
	
	// num 숫자의 i번째와 j번째를 swap해서 리턴하는 함수
	static int swap(int num, int i, int j) {
		String[] sArr = String.valueOf(num).split("");
		
		String temp = sArr[i];
		sArr[i] = sArr[j];
		sArr[j] = temp;
		
		// 바꾼 수가 0으로 시작하면 안된다
		if(sArr[0].equals("0")) {
			return -1;
		}
		
		StringBuilder sb = new StringBuilder();
		for(int a=0; a<sArr.length; a++) {
			sb.append(sArr[a]);
		}
		
		return Integer.parseInt(sb.toString());
	}

}