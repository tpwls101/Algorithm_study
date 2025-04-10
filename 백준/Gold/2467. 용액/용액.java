import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	
	static int N; // 전체 용액의 수
	static int[] arr;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;
        
        N = Integer.parseInt(br.readLine());
        
        arr = new int[N];
		
        st = new StringTokenizer(br.readLine());
        for(int i=0; i<N; i++) {
        	arr[i] = Integer.parseInt(st.nextToken());
        }
        
        int start = 0;
        int end = N-1;
        int min = Integer.MAX_VALUE;
        int answer[] = new int[2];
		
        while(true) {
        	if(start >= end) break;
        	
        	int diff = arr[start] + arr[end];
        	if(Math.abs(diff) < min) {
        		min = Math.abs(diff);
        		answer[0] = arr[start];
        		answer[1] = arr[end];
        	}
        	
        	if(diff < 0) start++;
        	else end--;
        }
        
        System.out.println(answer[0] + " " + answer[1]);
	}

}
