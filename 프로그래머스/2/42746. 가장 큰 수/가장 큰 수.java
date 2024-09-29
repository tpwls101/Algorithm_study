import java.util.*;

/**
numbers의 길이는 10만까지고 원소는 1000까지 가능하므로
최악의 경우 네자리수가 10만개 있게 된다.
애초에 숫자로 환산할 수 없는 길이!!!! -> int나 long 타입을 사용할 수 없다!!

따라서 numbers를 문자열로 바꿔 내림차순 정렬한다.
단, 3과 30을 비교했을 때 [30, 3]이 아니라 [3, 30]이 되어야 함을 주의!!
따라서 두개씩 묶어서 비교한다.
*/

class Solution {
    public String solution(int[] numbers) {
        String[] str = new String[numbers.length];
        
        for(int i=0; i<str.length; i++) {
            str[i] = String.valueOf(numbers[i]);
        }
        
        // 두개씩 묶어서 내림차순 정렬
        Arrays.sort(str, (o1, o2) -> (o2+o1).compareTo(o1+o2));
        
        StringBuilder sb = new StringBuilder();
        if(str[0].equals("0")) {
            return "0";
        }
        
        for(String s : str) {
            sb.append(s);
        }
        
        return sb.toString();
    }
}