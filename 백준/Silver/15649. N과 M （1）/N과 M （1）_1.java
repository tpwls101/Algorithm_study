import java.util.Scanner;

/**
 * <백준_15649_N과 M(1)>
 * nPm 구하기 : 재귀를 통한 순열 구현
 * 
 * 순열을 저장할 배열 numbers와 숫자가 사용되었는지 확인하는 배열 isSelected를 생성한다.
 * 재귀함수를 사용하여 count가 종료조건을 만나기 전까지 numbers에 for문을 통해 사용될 숫자를 저장하고
 * 다음 재귀함수를 호출하고 돌아왔을 때 isSelected를 false로 바꿔준다.
 * 
 * @author 유세진
 *
 */

public class Main {

	public static int N; // nPm
	public static int M; // mPm
	public static int[] numbers; // 순열을 저장할 배열
	public static boolean[] isSelected; // 인덱스에 해당하는 숫자가 사용 중인지 저장하는 배열

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		M = sc.nextInt();

		numbers = new int[M];
		isSelected = new boolean[N];

		int count = 0; // 현재까지 뽑은 순열 수의 개수

		perm(count);
		
		sc.close();
	}

	public static void perm(int count) {
		// 종료조건(기저조건)
		if (count == M) {
			for (int i = 0; i < M; i++) {
				System.out.print(numbers[i] + " ");
			}
			System.out.println();
		} else {
			for (int i = 0; i < N; i++) {
				if (isSelected[i] == true)
					continue;
				numbers[count] = i + 1;
				isSelected[i] = true;
				perm(count + 1);
				isSelected[i] = false;
			}
		}

	}

}
