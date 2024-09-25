import java.util.*;

/**
간과한 포인트!!
소수를 판별하는 메서드에서 1 이하는 false를 리턴
짝수인지 확인해서 짝수이면 false를 리턴했다
하지만 2는 짝수이지만 소수이다!!
따라서 if(num < 2), if(num == 2), if(num % 2 == 0)인 경우를 다 고려해야 한다.
그게 아니라면 그냥 if(num < 2)만 고려하고 for문으로 2부터 돌려서 처리해도 됨 (하지만 시간이 더 걸리겠죠?)
*/

class Solution {
    
    static String numbers;
    static boolean[] visited;
    static Set<Integer> set = new HashSet<>();
    static int count = 0; // 소수의 개수
    
    public int solution(String numbers) {
        this.numbers = numbers;
        visited = new boolean[numbers.length()];
        
        // 만들 수 있는 모든 경우의 수 구하기 (단, 중복된 수 제거)
        dfs("", 0);
        
        for(int num : set) {
            if(isPrime(num)) {
                count++;
            }
        }
        
        return count;
    }
    
    // s : 만들 수 있는 수
    static public void dfs(String s, int depth) {
        if(depth == numbers.length()) {
            return;
        }
        
        for(int i=0; i<numbers.length(); i++) {
            if(visited[i]) continue;
            set.add(Integer.parseInt(s + numbers.charAt(i)));
            visited[i] = true;
            dfs(s + numbers.charAt(i), depth+1);
            visited[i] = false;
        }
    }
    
    // 소수인지 판별하는 메서드
    static public boolean isPrime(int num) {
        if(num < 2) return false; // 1 이하는 소수가 아님
        if(num == 2) return true; // 2는 짝수지만 소수임
        if(num % 2 == 0) return false; // 짝수는 소수가 아님
        
        for(int i=3; i<=Math.sqrt(num); i++) {
            if(num % i == 0) { // 나눠지는 수가 있으면 소수가 아님
                return false;
            }
        }
        return true;
    }

}
