import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

/**
 * <BJ_9935_문자열폭발>
 * String.contains와 replaceAll을 사용하면 간단하게 풀 수 있는 문제.
 * 하지만 이렇게 풀면 메모리 초과가 난다.

 * String.replaceAll(regex, replacement) 접근 방식을 사용하면 한 번의 호출로 겉보기에는 교체를 수행 할 수 있지만 내부적으로 많은 추가 GC(Garbage Collection) 적격 객체가 생성된다.
 * 따라서 문자열 교체 문제는 replace가 아닌 다른 방법을 생각하는 것이 좋다!
 * 특히 String은 매번 객체를 할당하는 방식이기에 메모리 초과가 날 것이라고 생각한다.
 * 
 * 따라서 스택을 사용해야 한다.
 * 문자열의 문자를 하나씩 스택에 push하며 스택의 크기가 폭발 문자열의 길이보다 클 때 폭발 문자열을 가지고 있는지 확인한다.
 * 스택이 추가될 때마다 폭발 문자열을 가지고 있는지 확인하므로 폭발 문자열의 길이만큼만 확인하면 된다.
 * 가장 마지막에 쌓인 스택 중 폭발 문자열을 가지고 있다면 그 길이만큼 pop하면 되고, 이후 이를 반복한다.
 * 
 * @author YooSejin
 *
 */

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
//		String str = br.readLine();
//		String bomb = br.readLine();
//		
//		while(true) {
//			if(str.contains(bomb)) {
//				str = str.replaceAll(bomb, "");
//			} else {
//				break;
//			}
//		}
//		
//		System.out.println(str.equals("") ? "FRULA" : str);
		
		
		String str = br.readLine();
		String bomb = br.readLine();
		int bombLen = bomb.length(); // length()도 결국 하나의 String 메서드. 반복적으로 호출하면 메서드 호출 스택 처리 비용이 발생하므로 변수로 미리 지정하는게 성능에 좋다.
		
		Stack<Character> stack = new Stack<>();
		
		for(int i=0; i<str.length(); i++) {
			stack.push(str.charAt(i));
			
			// 스택 사이즈가 폭발 문자열의 길이보다 크거나 같으면 문자열을 뺼 수 있는지 바로 확인해서 제거
			if(stack.size() >= bombLen) {
				boolean flag = true;
				
				for(int j=0; j<bombLen; j++) {
					if(stack.get(stack.size() - bombLen + j) != bomb.charAt(j)) {
						flag = false;
						break;
					}
				}
				
				if(flag) {
					for(int j=0; j<bombLen; j++) {
						stack.pop();
					}
				}
			}
		}
		
		StringBuilder sb = new StringBuilder();
		for(Character c : stack) {
			sb.append(c);
		}
		
		System.out.println(sb.length() == 0 ? "FRULA" : sb);
	}

}
