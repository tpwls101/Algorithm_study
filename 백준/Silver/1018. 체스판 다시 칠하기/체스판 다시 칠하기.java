import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;
import java.util.StringTokenizer;

/**
 * <BJ_1018_체스판다시칠하기>
 * 
 * Tip) W와 B로 생각하지 말고 true/false로 생각하면 더 쉽다!
 * 		W : true
 * 		B : false
 * 
 * 첫번째 색을 고정으로 했을 때 count 했으므로 반대의 경우도 고려해야 한다!!!
 * 따라서, 64-count 와 최소값을 1차로 비교해줘야 한다.
 * 
 * <실수한 부분>
 * for(int a=i; a<i+8; a++) { }
 * a와 b는 i, j부터 시작해야 되는데 자꾸 0부터 8까지 돌리니  안됐던 것;;ㅜㅜ
 * 어이없다 실수하지 말자ㅜㅜ
 * 
 * @author YooSejin
 *
 */
public class Main {
	
	static int N; // 행
	static int M; // 열
	static boolean[][] arr; // 체스판 (흑과 백이니 반대로 바꾸기 쉽게 boolean 자료형을 사용한다)
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		Scanner sc = new Scanner(System.in);
		
		N = sc.nextInt();
		M = sc.nextInt();
		
		arr = new boolean[N][M];
		
		for(int i=0; i<N; i++) {
			String str = sc.next();
			for(int j=0; j<M; j++) {
				if(str.charAt(j) == 'W') {
					arr[i][j] = true;
				} else {
					arr[i][j] = false;
				}
			}
		}
		
		int answer = Integer.MAX_VALUE;
		
		for(int i=0; i<=N-8; i++) {
			for(int j=0; j<=M-8; j++) {
				
				int count = 0;
				boolean color = arr[i][j]; // 첫 번째 칸의 색을 기준으로 사용
				
				for(int a=i; a<i+8; a++) { // 실수한 부분!
					for(int b=j; b<j+8; b++) { // 실수한 부분!
						if(arr[a][b] != color) {
							count++;
						}
						color = !color; // 다음 비교 대상 색을 꼭 바꿔줄 것!!
					}
					color = !color; // 다음 행을 비교할 때는 그 전과 색이 같으니 까먹지 말 것!!
				}
				
				count = Math.min(count, 64-count); // 첫 번째를 기준으로 count 했으니 반대의 경우도 고려해서 최소값을 비교해줘야 함
				answer = Math.min(answer, count);
			}
		}
		
		System.out.println(answer);
	}

}