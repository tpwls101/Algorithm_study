import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * <BJ_1027_고층건물>
 * 기울기를 이용하는 문제!! -> 처음 접하는 유형으로 기울기로 접근하는 방식을 바로 생각하지 못했다.
 * i번째 건물을 기준으로 높이가 더 작다고 못보는 것이 아님
 * 왼쪽/오른쪽으로 확인하면서 못보는 건물이 있어도 그 이후에 볼 수 있는 건물이 있을 수 있으니 break 걸면 안됨!
 * 처음에 높이로 접근했었는데 그게 아니라 기울기(y/x -> 소수점까지 계산할 것)로 접근해야 한다.
 * 왼쪽 방향을 확인할 때 -> 기울기가 더 작은 경우에만 빌딩을 볼 수 있음 (기울기가 min보다 큰 경우 두 고층 빌딩 사이에 솟아있는 건물이 끼게 됨)
 * 오른쪽 방향을 확인할 때 -> 기울기가 더 큰 경우에만 빌딩을 볼 수 있음 (기울기가 max보다 작은 경우 두 고층 빌딩 사이에 솟아있는 건물이 끼게 됨)
 * 
 * @author YooSejin
 *
 */

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;
        
        int N = Integer.parseInt(br.readLine()); // 빌딩의 수
        
        int[] building = new int[N];
        
        st = new StringTokenizer(br.readLine());
        for(int i=0; i<N; i++) {
        	building[i] = Integer.parseInt(st.nextToken());
        }
        
        int answer = 0;
        for(int i=0; i<N; i++) {
        	int cnt = 0;
        	
        	// i번째 빌딩의 왼쪽 확인
        	double min = Integer.MAX_VALUE;
        	for(int j=i-1; j>=0; j--) {
        		int x = j - i;
        		int y = building[j] - building[i];
        		double a = (double)y / x; // 기울기
        		
        		// 기울기가 더 작은 빌딩만 볼 수 있음
        		if(a < min) {
        			min = Math.min(min, a);
        			cnt++;
        		}
        	}
        	
        	// i번째 빌딩의 오른쪽 확인
        	double max = Integer.MIN_VALUE;
        	for(int j=i+1; j<N; j++) {
        		int x = j - i;
        		int y = building[j] - building[i];
        		double a = (double)y / x; // 기울기
        		
        		// 기울기가 더 큰 빌딩만 볼 수 있음
        		if(a > max) {
        			max = Math.max(max, a);
        			cnt++;
        		}
        	}
        	
        	answer = Math.max(answer, cnt);
        }
		
		System.out.println(answer);
	}

}
