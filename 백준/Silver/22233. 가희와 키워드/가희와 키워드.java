import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;

public class Main {
	
	static int N; // 메모장에 적은 키워드 개수
	static int M; // 가희가 블로그에 쓴 글의 개수
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
		
        Set<String> set = new HashSet<>();
        for(int i=0; i<N; i++) {
        	String keyword = br.readLine();
        	set.add(keyword);
        }
		
        StringBuilder sb = new StringBuilder();
        for(int i=0; i<M; i++) {
        	String[] s = br.readLine().split(",");
        	for(int j=0; j<s.length; j++) {
        		if(set.contains(s[j])) {
        			set.remove(s[j]);
        		}
        	}
        	sb.append(set.size() + "\n");
        }
        
        System.out.println(sb);
	}

}