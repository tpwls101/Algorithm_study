import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;
        
        int N = Integer.parseInt(br.readLine());
        
        Deque<Integer> queue = new ArrayDeque<>(); // 가장 뒤에 있는 정수 출력 위해 Queue 대신 Deque 사용
        
        for(int i=0; i<N; i++) {
        	st = new StringTokenizer(br.readLine());
        	String command = st.nextToken();
        	
        	// if문 보다 switch문이 깔끔함
        	switch(command) {
        	case "push":
        		queue.add(Integer.parseInt(st.nextToken()));
        		break;
        	case "pop":
        		if(queue.isEmpty()) System.out.println(-1);
        		else System.out.println(queue.poll());
        		break;
        	case "size":
        		System.out.println(queue.size());
        		break;
        	case "empty":
        		if(queue.isEmpty()) System.out.println(1);
        		else System.out.println(0);
        		break;
        	case "front":
        		if(queue.isEmpty()) System.out.println(-1);
        		else System.out.println(queue.peek());
        		break;
        	case "back":
        		if(queue.isEmpty()) System.out.println(-1);
        		else System.out.println(queue.getLast());
        		break;
        	default:
        		break;
        	}
        }
	}

}
