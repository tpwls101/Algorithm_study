import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * <BJ_14890_경사로>
 * 올라가는 경사로와 내려가는 경사로를 구분하지 않고 풀었을 때 길이 L만큼 경사로가 있는지 확인하기 어려웠다.
 * 특히, 내려가는 경사로의 경우.
 * 올라가는 경사로와 내려가는 경사로를 구분지어 주자.
 * 
 * 안되는 경우를 찾아 구현하고 바로 return false 해주면 더 빠르게 할 수 있다.
 * 구현하기 꽤 복잡한 문제였다ㅜㅜ
 * 
 * @author YooSejin
 *
 */
public class Main {
	
	static int N; // NxN 지도
	static int L; // 경사로의 길이
	static int[][] arr;
	static int answer = 0; // 지나갈 수 있는 길의 개수
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		L = Integer.parseInt(st.nextToken());
		
		arr = new int[N][N];
		
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0; j<N; j++) {
				arr[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		for(int i=0; i<N; i++) {
			if(checkRow(i)) answer++;
			if(checkCol(i)) answer++;
		}
		
		System.out.println(answer);
	}
	
	// 높이 차이가 1이어야 함
	// 올라가는 경사가 있고 내려가는 경사가 있음
	// 경사로는 낮은 칸에 놓아야 하고, 낮은 칸의 높이는 모두 같고 L개가 연속되어야 함
	// 경사로가 계단의 범위를 벗어나면 안됨
	// 경사로가 이미 있는 곳에는 경사로를 둘 수 없음
	
	static boolean checkRow(int row) {
		boolean[] installed = new boolean[N]; // 경사로 설치 여부
		
		for(int i=0; i<N-1; i++) {
			int diff = arr[row][i] - arr[row][i+1];
			
			// 높이 차이가 1 이상이면 바로 false 리턴
			if(diff < -1 || diff > 1) return false;
			
			// 올라가는/내려가는 경사로를 설치할 수 없는 경우
			// 1. 계단의 길이가 경사로의 길이보다 짧은 경우
			// 2. 이미 경사로가 설치되어 있는 경우
			// 3. L개의 길이만큼 계단의 높이가 같아야 하는데 그렇지 않은 경우
			
			if(diff == -1) { // 올라가는 경사로
				if(i+1-L < 0) return false; // 1번
				for(int k=0; k<L; k++) {
					if(installed[i-k]) return false; // 2번
					if(arr[row][i] != arr[row][i-k]) return false; // 3번
					installed[i-k] = true; // 경사면 설치
				}
			}
			
			else if(diff == 1) { // 내려가는 경사로
				if(i+L >= N) return false; // 1번
				for(int k=1; k<=L; k++) {
					if(installed[i+k]) return false; // 2번
					if(arr[row][i+1] != arr[row][i+k]) return false; // 3번
					installed[i+k] = true; // 경사면 설치
				}
			}
		}
		
		return true;
	}
	
	static boolean checkCol(int col) {
		boolean[] installed = new boolean[N]; // 경사로 설치 여부
		
		for(int i=0; i<N-1; i++) {
			int diff = arr[i][col] - arr[i+1][col];
			
			// 높이 차이가 1 이상이면 바로 false 리턴
			if(diff < -1 || diff > 1) return false;
			
			// 올라가는/내려가는 경사로를 설치할 수 없는 경우
			// 1. 계단의 길이가 경사로의 길이보다 짧은 경우
			// 2. 이미 경사로가 설치되어 있는 경우
			// 3. L개의 길이만큼 계단의 높이가 같아야 하는데 그렇지 않은 경우
			
			if(diff == -1) { // 올라가는 경사로
				if(i+1-L < 0) return false; // 1번
				for(int k=0; k<L; k++) {
					if(installed[i-k]) return false; // 2번
					if(arr[i][col] != arr[i-k][col]) return false; // 3번
					installed[i-k] = true; // 경사면 설치
				}
			}
			
			else if(diff == 1) { // 내려가는 경사로
				if(i+L >= N) return false; // 1번
				for(int k=1; k<=L; k++) {
					if(installed[i+k]) return false; // 2번
					if(arr[i+1][col] != arr[i+k][col]) return false; // 3번
					installed[i+k] = true; // 경사면 설치
				}
			}
		}
		
		return true;
	}

}