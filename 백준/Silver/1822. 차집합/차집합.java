import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

public class Main {
	
	static int N; // 집합 A의 원소의 개수
	static int M; // 집합 B의 원소의 개수
	static int[] A; // 집합 A의 원소를 저장할 배열
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());		
		
		A = new int[N];
		
		st = new StringTokenizer(br.readLine());
		for(int i=0; i<N; i++) {
			A[i] = Integer.parseInt(st.nextToken());
		}
		Arrays.sort(A);
		
		Map<Integer, Integer> map = new HashMap<>();
		st = new StringTokenizer(br.readLine());
		for(int i=0; i<M; i++) {
			int num = Integer.parseInt(st.nextToken());
			map.put(num, 0);
		}
		
		StringBuilder sb = new StringBuilder();
		int count = 0;
		for(int i=0; i<N; i++) {
			if(map.get(A[i]) == null) {
				count++;
				sb.append(A[i] + " ");
			}
		}
		
		if(count == 0) {
			System.out.println("0");
		} else {
			System.out.println(count + "\n" + sb);			
		}
	}

}