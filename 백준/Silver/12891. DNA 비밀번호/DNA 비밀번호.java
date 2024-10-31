import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * <BJ_12891_DNA비밀번호>
 * 처음에 list에 비밀번호를 저장하고
 * 맨 첫번째 문자를 지우고 -> remove(0)
 * 새로운 문자를 추가하였는데  -> list.add()
 * 시간초과가 났다.
 * 100만번 돌아가는 for문 안에서 list.add()를 해주는데
 * list.add()의 시간복잡도는 O(N)이므로
 * 100만 x 100만이 최대가 되어 시간초과가 나는 것 같다.
 * 
 * list에 굳이 비밀번호 문자를 저장하지 않아도 되는 문제여서
 * list에 삭제하고 추가하는 작업을 지우니 시간초과 없이 통과했다.
 * 
 * @author YooSejin
 *
 */

public class BJ_12891_DNA비밀번호 {
	
	static int S; // DNA 문자열의 길이
	static int P; // 비밀번호로 사용할 부분 문자열의 길이
	static int answer = 0; // 가능한 비밀번호의 수
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        S = Integer.parseInt(st.nextToken());
        P = Integer.parseInt(st.nextToken());
		
        String[] s = br.readLine().split(""); // DNA 문자열
        
        int[] acgt = new int[4]; // A C G T 가 포함되어야 할 최소 개수
        st = new StringTokenizer(br.readLine());
        for(int i=0; i<4; i++) {
        	acgt[i] = Integer.parseInt(st.nextToken());
        }
		
//        List<String> list = new ArrayList<>(); // 비밀번호를 저장할 리스트
        
        int Acnt = 0;
        int Ccnt = 0;
        int Gcnt = 0;
        int Tcnt = 0;
        
        for(int i=0; i<P; i++) {
//        	list.add(s[i]);
        	if(s[i].equals("A")) Acnt++;
        	if(s[i].equals("C")) Ccnt++;
        	if(s[i].equals("G")) Gcnt++;
        	if(s[i].equals("T")) Tcnt++;
        }
        
        if(Acnt >= acgt[0] && Ccnt >= acgt[1] && Gcnt >= acgt[2] && Tcnt >= acgt[3]) {
        	answer++;
        }
        
        for(int i=P; i<S; i++) {
//        	String removed = list.remove(0);
        	if(s[i-P].equals("A")) Acnt--;
        	if(s[i-P].equals("C")) Ccnt--;
        	if(s[i-P].equals("G")) Gcnt--;
        	if(s[i-P].equals("T")) Tcnt--;
        	
//        	list.add(s[i]);
        	if(s[i].equals("A")) Acnt++;
        	if(s[i].equals("C")) Ccnt++;
        	if(s[i].equals("G")) Gcnt++;
        	if(s[i].equals("T")) Tcnt++;
        	
        	if(Acnt >= acgt[0] && Ccnt >= acgt[1] && Gcnt >= acgt[2] && Tcnt >= acgt[3]) {
            	answer++;
            }
        }
		
        System.out.println(answer);
	}

}
