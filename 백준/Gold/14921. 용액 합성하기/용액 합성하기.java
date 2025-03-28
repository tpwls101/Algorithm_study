import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * <BJ_14921_용액합성하기>
 * 투 포인터 문제!!
 * 일단 N의 범위가 10만이므로 이중 for문을 돌리면 안된다.
 * 그러면 어떻게 0에 가장 가까운 값을 찾을 것인가?
 * 오름차순으로 정렬되어 있으므로 양 끝 인덱스를 사용해
 * 두 수의 합이 음수이면 값을 증가시켜야 하므로 start 인덱스를 증가시키고
 * 두 수의 합이 양수이면 값을 감소시켜야 0에 가까워지므로 end 인덱스를 감소시킨다.
 * 그리고 각 합에 대해 절댓값으로 비교해 더 작은 수를 구한다.
 * 왜냐하면 절댓값이 가장 작은 수가 0에 가장 가까운 수이기 때문이다.
 * 주의할 점은 절댓값을 출력하면 안되고, 양수/음수를 표시해 출력해야 한다.
 * 이는 비교만 절댓값으로 하고 값 저장은 합을 그대로 사용하면 된다.
 * 
 * @author YooSejin
 *
 */

public class Main {
	
	static int N; // 용액의 개수
	static int[] arr; // 각 용액의 특성값
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;
        
        N = Integer.parseInt(br.readLine());
        
        arr = new int[N];
		
        st = new StringTokenizer(br.readLine());
        for(int i=0; i<N; i++) {
        	arr[i] = Integer.parseInt(st.nextToken());
        }
        
        int start = 0;
        int end = N-1;
        int min = Integer.MAX_VALUE; // 0에 가장 가까운 특성값
        
        while(true) {
        	if(start == end) break;
        	
        	int sum = arr[start] + arr[end];
        	if(Math.abs(sum) < Math.abs(min)) { // 0과 가장 가까운 수를 찾아야하므로 절댓값으로 비교
        		min = sum; // 단, 출력하는 값은 음수/양수를 표현해야 함
        	}
        	
        	if(sum < 0) start++; // 두 수의 합이 음수이면 start 인덱스를 증가시켜 합 증가시키기
        	else end--; // 두 수의 합이 양수이면 end 인덱스를 감소시켜 합 줄이기
        }
        
        System.out.println(min);
	}

}
