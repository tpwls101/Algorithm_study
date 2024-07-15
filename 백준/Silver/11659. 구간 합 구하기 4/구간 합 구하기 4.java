import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

/**
 * <BJ_11659_구간 합 구하기 4> - 누적합 문제
 * N개의 수를 저장할 배열 array를 만들어 값을 입력받는다.
 * 해당 인덱스 번호까지의 합을 저장할 배열 sumArr를 만들어 각 인덱스 번호에 값을 넣는다.
 * 5 4 3 2 1 이 입력값으로 주어지면
 * sumArr에 5 7 12 14 15 를  넣는다.
 * -> 새로운 sumArr배열을 사용하는 방법도 있지만 기존의 array 배열에 값을 갱신하는 방법이 더 간단!
 * 구간의 합은 차를 이용하여 구해준다.
 * 
 * 
 * 
 * @author 유세진
 *
 */

public class Main {

	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(st.nextToken()); // 수의 개수
		int M = Integer.parseInt(st.nextToken()); // 합을 구해야 하는 횟수
		
		int[] array = new int[N+1]; // N개의 수를 저장할 배열 (크기 : N+1 -> 0번째 인덱스에 0을 저장하기 위해)
		st = new StringTokenizer(br.readLine());
		for(int i=1; i<=N; i++) { // 
			array[i] = Integer.parseInt(st.nextToken());
		}
		
		// 방법1
//		int[] sumArr = new int[N+1]; // 해당 인덱스 번호까지의 합을 저장할 배열 (크기 : N+1 -> 0번째 인덱스에 0을 저장하기 위해)
//		for(int i=1; i<=N; i++) {
//			sumArr[i] = sumArr[i-1] + array[i]; // i=1일 때 주의!
//		}
		
		// 방법2
		for(int i=1; i<=N; i++) {
			array[i] = array[i] + array[i-1]; // 새로운 sumArr 배열을 만드는 대신 기존의 array 배열에 갱신
		}
		
		int sum = 0;
		for(int k=0; k<M; k++) {
			st = new StringTokenizer(br.readLine());
			int i = Integer.parseInt(st.nextToken());
			int j = Integer.parseInt(st.nextToken());
			
			// sum = sumArr[j] - sumArr[i-1];
			sum = array[j] - array[i-1];
			bw.write(String.valueOf(sum) + "\n");
		}
		
		bw.flush();
		bw.close();
	}

}
