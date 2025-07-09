import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * <BJ_16967_배열복원하기>
 * 그냥 구현문제
 * A 배열을 찾아내는 문제로 사실상 두 배열에 모두 포함되지 않는 경우는 고려할 필요가 없고
 * A의 크기만큼 for문을 돌리면서 겹치는 구간이 아니면 해당 위치의 값이 A(i,j)의 값이고
 * 겹치는 구간이면 식을 이용해 A(i,j)의 값을 구하면 되는 문제다.
 * 그림을 그려서 생각해보면 쉽게 규칙을 알아낼 수 있다.
 * 추가로 H와 W가 300까지 가능하므로 시간을 줄이기 위해 A(i,j)의 값을 하나씩 출력하는 것보다 StringBuilder를 사용했다.
 * 
 * @author YooSejin
 *
 */

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        int H = Integer.parseInt(st.nextToken());
        int W = Integer.parseInt(st.nextToken());
        int X = Integer.parseInt(st.nextToken());
        int Y = Integer.parseInt(st.nextToken());
		
        int[][] A = new int[H][W];
        int[][] B = new int[H+X][W+Y];
        
        for(int i=0; i<H+X; i++) {
        	st = new StringTokenizer(br.readLine());
        	for(int j=0; j<W+Y; j++) {
        		B[i][j] = Integer.parseInt(st.nextToken());
        	}
        }
        
        StringBuilder sb = new StringBuilder();
        
        for(int i=0; i<H; i++) {
        	for(int j=0; j<W; j++) {
        		if(i >= X && i < H && j >= Y && j < W) { // 겹치는 부분
        			A[i][j] = B[i][j] - A[i-X][j-Y];
        		} else { // 겹치는 부분이 아니면 배열 A
        			A[i][j] = B[i][j];
        		}
        		sb.append(A[i][j] + " ");
        	}
        	sb.append("\n");
        }
        
        System.out.println(sb.toString());
	}

}
