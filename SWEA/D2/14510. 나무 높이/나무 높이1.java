import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
 
/**
 * <D2_14510_나무높이>
 * 가장 큰 나무의 높이(maxHeight) 구하기
 * 나머지 나무들 -> 가장 큰 나무의 높이가 되기 위해 필요한 높이(need) 구하기
 * 필요 높이를 만들기 위해 짝수날 뿌려야 하는 수(even)와 홀수날 뿌려야 하는 수(odd)를 구한다.
 * 이 때, even은 필요높이 / 2 를 통해 구할 수 있고, odd는 필요높이  % 2 를 통해 구할 수 있다.
 * 짝수날 뿌려야 하는 수와 홀수날 뿌려야 하는 수를 구한 후 두 수를 최대한 균등하게 만든다.
 * 이 때, 짝수 1번 = 홀수 2번 이기 때문에 짝수날 뿌려야 하는 수가 홀수날 뿌려야 하는 수보다 클 때에만 균등하게 만들어 줄 수 있다.
 * add+1 < even 일 때 동안만 while문을 반복하면서 짝수는 -1해주고 홀수는 +2 해준다.
 * 짝수, 홀수 횟수를 최대한 균등하게 만들고나면 세가지 경우의 수가 생긴다.
 * 
 * 1. 짝수 > 홀수
 * 짝수날이 더 큰 경우는 짝수날 = 홀수날+1 인 경우밖에 없다.
 * 따라서 홀짝홀짝의 순서로 물을 주기 때문에 물을 주지 않는 경우가 한 번 생기고 날짜에 더해주어야 한다.
 * 
 * 2. 홀수 > 짝수
 * 공식 : 짝수x2 + (홀수-짝수)x2 - 1
 * 짝수의 수만큼 홀짝홀짝 물을 주기 때문에 짝수x2를 해주고, 홀수는 (홀수-짝수)만큼 더 물을 뿌려주어야 하므로 (홀수-짝수)x2를 해준다.
 * 단, 홀짝홀짝 반복하다 마지막에 짝수는 날짜에 더해주지 않기 때문에 -1을 해준다.
 * 
 * 3. 짝수 = 홀수
 * 짝수날 뿌려야 하는 수와 홀수날 뿌려야 하는 수가 같다면 홀짝홀짝 동일한 수만큼 반복하기 때문에 그냥 두 수를 더해주면 된다.
 * 
 * @author 유세진
 *
 */
 
public class Solution {
 
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
         
        int T = Integer.parseInt(br.readLine()); // 테스트케이스 수
         
        for(int test_case=1; test_case<=T; test_case++) {
            int N = Integer.parseInt(br.readLine()); // 나무의 개수
             
            int[] height = new int[N]; // 나무들의 높이를 저장할 배열
             
            st = new StringTokenizer(br.readLine());
             
            int maxHeight = 0; // 가장 큰 나무의 높이
             
            // 나무 높이 입력받기
            for(int i=0; i<N; i++) {
                height[i] = Integer.parseInt(st.nextToken());
                maxHeight = Math.max(maxHeight, height[i]); // 가장 큰 나무의 높이 저장
            }
             
            int need = 0; // 최대 나무 높이가 되기 위해 필요한 높이
            int odd = 0; // 홀수날 물을 뿌려야 하는 수
            int even = 0; // 짝수날 물을 뿌려야 하는 수
             
            // 홀수날 물을 뿌려야 하는 수와 짝수날 물을 뿌려야 하는 수 구하기
            for(int i=0; i<N; i++) {
                need = maxHeight - height[i];
                odd += need % 2;
                even += need / 2;
            }
             
            // 짝수날 1번 == 홀수날 2번
            // 짝수날 물을 뿌려야 하는 수가 더 많을 때에만 균형을 맞춰줄 수 있다(짝수날 -1, 홀수날 +2)
            // 홀수날과 짝수날 물을 뿌려야 하는 수를 최대한 균등하게 만들어주자
            while(odd+1 < even) { // @@@@@@@@@@@????????????
                even--;
                odd += 2;
            }
             
            int answer = 0;
             
            // 최대한 균등하게 만들어주었을 때 나오는 3가지 경우의 수
             
            // 1. 짝수 > 홀수    (짝수날 = 홀수날+1)
            // 짝수날이 더 큰 경우는 짝수날 = 홀수날+1 인 경우밖에 없다.
            // 따라서 홀짝홀짝의 순서로 물을 주기 때문에 물을 주지 않는 경우가 한 번 생기고 날짜에 더해주어야 한다.
            if(even > odd) {
                answer = odd + even + 1;
            }
             
            // 2. 짝수 < 홀수
            // 짝수x2 + (홀수-짝수)x2 - 1
            // -1을 해주는 이유 : 홀짝홀짝 반복하다 마지막에 짝수는 계산하지 않기 때문
            else if(even < odd) {
                answer = even*2 + (odd-even)*2 - 1;
            }
             
            // 3. 짝수 = 홀수
            else {
                answer = odd + even;
            }
             
            System.out.printf("#%d %d\n", test_case, answer);
        }
         
    }
 
}
 
