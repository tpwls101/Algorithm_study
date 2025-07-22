import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

/**
 * <BJ_16198_에너지모으기>
 * DFS 문제.
 * 처음과 끝을 제외한 1부터 list.size()-2까지 for문을 돌리면 된다.
 * 주의할 점은 구슬을 제거하며 list의 크기가 바뀌기 때문에 for문에 N-1로 작성하면 안된다.
 * i번째 구슬의 무게를 임시로 저장해두고, 제거한 다음 다시 해당 함수로 리턴했을 때 i번째 위치에 넣어주면 된다.
 * 구슬이 제거되면서 결국 1부터 다시 for문이 돌아가므로 방문처리를 할 필요가 없다.
 * 
 * @author YooSejin
 *
 */

public class Main {
	
	static int N; // 에너지 구슬의 개수
	static List<Integer> list; // 에너지 구슬의 무게 저장
	static int answer = 0;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		
		N = Integer.parseInt(br.readLine());
		
		list = new ArrayList<>();
		st = new StringTokenizer(br.readLine());
		for(int i=0; i<N; i++) {
			list.add(Integer.parseInt(st.nextToken()));
		}
		
		dfs(0);
		
		System.out.println(answer);
	}
	
	static void dfs(int sum) {
		if(list.size() == 2) {
			answer = Math.max(answer, sum);
			return;
		}
		
		for(int i=1; i<list.size()-1; i++) { // 처음과 끝 제외
			int weight = list.get(i); // 재귀 리턴 후 다시 해당 무게의 구슬을 list에 넣어주기 위해 임시 저장
			int energy = list.get(i-1) * list.get(i+1);
			list.remove(i);
			dfs(sum + energy);
			list.add(i, weight); // i번째 위치에 다시 무게가 weigth인 구슬 넣어주기
		}
	}

}
