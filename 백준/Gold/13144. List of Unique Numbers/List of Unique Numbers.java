import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;

/**
 * <BJ_13144_ListOfUniqueNumbers>
 * N의 범위가 10만까지이기 때문에 이중 for문 돌리면 O(N^2)으로 시간초과 난다.
 * 따라서 투 포인터를 이용해야 한다. -> 아직 바로 떠오르지 않았음 ㅜㅜ
 * end를 증가시켜 나가고 그 와중에 set을 통해 겹치는 수가 있다면 start를 증가시켜주는 식으로 풀었다.
 * 그리고 end를 증가시킬 때에만 새로운 수가 추가되어 새로운 경우의 수가 등장한다.
 * 또한 새로운 arr[end]를 추가하면 그 수만 추가하는 경우, 그 이전 수와 arr[end], 전전 수와 arr[end] 이런식으로 새로운 경우의 수가 생겨나므로
 * (end-start+1) 즉, start부터 end까지의 길이만큼 경우의 수가 추가된다.
 * 
 * 주의할 점은 최악의 경우 -> 1~10만까지의 수열이므로 1~10만까지의 합이 답이 된다.
 * 1~N까지의 합은 Nx(N+1)/2 이므로 약 5억
 * 즉, 정답은 long 타입을 사용해야 함에 주의하자!!!!
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
		
		int start = 0;
		int end = 0;
		long answer = 0;
		
		Set<Integer> set = new HashSet<>();
		
		while(start < N) {
			if(end < N && !set.contains(arr[end])) {
				set.add(arr[end]);
				answer += (end - start + 1);
				end++;
			} else {
				set.remove(arr[start]);
				start++;
			}
		}
		
		System.out.println(answer);
	}

}
