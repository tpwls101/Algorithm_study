import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * <BJ_1956_운동>
 * 사이클 최단경로를 찾는 문제 (단방향 그래프이다.)
 * 모든 정점에서 모든 정점까지의 최단경로를 구해 a->b로 가는 최단 경로와 b->a로 가는 최단 경로를 더하는 방식으로 사이클 최단 경로를 구한다.
 * 따라서 일단 플로이드워셜 알고리즘 적용.
 * 
 * 1. 2차원 배열을 INF로 초기화
 * 2. 배열에 a->b로 가는 거리 저장 (단방향 그래프이므로 대칭x)
 * 3. 3중 for문으로 최단 거리 갱신
 * 4. a->b로 가는 최단 거리와 b->a로 가는 최단 거리를 더해 사이클의 최단 거리 구하기
 * 		(주의할 점은 경로를 찾는 것이 불가능할 경우에는 -1 출력. 따라서 사이클 최단 거리가 INF보다 크면 경로가 없다는 것이므로 -1 출력)
 * 
 * @author YooSejin
 *
 */

public class Main {
	
//	static final int INF = 20001;
	
	public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        int V = Integer.parseInt(st.nextToken());
        int E = Integer.parseInt(st.nextToken());
		
        long[][] arr = new long[V+1][V+1];
		
        // 1. 배열 초기화
        for(int i=0; i<=V; i++) {
//        	Arrays.fill(arr[i], INF); // a->b로 가는 거리가 존재하지 않을 때 INF값을 가짐. 그리고 i부터 k까지의 거리와 k부터 j까지의 거리의 합보다 무조건 큰 값이어야 함. 그래야 최대로 거리가 존재할 때에도 최소값으로 arr[i][j]를 저장할 수 있음.
        	Arrays.fill(arr[i], Integer.MAX_VALUE);
        	arr[i][i] = 0;
        }
        
        // 2. 주어진 거리 저장
        for(int i=0; i<E; i++) {
        	st = new StringTokenizer(br.readLine());
        	int a = Integer.parseInt(st.nextToken());
        	int b = Integer.parseInt(st.nextToken());
        	int c = Integer.parseInt(st.nextToken());
        	arr[a][b] = c;
        }
        
        // 3. 모든 정점에서 모든 정점으로 가는 최단 거리 갱신
        for(int k=1; k<=V; k++) { // 중간점
        	for(int i=1; i<=V; i++) { // 행
        		for(int j=1; j<=V; j++) { // 열
        			arr[i][j] = Math.min(arr[i][j], arr[i][k] + arr[k][j]);
        		}
        	}
        }
        
        // 4. 사이클 계산 : a->b로 가는 최단 거리 + b->a로 가는 최단 거리
        long answer = Integer.MAX_VALUE;
        for(int i=1; i<=V; i++) {
        	for(int j=1; j<=V; j++) {
        		if(i == j) continue;
//        		if(arr[i][j] != INF && arr[j][i] != INF) { // a와 b 사이에 둘 다 경로가 있어야만 사이클 가능
        		if(arr[i][j] != Integer.MAX_VALUE && arr[j][i] != Integer.MAX_VALUE) {
        			answer = Math.min(answer, arr[i][j] + arr[j][i]);
        		}
        	}
        }
        
//        System.out.println(answer == INF ? -1 : answer);
        System.out.println(answer == Integer.MAX_VALUE ? -1 : answer);
	}

}
