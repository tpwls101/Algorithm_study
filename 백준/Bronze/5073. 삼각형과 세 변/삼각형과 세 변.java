import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	
	static int a;
	static int b;
	static int c;
	static int[] arr;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;
        
        arr = new int[3];
        
        while(true) {
        	st = new StringTokenizer(br.readLine());
        	arr[0] = a = Integer.parseInt(st.nextToken());
            arr[1] = b = Integer.parseInt(st.nextToken());
            arr[2] = c = Integer.parseInt(st.nextToken());
            
            if(a == 0) break;
            
            Arrays.sort(arr);
            
            if(arr[2] >= arr[0] + arr[1]) {
            	System.out.println("Invalid");
            	continue;
            }
            
            if(a == b && b == c) {
            	System.out.println("Equilateral");
            } else if(a != b && b != c && a != c) {
            	System.out.println("Scalene");
            } else {
            	System.out.println("Isosceles");
            }
        }
	}

}