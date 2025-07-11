import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * <BJ_16926_배열돌리기1>
 * 단순 구현 문제
 * 
 * 최대 300x300개의 arr[i][j]를 R번 회전
 * 300x300x1000 = 90,000,000 -> 시간 ok
 * 
 * 문제에서 제한조건으로 min(N,M)mod2 = 0 라고 주어졌기 때문에
 * 작은 수를 기준으로 나누기 2 한 것 만큼 라인을 회전시키면 된다.
 * 안의 라인 회전시킬 때 변수 주의! -> -j / +j 까먹으면 안됨
 * 
 * @author YooSejin
 *
 */

public class Main {
	
	static int N;
	static int M;
	static int R;
	static int[][] arr;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int R = Integer.parseInt(st.nextToken());
		
        arr = new int[N][M];
        
        for(int i=0; i<N; i++) {
        	st = new StringTokenizer(br.readLine());
        	for(int j=0; j<M; j++) {
        		arr[i][j] = Integer.parseInt(st.nextToken());
        	}
        }
        
        // 한 배열 내에서 돌려야하는 라인의 수
        int count = Math.min(N, M) / 2;
        
        for(int i=0; i<R; i++) { // 배열을 R번 회전
        	
        	for(int j=0; j<count; j++) { // 하나의 배열 당 count개의 라인을 회전
        		int tmp = arr[j][j];
        		
        		for(int k=j; k<M-1-j; k++) { // 위
        			arr[j][k] = arr[j][k+1];
        		}
        		
        		for(int k=j; k<N-1-j; k++) { // 오른쪽
        			arr[k][M-1-j] = arr[k+1][M-1-j];
        		}
        		
        		for(int k=M-1-j; k>j; k--) { // 아래
        			arr[N-1-j][k] = arr[N-1-j][k-1];
        		}
        		
        		for(int k=N-1-j; k>j+1; k--) { // 왼쪽
        			arr[k][j] = arr[k-1][j];
        		}
        		
        		arr[j+1][j] = tmp;
        	}
        	
        }
		
		StringBuilder sb = new StringBuilder();
		for(int i=0; i<N; i++) {
			for(int j=0; j<M; j++) {
				sb.append(arr[i][j] + " ");
			}
			sb.append("\n");
		}
		
		System.out.println(sb);
	}
}
