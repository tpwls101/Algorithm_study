import java.util.*;

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
            isPrime(num);
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
    static public void isPrime(int num) {
        if(num < 2) return;
        
        for(int i=2; i<num; i++) {
            if(num % i == 0) { // 나눠지는 수가 있으면 소수가 아님
                return;
            }
        }
        count++; // 나눠지는 수가 없으면 소수이므로 카운트
    }
}