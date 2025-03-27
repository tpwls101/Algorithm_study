import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	
	static int K; // 이미 가지고 있는 랜선의 개수
	static int N; // 필요한 랜선의 개수
	static int[] len; // 각 랜선의 길이
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        K = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());
		
        len = new int[K];
        
        int max = 0;
        for(int i=0; i<K; i++) {
        	len[i] = Integer.parseInt(br.readLine());
        	max = Math.max(max, len[i]);
        }
        
        long left = 1;
        long right = max;
        long mid;
        
        while(left <= right) {
        	mid = (left + right) / 2;
        	
        	long cnt = 0; // 만들 수 있는 랜선의 개수. 타입 주의. 만약 주어진 랜선은 MAX_VALUE이고 mid가 1이면 이후 cnt를 더할 때 오버플로우 발생.
        	for(int i=0; i<K; i++) {
        		cnt += len[i] / mid;
        	}
        	
        	if(cnt >= N) { // 만들어야 하는 갯수보다 랜선이 더 많이 나오면 랜선 길이 늘리기
        		left = mid + 1;
        	} else { // 만들어야 하는 만큼 못만드면 랜선 길이 줄이기
        		right = mid - 1;
        	}
        }
        
        System.out.println(right);
	}

}
