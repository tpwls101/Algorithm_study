import java.util.*;

/**
가능한 모든 경로를 탐색해야 한다!!
tickets 배열 도착지 기준으로 정렬해놓고 한번에 경로 찾아서 출력하면 실패!!! (예외처리 안되어있음)
예를 들어 ATL에서 ICN과 SFO를 갈 수 있다고 했을 때, 정렬이 되어 있으니 당연히 ICN으로 갈 것
하지만 ICN에서 다음 경로가 없다면?
SFO로 갔어야 했다!
*/

class Solution {
    
    static List<String> list = new ArrayList<>();
    static String[][] tickets;
    static boolean[] visited; // 출발점으로 방문 체크
    
    public String[] solution(String[][] tickets) {
        this.tickets = tickets;
        visited = new boolean[tickets.length];
        
        dfs(0, "ICN", "ICN");
        
        Collections.sort(list);
        
        String[] answer = list.get(0).split(" ");
        
        return answer;
    }
    
    // depth와 현재 공항, 가능한 한가지 경로를 담을 문자열
    static public void dfs(int depth, String airport, String course) {
        
        // 가능한 한가지 경로가 나왔을 때
        if(depth == tickets.length) {
            list.add(course);
            return;
        }
        
        for(int i=0; i<tickets.length; i++) {
            if(tickets[i][0].equals(airport) && !visited[i]) {
                visited[i] = true;
                // course를 dfs 매개변수로 같이 보내주는 것이 포인트! (이것 때문에 자꾸 실패했다ㅜㅜ)
                dfs(depth+1, tickets[i][1], course + " " + tickets[i][1]);
                visited[i] = false;
            }
        }
    }
}