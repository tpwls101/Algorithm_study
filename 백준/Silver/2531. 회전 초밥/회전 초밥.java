import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

/**
 * <BJ_2531_회전초밥>
 * 처음엔 회전 벨트라 당황할 수 있지만 나는 값을 저장할 배열의 길이를 늘려주었음
 * 2*N을 사용할까 했지만 공간을 많이 잡아먹을 수 있으니 N+k-1 만큼 크기를 지정해줬음
 * 하지만 N만큼만 사용하고 나머지 연산을 사용해 end로 사용해도 됨!!
 * 
 * @author YooSejin
 *
 */

public class Main {
	
	static int N; // 회전 초밥 벨트에 놓인 접시의 수
	static int d; // 초밥 의 가짓수
	static int k; // 연속해서 먹는 접시 수
	static int c; // 쿠폰 번호
	static int[] belt;
	static Map<Integer, Integer> map;
	static int answer = 0; // 초밥의 최대 가짓수
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		d = Integer.parseInt(st.nextToken());
		k = Integer.parseInt(st.nextToken());
		c = Integer.parseInt(st.nextToken());
		
		belt = new int[N+k-1];
		map = new HashMap<>();
		
		for(int i=0; i<N; i++) {
			belt[i] = Integer.parseInt(br.readLine());
			if(i < k-1) belt[N+i] = belt[i];
		}
		
		for(int i=0; i<k; i++) {
			map.put(belt[i], map.getOrDefault(belt[i], 0) + 1);
		}
		answer = map.size();
		if(!map.containsKey(c)) answer++;
		
		for(int i=0; i<N-1; i++) {
			int out = belt[i];
			if(map.get(out) == 1) {
				map.remove(out);
			} else {
				map.put(out, map.get(out) - 1);
			}
			
			map.put(belt[i+k], map.getOrDefault(belt[i+k], 0) + 1);
			
			int cnt = (map.containsKey(c)) ? map.size() : map.size()+1;
			answer = Math.max(answer, cnt);
		}
		
		System.out.println(answer);
	}

}
