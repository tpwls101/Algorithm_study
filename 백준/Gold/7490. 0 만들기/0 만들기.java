import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * <BJ_7490_0만들기>
 * 1. 처음에 어떤 기호를 사용할 것인지 순열로 고른 다음에 바로 계산하고자 했는데, 식이 어차피 필요하기 때문에 dfs에 String 식을 바로 만드는게 낫다.
 * 2. 공백은 어떻게 처리할 것인가?
 * 		공백은 replaceAll을 사용해서 " "를 ""로 바꿔준다.
 * 3. 공백을 없앤 계산식 문자열을 계산해준다. split을 사용해도 되고, StringTokenizer를 사용해도 된다.
 * 		split을 사용하면 +는 \\+로 사용해야 한다. (주의) -> .split("\\+|-")
 * 
 * @author YooSejin
 *
 */

public class Main {
	
	static int T;
	static int N;
	static StringBuilder sb = new StringBuilder();
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		T = Integer.parseInt(br.readLine());
		
		for(int t=0; t<T; t++) {
			N = Integer.parseInt(br.readLine());
			dfs(1, "1");
			sb.append("\n");
		}
		
		System.out.println(sb);
	}
	
	static void dfs(int num, String s) {
		if(num == N) {
			String express = s.replaceAll(" ", ""); // 공백 없애고 계산
			if(calculate(express)) {
				sb.append(s + "\n");
			}
			return;
		}
		
		// " ", +, - 가 아스키 순서. 다른 순서로 넣으면 List 활용해서 정렬해줘야 함.
		dfs(num+1, s + " " + (num+1));
		dfs(num+1, s + "+" + (num+1));
		dfs(num+1, s + "-" + (num+1));
	}
	
	static boolean calculate(String s) {
		StringTokenizer st = new StringTokenizer(s, "+|-", true); // (문자열, 구분자, true/false) -> true면 구분자도 토큰으로 넣고, false면 토큰에 넣지 않는다. false가 디폴트.
		int sum = Integer.parseInt(st.nextToken());
		
		while(st.hasMoreTokens()) {
			String str = st.nextToken();
			if(str.equals("+")) {
				sum += Integer.parseInt(st.nextToken());
			} else if(str.equals("-")) {
				sum -= Integer.parseInt(st.nextToken());
			}
		}
		
		if(sum == 0) return true;
		return false;
	}

}
