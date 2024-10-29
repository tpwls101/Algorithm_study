import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

/**
 * <BJ_2075_N번째큰수>
 * 1. list에 N*N개의 수를 담아 정렬한 후 N번째 큰 수를 구하는 방법 (1976ms)
 * 
 * @author YooSejin
 *
 */

public class Main {
	
	static int N;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		
		N = Integer.parseInt(br.readLine());
		
		List<Integer> list = new ArrayList<>();
		
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0; j<N; j++) {
				list.add(Integer.parseInt(st.nextToken()));
			}
		}
		
		Collections.sort(list);
		
		System.out.println(list.get(N*N - N));
	}

}
