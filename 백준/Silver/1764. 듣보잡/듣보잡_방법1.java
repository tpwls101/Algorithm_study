import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;

/**
 * <BJ_1764_듣보잡>
 * N과 M의 범위가 50만까지인 것에 주의할 것!
 * 이중 for문 돌리면 시간초과!!
 * 따라서 Map 또는 Set 자료구조를 사용해야 한다!
 * 
 * <방법1>
 * HashMap에 getOrDefault() 함수를 사용하여 이름을 put한다.
 * 듣도 또는 보도 못한 사람이면 value가 1이 되고, 듣보잡이면 value가 2가 된다.
 * value가 2인 사람만 뽑아서 list에 넣고 정렬한다.
 * 
 * <방법2>
 * N명의 사람을 HashSet에 add하고
 * M만큼 for문을 돌리면서 이 이름이 set에 포함되어 있다면 듣보잡이므로 list에 추가한다.
 * list를 정렬하여 출력한다.
 * 
 * @author YooSejin
 *
 */

public class Main {
	
	static int N; // 듣도 못한 사람의 수
	static int M; // 보도 못한 사람의 수
	
	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		HashMap<String, Integer> map = new HashMap<>();

		for(int i=0; i<N+M; i++) {
			String name = br.readLine();
			map.put(name, map.getOrDefault(name, 0)+1);
		}
		
		List list = new ArrayList<>();
		for(Map.Entry<String, Integer> entry : map.entrySet()) {
			if(entry.getValue() == 2) {
				list.add(entry.getKey());
			}
		}
		
		Collections.sort(list);
		System.out.println(list.size());
		
		for(int i=0; i<list.size(); i++) {
			System.out.println(list.get(i));
		}

	}

}
