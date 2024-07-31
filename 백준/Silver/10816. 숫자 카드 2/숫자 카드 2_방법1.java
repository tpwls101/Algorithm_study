import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

/**
 * <BJ_10816_숫자카드2>
 * 
 * <방법1>
 * HashMap을 사용해서 풀었는데 시간초과가 났다!!
 * Why?
 * 처음에는 이분탐색보다 Hash를 사용하는 것이 효율적이므로 당연히 통과할 것이라 생각했는데 당황했다.
 * 그래서 출력을 sysout 대신 StringBuilder로 바꿔봤더니 통과했다...
 * sysout이 M만큼 출력되고 M의 최댓값은 50만이기 때문에 시간이 상당히 많이 걸리나보다.
 * 그냥 시간초과를 염두에 두는 문제는 애초에 StringBuilder를 써버리자...!
 * 
 * <방법2>
 * 이분탐색으로 접근!
 * 동일한 숫자가 중복해서 있을 수 있으므로 lower bound와 upper bound를 찾자!
 * 어려웠던 점 : while문의 조건과 전에 풀었던 이분탐색 문제에서는 right=N-1로 지정했는데 여기서는 상한값이므로 N으로 지정한 것이 생각해내기 어려웠다.
 * 
 * @author YooSejin
 *
 */

public class Main {
	
	static int N;
	static int M;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		
		Map<Integer, Integer> map = new HashMap<>();
		StringTokenizer st = new StringTokenizer(br.readLine());
		for(int i=0; i<N; i++) {
			int num = Integer.parseInt(st.nextToken());
			map.put(num, map.getOrDefault(num, 0) + 1);
		}
		
		M = Integer.parseInt(br.readLine());
		st = new StringTokenizer(br.readLine());
		StringBuilder sb = new StringBuilder();
		for(int i=0; i<M; i++) {
			int num = Integer.parseInt(st.nextToken());
			if(map.get(num) != null) {
				sb.append(map.get(num) + " ");
			} else {
				sb.append("0 ");
			}
		}
		
		System.out.println(sb);
	}

}
