import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		
		boolean[][] arr = new boolean[101][101];
		
		int N = Integer.parseInt(br.readLine()); // 색종이의 수
		
		int count = 0;
		
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());
			
			for(int a=x; a<x+10; a++) {
				for(int b=y; b<y+10; b++) {
					if(!arr[a][b]) count++;
					arr[a][b] = true;
				}
			}
		}
		
		System.out.println(count);
	}

}