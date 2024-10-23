import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * <BJ_2805_나무자르기>
 * 이분탐색 사용!!!
 * sum은 long 타입 주의
 * 
 * @author YooSejin
 *
 */

public class Main {
	
	static int N; // 나무의 수
	static int M; // 상근이가 집으로 가져가려고 하는 나무의 길이
	static int[] tree; // 나무의 높이를 저장할 배열
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		tree = new int[N];
		
		st = new StringTokenizer(br.readLine());
		for(int i=0; i<N; i++) {
			tree[i] = Integer.parseInt(st.nextToken());
		}
		
		Arrays.sort(tree);
		
		int min = 0;
		int max = tree[tree.length - 1]; // 제일 높은 나무의 길이
		int mid = 0;
		
		while(min <= max) { // left <= right 는 안되는 이유는...? ㅜㅜ
			mid = (min + max) / 2;
			
			// 절단기의 높이(H)를 mid라고 가정하고 얻는 나무의 길이 합 구하기
			long sum = 0; // 최악의 경우, 5억짜리 나무를 100만개 더해야 하므로 long 타입 사용하자!
			for(int i=0; i<N; i++) {
				if(tree[i] - mid > 0) {
					sum += tree[i] - mid;
				}
			}
			
			if(sum >= M) {
				min = mid + 1;
			} else {
				max = mid - 1;
			}
		}
		
		System.out.println(max);
	}

}