import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;

public class Main {
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int n = Integer.parseInt(st.nextToken());
		
		Map<String, Integer> map = new HashMap<>();
		for(int i=0; i<n; i++) {
			st = new StringTokenizer(br.readLine());
			String name = st.nextToken();
			
			if(st.nextToken().equals("enter")) {
				map.put(name, 0);
			} else {
				map.remove(name);
			}
		}
		
		List<String> list = new ArrayList<>();
		for(String key : map.keySet()) {
			list.add(key);
		}
		
		Collections.sort(list, Collections.reverseOrder());
		
		for(String s : list) {
			System.out.println(s);
		}
	}

}