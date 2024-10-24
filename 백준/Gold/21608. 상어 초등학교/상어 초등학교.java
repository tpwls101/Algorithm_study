import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.StringTokenizer;

/**
 * <BJ_21608_상어초등학교>
 * 일단 내가 구현해보았다.
 * 자료구조나 정렬을 생각하지 못해 완전 빡구현 했는데
 * 테케만 맞고 틀렸다.
 * 
 * 문제를 효율적으로 풀기 위해서는 먼저 자료구조를 이용한다.
 * HashMap에 각 학생의 번호(key)와 학생이 좋아하는 학생들의 번호(value)를 저장한다.
 * - key : 학생번호 (Integer)
 * - value : 좋아하는 학생들의 번호 (Set<Integer>) -> 좋아하는 학생 번호를 찾을 때 set.contains(num)로 쉽게 찾기 위해!
 * 
 * 두 번째로는 자리를 정할 때 우선순위대로 정렬을 이용해 구한다.
 * 각 칸(자리)마다 {좋아하는 학생 수, 빈칸 수, 행, 열} 정보를 구해 list에 저장하면
 * 이를 정렬하여 자리를 정할 수 있다.
 * 
 * @author YooSejin
 *
 */

public class Main {
	
	static int N; // NxN 교실
	static Map<Integer, Set<Integer>> map; // 학생 번호와 각 학생이 좋아하는 학생들의 번호를 저장
	static int[][] arr; // 학생들의 자리를 저장할 배열
	
	static int[] dx = { 0, 1, 0, -1 };
	static int[] dy = { 1, 0, -1, 0 };
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		
		N = Integer.parseInt(br.readLine());
		
		arr = new int[N][N];
		map = new HashMap<>();
		
		for(int i=0; i<N*N; i++) {
			st = new StringTokenizer(br.readLine());
			int studentNum = Integer.parseInt(st.nextToken());
			
			Set<Integer> set = new HashSet<>();
			for(int j=0; j<4; j++) {
				set.add(Integer.parseInt(st.nextToken()));
			}
			
			map.put(studentNum, set);
			
			// 정해진 순서대로 학생의 자리를 정한다
			decideSeat(studentNum);
		}
		
		// 학생의 만족도 구하기
		int sum = 0;
		for(int i=0; i<N; i++) {
			for(int j=0; j<N; j++) {
				int cnt = getLikeStudentCnt(arr[i][j], i, j);
				
				if(cnt == 1) sum += 1;
				if(cnt == 2) sum += 10;
				if(cnt == 3) sum += 100;
				if(cnt == 4) sum += 1000;
			}
		}
		
		System.out.println(sum);
	}
	
	// 우선순위 1. 인접한 칸에 좋아하는 학생이 가장 많아야 함
	// 우선순위 2. 인접한 빈 칸이 가장 많아야 함
	// 우선선위 3. 행 번호가 가장 작은 칸, 열 번호가 가장 작은 칸
	static void decideSeat(int studentNum) {
		
		List<Integer[]> list = new ArrayList<>();
		
		// 각 칸(자리)마다 좋아하는 학생 수, 빈 칸 수, 행 번호, 열 번호를 구해 저장하고 정렬한다
		for(int i=0; i<N; i++) {
			for(int j=0; j<N; j++) {
				// 자리가 비어있는 칸만 확인한다 (앉을 수 있는 자리)
				if(arr[i][j] == 0) {
					int likeStudentCnt = getLikeStudentCnt(studentNum, i, j); // 좋아하는 학생 수
					int emptyCnt = getEmptyCnt(i, j); // 빈 칸 수
					
					list.add(new Integer[] {likeStudentCnt, emptyCnt, i, j});
				}
			}
		}
		
		// 리스트를 우선순위대로 정렬
		Collections.sort(list, new Comparator<Integer[]>() {
			@Override
			public int compare(Integer[] o1, Integer[] o2) {
				if(o1[0] == o2[0]) {
					if(o1[1] == o2[1]) {
						if(o1[2] == o2[2]) {
							return o1[3] - o2[3]; // 열은 오름차순
						}
						return o1[2] - o2[2]; // 행은 오름차순
					}
					return o2[1] - o1[1]; // 빈 칸 수는 내림차순
				}
				return o2[0] - o1[0]; // 좋아하는 학생 수는 내림차순
			}
		});
		
		int x = list.get(0)[2];
		int y = list.get(0)[3];
		arr[x][y] = studentNum;
	}
	
	static int getLikeStudentCnt(int studentNum, int x, int y) {
		int cnt = 0;
		
		for(int i=0; i<4; i++) {
			int nx = x + dx[i];
			int ny = y + dy[i];
			
			if(nx >= 0 && nx < N && ny >= 0 && ny < N) {
				if(map.get(studentNum).contains(arr[nx][ny])) {
					cnt++;
				}
			}
		}
		return cnt;
	}
	
	static int getEmptyCnt(int x, int y) {
		int cnt = 0;
		
		for(int i=0; i<4; i++) {
			int nx = x + dx[i];
			int ny = y + dy[i];
			
			if(nx >= 0 && nx < N && ny >= 0 && ny < N) {
				if(arr[nx][ny] == 0) {
					cnt++;
				}
			}
		}
		return cnt;
	}

}