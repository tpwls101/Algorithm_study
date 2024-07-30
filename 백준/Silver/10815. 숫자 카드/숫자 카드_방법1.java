import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * <BJ_10815_숫자카드>
 * NxM(50만x50만)이므로 시간초과에 주의!!
 * 
 * <방법1 : 이분탐색>
 * for문을 효율적으로 사용하는 것이 필수이기에 이분탐색을 이용한다.
 * 이분탐색을 사용하기 위해서는 정렬은 필수이다.
 * 이분탐색의 시간복잡도는 O(logN)이다.
 * Tip) arr[middle] < num -> left = middle + 1
 * 		arr[middle] > num -> right = middle - 1
 * left == middle == right 인 경우도 존재한다.
 * 이 때에도 num을 찾지 못하면 while문의 조건에 부합하지 않으므로 while문을 빠져나와 0을 출력한다.
 * 
 * <방법2 : Hash 자료구조 사용>
 * Hash의 시간복잡도는 O(1)이므로 훨씬 효율적이다.
 * HashMap에 상근이가 가지고 있는 숫자 카드들을 저장하고
 * M개의 카드들을 key로 사용하여 get() 함수로 map에 있는지 확인하면
 * 있는 경우 그 key의 value를 리턴하고, 없으면 null을 리턴한다.
 * 이를 이용해 답을 출력한다.
 * 
 * @author YooSejin
 *
 */

public class Main {
	
	static int N; // 상근이가 가지고 있는 숫자 카드의 개수
	static int M; // 주어지는 카드의 수
	static int[] arr; // 상근이가 가지고 있는 숫자 카드를 저장할 배열
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		N = Integer.parseInt(br.readLine());
		arr = new int[N];
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		for(int i=0; i<N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		Arrays.sort(arr);
		
		M = Integer.parseInt(br.readLine());
		st = new StringTokenizer(br.readLine());
		
		for(int i=0; i<M; i++) {
			int num = Integer.parseInt(st.nextToken());
			BinarySearch(num);
		}
	}
	
	// num을 이분탐색으로 찾는 함수
	static void BinarySearch(int num) {
		int left = 0;
		int right = N-1;
		
		// left == middle == right 인 경우는 가능
		// left < right 인 경우에는 불가능
		while(left <= right) {
			int middle = (left + right) / 2;
			
			if(arr[middle] < num) {
				left = middle + 1;
			} else if(arr[middle] > num) {
				right = middle - 1;
			} else {
				System.out.print("1 ");
				return;
			}
		}
		
		// 찾는 수가 없으면 조건식에 부합하지 않아 while문을 빠져나오고 0 출력
		System.out.print("0 ");
	}

}
