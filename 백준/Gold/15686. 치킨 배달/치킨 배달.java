import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

/**
 * <BJ_15686_치킨배달>
 * 가능한 M개의 치킨집을 골라
 * 각 집마다 치킨 거리를 구하고
 * 도시의 치킨 거리를 구한다.
 * M개의 치킨집 경우에 따라 도시의 치킨 거리를 최솟값으로 갱신한다.
 * 
 * @author YooSejin
 *
 */

public class Main {
	
	static int N; // NxN 도시
	static int M; // 치킨집의 최대 개수
	static int[][] arr; // 도시 배열
	static List<Node> list = new ArrayList<>(); // 도시 내의 모든 치킨 집 위치 정보를 저장
	static boolean[] visited;
	static Node[] selected; // 선택된 M개의 치킨집
	static int distance; // 한 집의 치킨 거리
	static int answer = Integer.MAX_VALUE; // 도시의 치킨 거리 최솟값
	
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
		M = Integer.parseInt(st.nextToken());
		
		arr = new int[N+1][N+1];
		selected = new Node[M];
		
		// 입력 값 받기
		for(int i=1; i<=N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=1; j<=N; j++) {
				arr[i][j] = Integer.parseInt(st.nextToken());
				
				// 치킨집의 위치 정보를 리스트에 추가
				if(arr[i][j] == 2) {
					list.add(new Node(i, j));
				}
			}
		}
		
		visited = new boolean[list.size()];
		
		// 치킨집 M개 선택
		comb(0, 0);
		
		System.out.println(answer);
	}
	
	// start : 시작 인덱스
	static void comb(int start, int cnt) {
		// 치킨집을 M개 골랐으면 도시의 치킨 거리 구하기
		if(cnt == M) {
			int cityChicken = getCityChicken();
			answer = Math.min(cityChicken, answer);
			return;
		}
		
		for(int i=start; i<list.size(); i++) {
			if(visited[i]) continue;
			selected[cnt] = list.get(i);
			visited[i] = true;
			comb(i+1, cnt+1);
			visited[i] = false;
		}
	}
	
	// 도시의 치킨 거리를 구하는 함수
	static int getCityChicken() {
		int sum = 0; // 도시의 치킨 거리
		
		for(int i=0; i<=N; i++) {
			for(int j=0; j<=N; j++) {
				// 한 집의 치킨 거리 구하기 (가장 가까운 치킨집과의 거리)
				if(arr[i][j] == 1) {
					distance = Integer.MAX_VALUE; // 초기화
					
					for(int k=0; k<M; k++) {
						int temp = Math.abs(i - selected[k].x) + Math.abs(j - selected[k].y);
						distance = Math.min(distance, temp);
					}
					
					sum += distance;
				}
			}
		}
		
		return sum;
	}

}