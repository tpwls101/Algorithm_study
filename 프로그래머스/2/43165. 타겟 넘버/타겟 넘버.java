class Solution {
    
    static int[] numbers; // 전역변수로 선언안하면
    static int target; // 매번 dfs에서 같이 매개변수로 넘겨줘야함
    static int count = 0;
    
    public int solution(int[] numbers, int target) {
        this.numbers = numbers;
        this.target = target;
        
        dfs(0, 0); // index와 sum
        
        return count;
    }
    
    static public void dfs(int index, int sum) {
        if(index == numbers.length) {
            if(sum == target) count++;
            return;
        }
        
        dfs(index + 1, sum + numbers[index]);
        dfs(index + 1, sum - numbers[index]);
    }
}