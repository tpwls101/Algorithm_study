import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

/**
 * <BJ_18870_좌표압축>
 * 배열을 정렬한 후 해당 인덱스가 정답이 된다. (순위라고 생각하면 됨)
 * 단, 중복된 수는 같은 순위를 가짐을 주의할 것!
 * 
 * @author YooSejin
 *
 */

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		
		int N = Integer.parseInt(br.readLine());		
		
		int[] arr = new int[N];
		st = new StringTokenizer(br.readLine());
		for(int i=0; i<N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		
		int[] sortedArr = Arrays.copyOf(arr, N);
		Arrays.sort(sortedArr);
		
		// HashMap에 순위(인덱스) 저장
		Map<Integer, Integer> map = new HashMap<>();
		int index = 0;
		for(int i=0; i<N; i++) {
			// 중복된 수가 이미 있으면 패스
			if(!map.containsKey(sortedArr[i])) {
				map.put(sortedArr[i], index);
				index++;
			}
		}
		
		StringBuilder sb = new StringBuilder();
		for(int i=0; i<N; i++) {
			sb.append(map.get(arr[i]) + " ");
		}
		
		System.out.println(sb);
	}

}