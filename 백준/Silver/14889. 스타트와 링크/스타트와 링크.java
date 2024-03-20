import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	
	static int N; // 총 축구 차는 인원
	static int[][] S; // 양방향 행렬
	static boolean[] selected; // 조합으로 선택되었는지 여부 (선택되면 팀1, 선택되지 않았으면 팀2)
	static int min = Integer.MAX_VALUE; // 두 팀의 능력치 차의 최소값
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		
		S = new int[N+1][N+1];
		selected = new boolean[N+1];
		
		for(int i=1; i<=N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for(int j=1; j<=N; j++) {
				S[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		// 1. 모든 팀의 경우의 수 구하기 (조합)
		comb(0, 1);
		
		System.out.println(min);
	}
	
	// cnt : 뽑은 한 팀의 팀원 수
	public static void comb(int cnt, int start) {
		if(cnt == N/2) {
			// 2. 팀1과 팀2 각각 팀의 능력치 구하기
			getPoint();
			return;
		}
		
		for(int i=start; i<=N; i++) {
			if(!selected[i]) {
				selected[i] = true; // 조합으로 선택 (팀1)
				comb(cnt+1, i+1);
				selected[i] = false; // 한가지 팀 경우의 수가 끝나면 다시 선택 취소
			}
		}
	}
	
	public static void getPoint() {
		int team1 = 0; // 팀1의 능력치
		int team2 = 0; // 팀2의 능력치
		
		for(int i=1; i<=N; i++) {
			for(int j=i+1; j<=N; j++) {
				
				// 선택된 팀원들의 쌍일 때 -> 팀1의 능력치 추가
				if(selected[i] && selected[j]) {
					team1 += S[i][j] + S[j][i];
				}
				
				// 선택되지 않은 팀원들의 쌍일 때 -> 팀2의 능력치 추가
				else if(!selected[i] && !selected[j]) {
					team2 += S[i][j] + S[j][i];
				}
			}
		}
		
		// 3. 두 팀의 능력치 차 구하기
		int gap = Math.abs(team1 - team2);
		// 두 팀의 능력치 차가 0이라면 이미 최소값이므로 답을 출력하고 강제 종료
		if(gap == 0) {
			System.out.println(gap);
			System.exit(0);
		}
		min = Math.min(gap, min);
	}

}