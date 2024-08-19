import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Set;

/**
 * <BJ_5568_카드놓기>
 * 순열
 * 숫자 카드에 같은 수가 있어 만든 정수가 중복될 수 있으니 set 자료구조를 활용하자!
 * 
 * @author YooSejin
 *
 */

public class Main {

	static int n;
	static int k;
	static int[] numbers; // n개의 숫자 카드를 저장할 배열
	static boolean[] visited;
	static int[] selectedNum; // 선택된 k개의 숫자 카드를 저장할 배열
	static Set<Integer> set; // 만든 정수의 중복을 피하기 위해
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		n = Integer.parseInt(br.readLine());
		k = Integer.parseInt(br.readLine());
		
		numbers = new int[n];
		visited = new boolean[n];
		selectedNum = new int[k];
		set = new HashSet<>();
		
		for(int i=0; i<n; i++) {
			numbers[i] = Integer.parseInt(br.readLine());
		}
		
		perm(0);
		
		System.out.println(set.size());
	}
	
	static void perm(int cnt) {
		if(cnt == k) {
			String s = "";
			for(int i=0; i<k; i++) {
				s += selectedNum[i];
			}
			set.add(Integer.parseInt(s));
			return;
		}
		
		for(int i=0; i<n; i++) {
			if(visited[i]) continue;
			selectedNum[cnt] = numbers[i];
			visited[i] = true;
			perm(cnt+1);
			visited[i] = false;
		}
	}

}