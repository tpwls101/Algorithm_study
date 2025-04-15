import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;

public class Main {
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;
        
        int N = Integer.parseInt(br.readLine());
        
        Stack<Integer> stack = new Stack<>();
        int count = 0; // 건물 최소 갯수
		
        for(int i=0; i<N; i++) {
        	st = new StringTokenizer(br.readLine());
        	
        	int x = Integer.parseInt(st.nextToken());
        	int y = Integer.parseInt(st.nextToken());
        	
        	while(true) {
        		if(!stack.isEmpty()) {
        			if(stack.peek() > y) {
            			stack.pop();
            			count++;
            		} else if(stack.peek() < y) {
            			stack.push(y);
            			break;
            		} else { // 높이가 같으면 stack에 추가해봤자 나중에 같은 수를 모두 pop하면서 여러 번 count++되므로 한번만 들어있어야 한다.
            			break;
            		}
        		} else {
        			stack.push(y);
        			break;
        		}
        	}
        }
        
        // 스카이라인 마지막 부분 처리해줘야 함.
        // 마지막 높이가 0이라면 더 이상의 건물은 없다.
        // 하지만 마지막 높이가 1이상으로 끝나면 가능한 건물이 더 있을 수 있으므로 스택에서 하나씩 꺼내 건물 개수를 카운트해줘야 한다.
        while(!stack.isEmpty()) {
        	if(stack.pop() > 0) {
        		count++;
        	}
        }
		
		System.out.println(count);
	}

}
