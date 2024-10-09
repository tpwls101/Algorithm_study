import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * <BJ_13335_트럭>
 * bridge 큐와 truck 큐 -> 2개를 사용한다.
 * 이 때 항상 다리 큐에 2개가 채워져있는 것이 포인트! (다리 위에 올려져 있는 트럭의 무게를 나타냄)
 * 
 * @author YooSejin
 *
 */

public class Main {
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int n = Integer.parseInt(st.nextToken()); // 트럭의 개수
		int w = Integer.parseInt(st.nextToken()); // 다리의 길이
		int L = Integer.parseInt(st.nextToken()); // 다리의 최대하중
		
		Queue<Integer> truck = new ArrayDeque<>(); // 트럭의 무게를 저장
		st = new StringTokenizer(br.readLine());
		for(int i=0; i<n; i++) {
			truck.add(Integer.parseInt(st.nextToken()));
		}
		
		Queue<Integer> bridge = new ArrayDeque<>(); // 현재 다리 위에 있는 트럭의 무게 저장
		for(int i=0; i<w; i++) {
			bridge.add(0);
		}
		
		int count = 0; // 모든 트럭이 다리를 건너는 최단시간
		int weightSum = 0; // 현재 다리 위에 있는 트럭들의 무게의 합
		
		while(!bridge.isEmpty()) {
			count++;
			weightSum -= bridge.poll(); // 다리에 올라와있는 트럭이 한대씩 다리에서 내려옴
			
			// 남아있는 트럭이 있으면 트럭 출발
			if(!truck.isEmpty()) {
				// 트럭 무게의 합이 무게 하중을 넘지 않으면 트럭을 출발시킴
				if(weightSum + truck.peek() <= L) {
					weightSum += truck.peek();
					bridge.add(truck.poll());
				}
				// 트럭을 출발시킬 수 없으면
				else {
					bridge.add(0); // bridge 큐에 0을 넣어준다
				}
			}
		}
		
		System.out.println(count);
	}

}