import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

/**
 * <BJ_12891_DNA비밀번호>
 * 문자열 중 고정된 길이의 부분문자열을 뽑는 문제이다.
 * 이 때 문자열 길이의 범위가 100만까지이므로 이중 for문을 사용하면 시간초과가 난다.
 * 따라서 슬라이딩 윈도우를 사용해야 한다.
 * 
 * 주의할 점을 map을 사용할 때 이미 key를 가지고 있는지, key값의 value를 가져올 때 exception 발생하지는 않는지
 * 이런 것들을 주의해야 한다.
 * 
 * @author YooSejin
 *
 */

public class Main {
	
	static int S; // 임의로 만든 DNA 문자열의 길이
	static int P; // 비밀번호로 사용할 부분문자열의 길이
	static String[] arr;
	static int A, C, G, T; // 각 문자가 있어야 할 최소 개수
	static Map<String, Integer> map = new HashMap<>();
	static int answer = 0; // 만들 수 있는 비밀번호의 수
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		S = Integer.parseInt(st.nextToken());
		P = Integer.parseInt(st.nextToken());
		
		arr = new String[S];
		arr = br.readLine().split("");
		
		st = new StringTokenizer(br.readLine());
		A = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		G = Integer.parseInt(st.nextToken());
		T = Integer.parseInt(st.nextToken());
		
		// 이 문제의 경우 map에 들어갈 문자가 A,C,G,T로 정해져 있으므로 아예 map에 key로 넣어준다.
		// 왜냐하면 4개 중 아예 map에 들어가지 않는 문자가 있다면 이후 조건을 검사할 때 map.get()에서 NullPointerException 발생
		map.put("A", 0);
		map.put("C", 0);
		map.put("G", 0);
		map.put("T", 0);
		
		for(int i=0; i<P; i++) {
			map.put(arr[i], map.get(arr[i]) + 1);
		}
		
		// map에 무조건 A,C,G,T 문자열이 key로 있으므로 map.get()에서 에러날 일 없음
		if(map.get("A") >= A && map.get("C") >= C && map.get("G") >= G && map.get("T") >= T) {
			answer++;
		}
		
		for(int i=0; i<S-P; i++) {
			String minus = arr[i]; // 뺄 문자열
			String plus = arr[i+P]; // 더할 문자열
			
			map.put(minus, map.get(minus) - 1); // 무조건 뺼 문자가 하나 이상 있으므로 있는지 없는지 확인할 필요 없음
			map.put(plus, map.get(plus) + 1);
			
			if(map.get("A") >= A && map.get("C") >= C && map.get("G") >= G && map.get("T") >= T) {
				answer++;
			}
		}
		
		System.out.println(answer);
	}

}
