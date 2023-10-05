import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Queue;
import java.util.Set;
import java.util.StringTokenizer;
import java.util.TreeSet;

/**
 * <SW_5658_보물상자비밀번호>
 * 보물상자의 비밀번호를 구해라! -> 비밀번호 : 보물상자에 적힌 숫자로 만들 수 있는 모든 수 중, K번째로 큰 수를 10진수로 만든 수!
 * 0회전, 1회전, 2회전까지 가능! -> 3회전하면 0회전과 동일!
 * 주의 : 서로 다른 회전 횟수에서 동일한 수가 중복으로 생성될 수 있음!! -> 크기에 따라 순서를 셀 때 같은 수를 중복으로 세지 말 것!
 * 
 * 1. 한 변이 세자리수(N/4) 일 때 2회전(N/4 - 1)까지 가능
 * 2. numbers 배열을 세자리수(N/4)만큼씩 끊어서 회전별로 생성 가능한 수를 만든다.
 *         0회전 : 1B3, B3B, 81F, 75E
 *         1회전 : 
 *         2회전 : 
 *         => 회전할 때마다 shift(>>) 연산자 써보기?
 * 3. 생성한 수를 Set 자료구조에 넣어서 중복 없애기
 * 4. 다시 ArrayList로 넣어서 오름차순 정렬
 * 
 * 너무 노가다............! (X) => 자료구조를 활용하자!
 * 
 * @author 유세진
 *
 */

public class Solution {
    
    static int N; // 숫자의 개수
    static int K; // 크기 순서
    //static String[] numbers; // 입력받은 16진수 숫자들을 저장할 배열
    
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        int T = Integer.parseInt(br.readLine()); // 테스트케이스 수
        
        for(int tc=1; tc<=T; tc++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            
            N = Integer.parseInt(st.nextToken());
            K = Integer.parseInt(st.nextToken());
            int size = N/4; // 비밀번호 길이
            
            
            Queue<String> queue = new ArrayDeque<>();
            TreeSet<String> set = new TreeSet<>(Collections.reverseOrder());
            
            // 처음 한 줄 입력받은 값 큐에 넣기
            String[] s = br.readLine().split("");
            for(int i=0; i<N; i++) {
                queue.add(s[i]);
            }
            
            // 보물상자 뚜껑 size-1 만큼 회전
            for(int i=0; i<size; i++) {
                
                // 비밀번호 TreeSet에 넣기
                for(int a=0; a<4; a++) { // 비밀번호는 4개니까 4번 반복
                	String pw = "";
                    for(int b=0; b<size; b++) { // 비밀번호의 길이
                        String one = queue.poll(); // 큐에서 꺼낸 비밀번호 중 한자리
                        pw += one; // size 크기의 비밀번호 만들기
                        queue.offer(one);
                    }
                    set.add(pw); // TreeSet에 비밀번호 넣기
                }
                
                // 한칸씩 밀어주기
                String one = queue.poll(); // 맨 앞에꺼 빼서
                queue.offer(one); // 맨 뒤에 넣어주기
                
            }
            
            
            	String[] arr = set.toArray(new String[0]);
            
            	
            	
            	
            	
            	System.out.println("#" + tc + " " + Integer.parseInt(arr[K-1], 16));
//            Iterator iter = set.iterator();
//            int cnt = 1;
//            while(iter.hasNext()) {
//            	//System.out.println(iter.next());
//            	iter.next();
//            	cnt++;
//            	if(cnt == K) {
//            		//System.out.println(iter.next());
//            		String hex = (String)iter.next();
//            		System.out.printf("#%d %d\n", tc, Integer.parseInt(hex, 16)); // 16진수 문자열 -> 10진수 정수로 바꿔서 출력
//            	}
//            }
            
            // 다음 테케 실행 위해서 큐랑 셋 비우기!!!!!!
            queue.clear();
            set.clear();
        }
        
    }

}