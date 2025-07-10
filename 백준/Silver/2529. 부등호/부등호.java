import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

/**
 * <BJ_2529_부등호>
 * 먼저 최대 0~9까지 10개의 숫자로 수를 만들 수 있으므로 long 타입 사용하기
 * 
 * 부등호 조건에 맞는 숫자를 고르는 방식보다
 * 그냥 순열로 숫자 뽑고 부등호 조건을 만족하는지 검사하는 방식이 구현이 더 쉬워서 선택했다.
 * -> 중복 불가능한 순열 구하기
 * 
 * 021과 같이 맨 앞의 0도 포함해 출력해야 하는 부분을 어떻게 해야할지 고민했음.
 * 문자열 s를 숫자로 바꿔 min과 max를 비교하고 String.format()을 사용할까 했지만 k+1 크기로 설정이 안됨.
 * 따라서 이 방식보다 list에 가능한 모든 결과값을 넣고 정렬한 후 0번째와 마지막 인덱스를 출력하는 방식이 좋음.
 * 
 * @author YooSejin
 *
 */

public class Main {
	
	static int k;
	static String[] sign;
	static boolean[] visited; // 0~9까지 사용했는지 확인
	static int[] number; // 선택한 숫자 저장
	static List<String> list;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;
        
        k = Integer.parseInt(br.readLine());
        
        sign = new String[k];
        visited = new boolean[10];
        number = new int[k+1];
        list = new ArrayList<>();
        
        st = new StringTokenizer(br.readLine());
        for(int i=0; i<k; i++) {
        	sign[i] = st.nextToken();
        }
		
        dfs(0, "");
        
        Collections.sort(list);
        
		System.out.println(list.get(list.size() - 1));
		System.out.println(list.get(0));
	}
	
	static void dfs(int cnt, String s) {
		if(cnt == k+1) {
			if(check()) { // 부등호 조건 만족하는지 검사
				list.add(s);
			}
			return;
		}
		
		for(int i=0; i<10; i++) {
			if(visited[i]) continue;
			number[cnt] = i;
			visited[i] = true;
			dfs(cnt+1, s+i);
			visited[i] = false;
		}
	}
	
	static boolean check() {
		boolean possible = true;
		
		for(int i=0; i<k; i++) {
			if(sign[i].equals("<")) {
				if(number[i] >= number[i+1]) {
					possible = false;
					break;
				}
			} else if(sign[i].equals(">")) {
				if(number[i] <= number[i+1]) {
					possible = false;
					break;
				}
			}
		}
		
		return possible;
	}

}
