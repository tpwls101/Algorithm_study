import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * <BJ_2179_비슷한단어>
 * 
 * @author YooSejin
 *
 */

public class Main {
	
	public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        int N = Integer.parseInt(br.readLine());
        
        String[] arr = new String[N];
        
        for(int i=0; i<N; i++) {
        	arr[i] = br.readLine();
        }
        
        String S = "";
        String T = "";
        int max = 0; // 가장 긴 공통 접두사의 길이
        
        for(int i=0; i<N; i++) {
        	String now = arr[i];
        	
        	for(int j=i+1; j<N; j++) {
        		String next = arr[j];
        		int len = getPrefixLength(now, next); // 공통 접두사의 길이 구하기
        		
        		if(max < len) {
        			max = len;
        			S = now;
        			T = next;
        		}
        	}
        }
        
        System.out.println(S);
        System.out.println(T);
	}
	
	static int getPrefixLength(String now, String next) {
    	int cnt = 0; // 공통 접두사의 길이
    	int len = Math.min(now.length(), next.length());
    	
    	for(int j=0; j<len; j++) {
    		if(now.charAt(j) != next.charAt(j)) break;
    		cnt++;
    	}
		
    	return cnt;
	}

}
