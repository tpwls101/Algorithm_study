import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * <BJ_22251_빌런호석>
 * 미리 0~9에서 0~9로 바꾸는데 필요한 반전 개수를 저장해둬야겠다는 생각까지는 했음.
 * 다만 어떻게 반전 최대 개수를 확인하면서 가능한 수를 파악할 것인가를 고민했는데 로직이 너무 복잡해지는 것 같았음.
 * 단순하게 생각하면 K=4라고 할 때, 현재 층수를 디스플레이로 나타내고, 그러면 0501 이런식으로 층수가 나오게 됨.
 * 이를 1층부터 N층까지로 만들 수 있으므로 목표 층수를 디스플레이로 나타내고, 그러면 0001로 표현할 수 있음.
 * 그리고 for문을 돌려서 각 자릿수마다 0->0, 5->0, 0->0, 1->1 로 바꾸는데 필요한 반전 개수를 카운트함
 * 그 합이 최대 반전 개수인 P를 넘으면 1층으로 바꿀 수 없음.
 * 합이 P를 초과하지 않으면 해당 층수로 디스플레이를 바꿀 수 있으므로 카운트.
 * 
 * 항상 세그먼트 문제가 나오면 헷갈려하는데 차근차근 로직을 생각하는게 중요하다.
 * 막상 풀면 생각보다 어렵지 않다!
 * 
 * @author YooSejin
 *
 */

public class Main {
	
	static int N; // 1~N층
	static int K; // K 자리 수
	static int P; // 1~최대 P개 반전 가능
	static int X; // 현재 층
	static int[] nowFloor; // 현재 층 수를 디스플레이 방식으로 표현한 배열
	static int answer = 0; // LED로 만들 수 있는 경우의 수
	
	static int[][] reverse = { { 0, 4, 3, 3, 4, 3, 2, 3, 1, 2 }, // 0 -> 0~9로 바꾸는데 필요한 개수
							   { 4, 0, 5, 3, 2, 5, 6, 1, 5, 4 }, // 1 -> 0~9로 바꾸는데 필요한 개수
							   { 3, 5, 0, 2, 5, 4, 3, 4, 2, 3 }, // 2
							   { 3, 3, 2, 0, 3, 2, 3, 2, 2, 1 }, // 3
							   { 4, 2, 5, 3, 0, 3, 4, 3, 3, 2 }, // 4
							   { 3, 5, 4, 2, 3, 0, 1, 4, 2, 1 }, // 5
							   { 2, 6, 3, 3, 4, 1, 0, 5, 1, 2 }, // 6
							   { 3, 1, 4, 2, 3, 4, 5, 0, 4, 3 }, // 7
							   { 1, 5, 2, 2, 3, 2, 1, 4, 0, 1 }, // 8
							   { 2, 4, 3, 1, 2, 1, 2, 3, 1, 0 } }; // 9
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        P = Integer.parseInt(st.nextToken());
        X = Integer.parseInt(st.nextToken());
		
        // 현재 층 수를 디스플레이 방식으로 표시
        nowFloor = new int[K];
        nowFloor = numToDigit(X);
        
        // 현재 층의 수를 1부터 N까지의 층수로 만들 수 있는지 확인
        for(int i=1; i<=N; i++) {
        	if(i == X) continue; // 현재 층 수로는 카운트하지 않음. 최소 1개 이상 반전시켜야 함.
        	if(check(i)) {
        		answer++; // i층으로 만들 수 있다면 카운트
        	}
        }
		
		System.out.println(answer);
	}
	
	static int[] numToDigit(int num) {
		int[] digit = new int[K];
		for(int i=K-1; i>=0; i-- ) {
        	digit[i] = num % 10;
        	num /= 10;
        }
		return digit;
	}
	
	static boolean check(int num) {
		int[] target = new int[K]; // 만들려는 목표 층 수
    	target = numToDigit(num);
    	
    	int cnt = 0;
    	for(int i=0; i<K; i++) {
    		cnt += reverse[nowFloor[i]][target[i]];
    		if(cnt > P) return false;
    	}
    	
    	return true;
	}

}
