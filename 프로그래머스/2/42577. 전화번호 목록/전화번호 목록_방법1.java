import java.util.*;

/**
phone_book의 최대 값이 100만
이중 for문 -> 시간초과!!

<방법1> - 정렬
    정렬(O(NlogN))을 이용한다. 앞에서부터 n번째랑 n+1번째만 비교하면 된다.
<방법2> - 해시
*/

class Solution {
    public boolean solution(String[] phone_book) {
        boolean answer = true;
        Arrays.sort(phone_book);
        for(int i=0; i<phone_book.length-1; i++) {
            if(phone_book[i+1].startsWith(phone_book[i])) {
                answer = false;
                break;
            }
        }
        return answer;
    }
}
