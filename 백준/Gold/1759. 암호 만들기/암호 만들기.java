import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.StringTokenizer;

public class Main {
	
	static int L; // 암호의 길이
	static int C; // 문자의 종류 개수
	static String[] arr; // 알파벳을 저장할 배열
	static boolean[] visited;
	static String[] selected;
	static Set<String> set = new HashSet<>();
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		L = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		
		arr = new String[C];
		visited = new boolean[C];
		selected = new String[L];
		
		st = new StringTokenizer(br.readLine());
		for(int i=0; i<C; i++) {
			arr[i] = st.nextToken();
		}
		
		Arrays.sort(arr);
		
		comb(0, 0);
		
		List<String> list = new ArrayList<>();
		
		// 암호 검사 (최소 한 개의 모음 + 최소 두 개의 자음)
		for(String code : set) {
			int vowelCnt = 0;
			
			if(code.contains("a")) vowelCnt++;
			if(code.contains("e")) vowelCnt++;
			if(code.contains("i")) vowelCnt++;
			if(code.contains("o")) vowelCnt++;
			if(code.contains("u")) vowelCnt++;
			
			if(vowelCnt >= 1 && (L - vowelCnt) >= 2) {
				list.add(code);
			}
		}
		
		// 알파벳 순으로 출력하기 위해 정렬
		Collections.sort(list);
		
		for(int i=0; i<list.size(); i++) {
			System.out.println(list.get(i));
		}
	}
	
	static void comb(int start, int cnt) {
		// 조합으로 L개의 문자 뽑고 정렬 -> 중복되는 암호는 제거
		if(cnt == L) {
			Arrays.sort(selected);
			
			String code = "";
			for(int i=0; i<L; i++) {
				code += selected[i];
			}
			set.add(code);
			
			return;
		}
		
		for(int i=start; i<C; i++) {
			if(visited[i]) continue;
			selected[cnt] = arr[i];
			visited[i] = true;
			comb(i+1, cnt+1);
			visited[i] = false;
		}
	}

}