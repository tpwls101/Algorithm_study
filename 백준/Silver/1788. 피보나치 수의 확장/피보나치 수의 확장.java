import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
	
	static int N;
	static int[] plusdp;
	static int[] minusdp;
	static final int MOD = 1000000000;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		N = Integer.parseInt(br.readLine());
		
		plusdp = new int[1000001];
		minusdp = new int[1000001];
		
		plusdp[1] = 1;
		minusdp[1] = 1;
		
		for(int i=2; i<=1000000; i++) {
			plusdp[i] = (plusdp[i-1] + plusdp[i-2]) % MOD;
		}
		
		for(int i=2; i<=1000000; i++) {
			minusdp[i] = (minusdp[i-2] - minusdp[i-1]) % MOD;
		}
		
		if(N <= 0) {
			N = Math.abs(N);
			if(minusdp[N] > 0) System.out.println(1);
			else if(minusdp[N] == 0) System.out.println(0);
			else System.out.println(-1);
			
			System.out.println(Math.abs(minusdp[N]));
		} else { // N이 양수면 F(N)도 무조건 양수
			System.out.println(1);
			System.out.println(plusdp[N]);
		}
		
	}

}
