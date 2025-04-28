import java.util.Scanner;

/**
 * <BJ_15650_N과 M(2)>
 * nCm 구하기 : N까지의 자연수 중에서 중복 없이 M개 고르기
 * comb 함수를 호출 할 때 매개변수로 현재까지 뽑은 조합 원소의 개수 count와
 * 탐색을 시작할 주사위 눈의 수 start를 전달한다.
 * count가 2이면 재귀를 빠져나오고 아니면 for문을 start부터 시작하여 numbers에 값을 넣어주고 다시 comb를 호출한다.
 * 
 * @author 유세진
 *
 */

public class Main {

	static int N, M;
	static int[] numbers;
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		M = sc.nextInt();
		
		numbers = new int[M]; // 가능한 조합의 경우가 저장될 배열
		
		int count = 0; // 현재까지 뽑은 조합 원소의 개수
		
		comb(count, 1);
		
	}
	
	// 현재까지 뽑은 조합 원소의 개수 count와
	// 탐색을 시작할 주사위 눈의 수 start를 매개변수로 전달
	public static void comb(int count, int start) {
		if(count == M) {
			for(int i=0; i<M; i++) {
				System.out.print(numbers[i] + " ");
			}
			System.out.println();
		} else {
			for(int i=start; i<=N; i++) {
				numbers[count] = i;
				comb(count+1, i+1);
			}
		}
	}

}
