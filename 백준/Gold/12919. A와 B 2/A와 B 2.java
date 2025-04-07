import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * <BJ_12919_A와B2>
 * 가능한 범위가 50까지이므로 크지 않아 dfs를 돌렸지만 시간초과가 났다.
 * A를 더하거나 B를 더하고 뒤집거나 2가지 경우의 수가 가능한데 문자열의 최대 길이가 50까지이므로
 * 2^50은 2^10 x 2^10 x 2^10 x 2^10 x 2^10 = ??_000_000_000_000_000 이상이므로 시간초과가 난다.
 * 
 * 따라서 S를 T로 바꾸는게 아니라 T를 S로 바꾸는 방법을 쓰는 것이 효과적이다.
 * S->T는 가능한 모든 경우를 따져봐야 하지만
 * T->S는 맨 끝이 A로 끝나는지, 혹은 맨 앞이 B인지 조건을 확인한 후 dfs를 실행시킬 수 있기 때문에 시간을 줄일 수 있다.
 * 
 * @author YooSejin
 *
 */

public class Main {
	
	static String S, T;
	static int answer = 0;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		S = br.readLine();
		T = br.readLine();
		
		dfs(T);
		
		System.out.println(answer);
	}
	
	static void dfs(String T) {
		if(answer == 1) return;
		
		if(S.length() == T.length()) {
			if(S.equals(T)) answer = 1;
			return;
		}
		
		if(T.endsWith("A")) {
			dfs(T.substring(0, T.length()-1));
		}
		
		if(T.startsWith("B")) {
			dfs(reverse(T.substring(1)));
		}
	}
	
	static String reverse(String s) {
		StringBuilder sb = new StringBuilder(s);
		return sb.reverse().toString();
	}
}
