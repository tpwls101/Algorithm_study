import java.util.*;

/*
분할 가능한 모든 경우의 수를 나눠보고 형과 동생의 토핑 수를 세어 비교하면 이중 for문을 돌아야 하므로 시간복잡도는 O(N^2)이고 topping의 길이가 최대 100만이므로 시간초과가 난다. 따라서 무조건 for문을 한번만 도는 방향으로 풀이법을 생각했다.

해답은 형과 동생의 토핑을 담은 map 두 개를 만드는 것에서 찾았다.
이미 동생이 모든 토핑을 가지고 있다는 전제 하에 for문을 한번만 돌리면서 한가지 토핑씩 형에게 건내주면 각 map의 사이즈가 각자 가진 토핑의 개수가 된다.
따라서 for문을 한번만 돌리면서 map의 사이즈가 같은 경우의 수를 찾으면 토핑의 개수를 똑같이 나눌 수 있는 경우의 수랑 같다.
*/

class Solution {
    public int solution(int[] topping) {
        Map<Integer, Integer> older = new HashMap<>(); // 형의 토핑
        Map<Integer, Integer> younger = new HashMap<>(); // 동생 토핑
        
        // 동생 토핑 세팅
        for(int type : topping) {
            younger.put(type, younger.getOrDefault(type, 0) + 1);
        }
        
        int answer = 0;
        
        // 케이크를 반으로 나누며 토핑 개수 확인
        for(int type : topping) {
            older.put(type, older.getOrDefault(type, 0) + 1);
            if(younger.get(type) == 1) {
                younger.remove(type);
            } else {
                younger.put(type, younger.get(type) - 1);
            }
            
            if(older.size() == younger.size()) answer++;
        }
        
        return answer;
    }
}