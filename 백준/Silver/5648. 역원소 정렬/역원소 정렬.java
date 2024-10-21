import java.util.Arrays;
import java.util.Collections;
import java.util.Scanner;

/**
 * <BJ_5648_역원소정렬>
 * long 타입 주의!!
 * 아니면 런타임에러(InputMismatch)
 * 
 * @author YooSejin
 *
 */

public class Main {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();
		
		long[] arr = new long[N];
		
		for(int i=0; i<N; i++) {
			arr[i] = sc.nextLong();
		}
		
		for(int i=0; i<N; i++) {
			String s = String.valueOf(arr[i]);
			String[] sArr = s.split("");
			Collections.reverse(Arrays.asList(sArr));
			
			String reversed = "";
			for(int j=0; j<sArr.length; j++) {
				reversed += sArr[j];
			}
			arr[i] = Long.parseLong(reversed);
		}
		
		Arrays.sort(arr);
		
		for(int i=0; i<N; i++) {
			System.out.println(arr[i]);
		}
	}

}
