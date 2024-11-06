import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;

public class Main {
	
	static int N; // 영단어 개수
	static int M; // 외울 단어의 길이 기준
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
		
        Map<String, Integer> map = new HashMap<>();
        for(int i=0; i<N; i++) {
        	String word = br.readLine();
        	if(word.length() >= M) {
        		map.put(word, map.getOrDefault(word, 0) + 1);
        	}
        }
		
        List<String> list = new ArrayList<>(map.keySet());
        
        Collections.sort(list, new Comparator<String>() {
        	@Override
        	public int compare(String o1, String o2) {
        		if(map.get(o2) == map.get(o1)) {
        			if(o2.length() == o1.length()) {
        				return o1.compareTo(o2); // 알파벳 순 (오름차순)
        			}
        			return o2.length() - o1.length(); // 단어의 길이 순 (내림차순)
        		}
        		return map.get(o2) - map.get(o1); // 자주 나오는 순 (내림차순)
        	}
        });
        
        StringBuilder sb = new StringBuilder();
        for(String s : list) {
        	sb.append(s + "\n");
        }
        
        System.out.println(sb);
	}

}