import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;

public class Main {
	
	static int N; // 옵션의 개수
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		N = Integer.parseInt(br.readLine());
		
		Set<String> set = new HashSet<>();
		
		StringBuilder sb = new StringBuilder();
		
		for(int i=0; i<N; i++) {
			String[] sArr = br.readLine().split(" ");
			
			boolean flag = false;
			
			// 단어의 첫번째 알파벳 먼저 확인
			for(int j=0; j<sArr.length; j++) {
				String first = sArr[j].substring(0, 1);
				
				if(!set.contains(first.toLowerCase()) && !flag) {
					set.add(first.toLowerCase());
					flag = true;
					sArr[j] = "[" + sArr[j].substring(0, 1) + "]" + sArr[j].substring(1);
					continue;
				}
			}
			
			if(flag) {
				print(sArr);
				continue;
			}
			
			// 단어의 첫번째 알파벳을 다 단축키로 쓰지 못하면 차례대로 확인
			for(int j=0; j<sArr.length; j++) {
				for(int k=0; k<sArr[j].length(); k++) {
					String alphabet = sArr[j].substring(k, k+1);
					
					if(!set.contains(alphabet.toLowerCase())) {
						set.add(alphabet.toLowerCase());
						sArr[j] = sArr[j].substring(0, k) + "[" + sArr[j].substring(k, k+1) + "]" + sArr[j].substring(k+1);
						flag = true;
						break;
					}
				}
				if(flag) break;
			}
			
			print(sArr);
		}
	}
	
	static void print(String[] sArr) {
		StringBuilder sb = new StringBuilder();
		for(int i=0; i<sArr.length; i++) {
			sb.append(sArr[i] + " ");
		}
		System.out.println(sb);
	}

}