import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;

/**
 * <BJ_14719_빗물>
 * 처음에 스택 문제인줄 알았으나 아님
 * stack.peek() > height 이면 높이의 차이를 sum에 더해준다고 했을 때
 * 높이가 3 1 2 로 주어지면
 * 답은 1이 되어야 하는데 3이 되어버림
 * 
 * <풀이 방법> - 구현
 * 1. 빗물이 고이는 조건
 * 		- 열을 기준으로 양쪽에 자신보다 높은 블럭이 존재해야 함
 * 		- 그 중 높이가 더 낮은 블록을 기준으로 빗물의 양 계산
 * 		- 첫번째와 마지막 블록에는 빗물이 고일 수 없음
 * 2. 각 인덱스별로 고이는 빗물의 양을 구해 더해주면 됨
 * 
 * @author YooSejin
 *
 */

public class Main {
	
	static int H;
	static int W;
	static int[] height;
	static int sum = 0;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        H = Integer.parseInt(st.nextToken());
        W = Integer.parseInt(st.nextToken());
        
        height = new int[W];
        
        st = new StringTokenizer(br.readLine());
        for(int i=0; i<W; i++) {
        	height[i] = Integer.parseInt(st.nextToken());
        }
        
        // 첫번째와 마지막 블록은 빗물이 고일 수 없으므로 제외
        for(int i=1; i<W-1; i++) {
        	int leftMax = 0;
        	int rightMax = 0;
        	
        	// 인덱스 기준 왼쪽에서 가장 높이가 높은 블럭 찾기
        	for(int j=i-1; j>=0; j--) {
        		leftMax = Math.max(leftMax, height[j]);
        	}
        	
        	// 인덱스 기준 오른쪽에서 가장 높이가 높은 블럭 찾기
        	for(int j=i+1; j<W; j++) {
        		rightMax = Math.max(rightMax, height[j]);
        	}
        	
        	// 양쪽의 가장 높은 블럭이 기준 인덱스의 높이보다는 커야함
        	if(leftMax > height[i] && rightMax > height[i]) {
        		int standard = Math.min(leftMax, rightMax);
            	sum += standard - height[i];
        	}
        }
        
        System.out.println(sum);
	}

}
