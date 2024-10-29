import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.StringTokenizer;

/**
 * <BJ_17140_이차원배열과연산> 
 * 1. 매번 arr의 크기를 새로 할당해주는 방식
 *  
 * 행과 열 정렬할 때 i,j 헷갈리지 않게 조심할 것!
 * 나는 가장 길이가 큰 행/열의 크기를 구해 매번 arr 크기를 새로 할당해줬는데
 * 처음부터 arr[100][100]으로 선언하고 시작해도 된다!
 * 
 * @author YooSejin
 *
 */

public class Main {
	
	static int r;
	static int c;
	static int k;
	static int[][] arr;
	static Map<Integer, Integer> map;
	static ArrayList<ArrayList<Integer>> list;
	static int max; // 길이가 가장 큰 리스트의 크기 -> 새로운 배열 크기에 사용
	static int answer = 0; // arr[r][c]의 값이 k가 되기 위한 연산의 최소 시간 구하기
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		r = Integer.parseInt(st.nextToken()) - 1;
		c = Integer.parseInt(st.nextToken()) - 1;
		k = Integer.parseInt(st.nextToken());
		
		arr = new int[3][3];
		
		for(int i=0; i<3; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0; j<3; j++) {
				arr[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		while(true) {
			// 100초가 되었을 때에도 arr[r][c]가 k와 값이 같은지 비교해야 한다! 따라서 if(answer >= 100)보다 먼저!
			// 예제6의 경우 1번 R연산을 하고 나면 arr[3][2] 크기가 되어 arr[2][2]의 값이 k와 같은지 확인할 수 없다 (ArrayIndexOutOfBoundsException)
			if(r < arr.length && c < arr[0].length) {
				if(arr[r][c] == k) { // 만약 100초가 지나서 값이 k와 같아지면 while문 빠져나와 100초 출력
					break;
				}
			}
			
			if(answer >= 100) { // 100초가 지나도 k가 되지 않으면 -1 출력
				answer = -1;
				break;
			}
			
			if(arr.length >= arr[0].length) { // 행의 개수 >= 열의 개수 : R연산
				operationR();
			} else { // 행의 개수 < 열의 개수 : C연산
				operationC();
			}
			answer++;
		}
		
		System.out.println(answer);
	}
	
	static void operationR() {
		// 각 행마다 정렬
		list = new ArrayList<>();
		max = 0;
		for(int i=0; i<arr.length; i++) {
			list.add(new ArrayList<>());
			
			// 1. Map에 저장 <숫자, 나온 횟수>
			map = new HashMap<>();
			for(int j=0; j<arr[i].length; j++) {
				if(arr[i][j] == 0) continue;
				map.put(arr[i][j], map.getOrDefault(arr[i][j], 0) + 1);
			}
			doSort(i); // 정렬해서 i번째 ArrayList에 저장
		}
		
		// 4. 새로운 배열 만들기
		if(max > 100) max = 100;
		arr = new int[arr.length][max];
		for(int i=0; i<arr.length; i++) {
			for(int j=0; j<list.get(i).size(); j++) {
				if(j >= 100) break;
				arr[i][j] = list.get(i).get(j);
			}
		}
	}
	
	static void operationC() {
		// 각 열마다 정렬
		list = new ArrayList<>();
		max = 0;
		for(int i=0; i<arr[0].length; i++) {
			list.add(new ArrayList<>());
			
			// 1. Map에 저장 <숫자, 나온 횟수>
			map = new HashMap<>();
			for(int j=0; j<arr.length; j++) {
				if(arr[j][i] == 0) continue;
				map.put(arr[j][i], map.getOrDefault(arr[j][i], 0) + 1);
			}
			doSort(i); // 정렬해서 i번째 ArrayList에 저장
		}
		
		// 4. 새로운 배열 만들기
		if(max > 100) max = 100;
		arr = new int[max][arr[0].length];
		for(int i=0; i<arr[0].length; i++) {
			for(int j=0; j<list.get(i).size(); j++) {
				if(j >= 100) break;
				arr[j][i] = list.get(i).get(j);
			}
		}
	}
	
	static void doSort(int index) {
		// 2. 배열에 저장 후 정렬 (나온 횟수 오름차순, 숫자 오름차순)
		int[][] temp = new int[map.size()][2];
		int idx = 0;
		for(Entry<Integer, Integer> entry : map.entrySet()) {
			temp[idx][0] = entry.getKey();
			temp[idx][1] = entry.getValue();
			idx++;
		}
		
		Arrays.sort(temp, new Comparator<int[]>() {
			@Override
			public int compare(int[] o1, int[] o2) {
				if(o1[1] == o2[1]) {
					return o1[0] - o2[0];
				}
				return o1[1] - o2[1];
			}
		});
		
		// 3. 정렬한대로 차례대로 list에 저장
		for(int i=0; i<temp.length; i++) {
			list.get(index).add(temp[i][0]);
			list.get(index).add(temp[i][1]);
		}
		
		max = Math.max(max, list.get(index).size());
	}

}
