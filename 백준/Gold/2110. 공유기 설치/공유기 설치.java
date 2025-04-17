import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * <BJ_2110_공유기설치>
 * 처음에 문제 자체를 이해하기가 어려웠다.
 * 가장 인접한 두 공유기 사이의 거리를 최대로 하라는 말은
 * 즉, 두 공유기 사이에 최소 x만큼의 거리를 가져야하고, 이 때 설치할 수 있는 공유기의 개수를 카운트,
 * 그리고 설치되는 공유기의 개수가 C개를 만족하는 범위 내에서 거리 x를 최대로 가지는 값을 구한다는 말이다.
 * 즉, 우리가 구해야 하는 값인 '두 공유기 사이의 거리'를 기준으로 이분탐색을 이용하면 풀 수 있다!!
 * 안그러면 N의 범위가 20만까지라 시간초과가 날 것이다.
 * 
 * @author YooSejin
 *
 */

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(st.nextToken()); // 집의 개수
		int C = Integer.parseInt(st.nextToken()); // 공유기의 개수
		
		int[] arr = new int[N];
		
		for(int i=0; i<N; i++) {
			arr[i] = Integer.parseInt(br.readLine());
		}
		Arrays.sort(arr);
		
		// 구하고자 하는 것 : 가장 인접한 두 공유기 사이의 거리(즉, 최소한 거리가 x이상이게끔 공유기를 설치한다.)
		int left = 1;
		int right = 1_000_000_000;
		int mid;
		
		while(left <= right) {
			mid = (left + right) / 2;
			
			int cnt = 1; // 두 공유기 사이의 거리가 mid일 때 설치할 수 있는 공유기의 개수
			int next = arr[0] + mid;
			
			for(int i=1; i<N; i++) {
				if(next <= arr[i]) {
					cnt++;
					next = arr[i] + mid;
				} else {
					continue;
				}
			}
			
			if(cnt >= C) {
				left = mid + 1;
			} else {
				right = mid - 1;
			}
		}
		
		System.out.println(right);
	}

}
