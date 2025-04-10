import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * <BJ_2668_숫자고르기>
 * DFS와 부분집합을 사용해야 하나 싶었는데 부분집합을 사용할 경우 최악의 경우 2^100 시간복잡도를 가지게 된다.
 * 당연히 시간초과 날 거라고 생각해 풀이를 참고했다.
 * 부분집합을 사용하는 것이 아니라 '싸이클'을 찾는 것이 핵심이다.
 * 예를 들어, 1 2 3 4 / 2 3 4 1 이 있다고 치면
 * idx = 1 이라 했을 때, arr[idx]=2 -> arr[arr[idx]] -> 이 값이 idx와 같으면 싸이클
 * 
 * 1. 1부터 N까지 for문 돌려서 최대한 많은 싸이클 찾기
 * 2. 방문 안했다면 다시 dfs로 싸이클 되는지 탐색
 * 
 * @author YooSejin
 *
 */

public class Main {
	
	static int N;
	static int[] arr;
	static boolean[] visited;
	static List<Integer> list;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		N = Integer.parseInt(br.readLine());
		
		arr = new int[N+1];
		visited = new boolean[N+1];
		list = new ArrayList<>();
		
		for(int i=1; i<=N; i++) {
			arr[i] = Integer.parseInt(br.readLine());
		}
		
		// 1~N까지 DFS 돌리기
		for(int i=1; i<=N; i++) {
			if(!visited[i]) {
				List<Integer> tmpList = new ArrayList<>();
				tmpList.add(i);
				visited[i] = true;
				dfs(arr[i], i, tmpList);
			}
		}
		
		System.out.println(list.size());
		Collections.sort(list);
		for(int i : list) {
			System.out.println(i);
		}
	}
	
	// idx : 인덱스번호, target : dfs의 시작점이자 사이클이 종료되는 번호
	static void dfs(int idx, int target, List<Integer> tmpList) {
		if(idx == target) {
			for(int i : tmpList) {
				list.add(i);
			}
			return;
		}
		
		if(!visited[idx]) {
			tmpList.add(idx);
			visited[idx] = true;
			dfs(arr[idx], target, tmpList);
			visited[idx] = false;
		}
	}

}
