import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * <D4_1238_Contact>
 * 인접행렬을 사용하여 유향그래프를 구현하였고 bfs를 사용하여 탐색했다.
 * 
 * @author 유세진
 *
 */

public class Solution {
	
	static int[][] adjMatrix; // 인접행렬
	static boolean[] visited; // 방문 체크 배열
	static int[] counts; // bfs 너비를 저장할 배열 (인덱스가 해당 정점 번호)
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		for(int test_case = 1; test_case <= 10; test_case++) {
			
			st = new StringTokenizer(br.readLine());
			
			int inputLen = Integer.parseInt(st.nextToken()); // 입력받는 데이터의 길이
			int start = Integer.parseInt(st.nextToken()); // 시작점
			
			st = new StringTokenizer(br.readLine());
			
			adjMatrix = new int[101][101]; // 연락 가능한 인원은 최대 100명
			visited = new boolean[101]; // 방문 체크 배열 (1부터 시작)
			counts = new int[101]; // bfs 너비를 저장할 배열 (인덱스가 해당 정점 번호, 1부터 시작)
			
			// (from, to) 쌍이 입력되므로 정점의 개수는 inputLen/2 개
			for(int i=0; i<inputLen/2; i++) {
				int from = Integer.parseInt(st.nextToken());
				int to = Integer.parseInt(st.nextToken());
				
				adjMatrix[from][to] = 1;
			}
			
			bfs(start);
			
			// 큐 탐색을 끝낸 후 돌아와서 count(너비)의 max값 찾기 -> 가장 마지막에 연락한 정점
			int max = 0; // 너비의 max값
			int maxIndex = 0; // 가장 마지막에 연락된 정점들의 번호
			for(int i=0; i<counts.length; i++) {
				max = Math.max(max, counts[i]); // 너비의 max값 찾기
				if(max == counts[i]) {
					maxIndex = i;
				}
			}
			
			System.out.println("#" + test_case + " " + maxIndex);
		}
		
	}
	
	// start : 시작 정점
	private static void bfs(int start) {
		
		Queue<Integer> queue = new ArrayDeque<>();
		queue.offer(start);
		visited[start] = true;
		counts[start] = 1; // 처음 너비는 1
		
		while(!queue.isEmpty()) {
			int current = queue.poll(); // 현재 정점
			for(int i=1; i<101; i++) {
				if(adjMatrix[current][i] == 1 && !visited[i]) { // 연결되어 있고 방문하지 않았다면
					queue.offer(i); // 큐에 연결된 정점 삽입
					visited[i] = true;
					counts[i] = counts[current] + 1; // i번 정점의 너비 = 현재 정점의 너비 + 1
				}
			}
		}
		
	}

}