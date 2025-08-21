import java.util.*;

/*
한 번에 최대 2명씩 밖에 태울 수 없으니 투 포인터로 접근.
정렬하면 가장 가벼운 사람과 가장 무거운 사람을 같이 태울 수 있는지 없는지만 확인하면 됨.
제한보다 무거워 같이 태울 수 없다면 가장 무거운 사람은 어차피 누구와도 같이 타지 못함.
따라서 구명보트에 태워보내고 right 인덱스를 하나 감소시켜 다음으로 무거운 사람과 비교.
두명을 같이 태울 수 있다면 둘 다 태워보내고 left 인덱스는 증가, right 인덱스는 감소시켜 다음 사람 비교.
이를 left <= right일 때까지 반복하면 구명보트의 최소값이 나옴.
*/

class Solution {
    public int solution(int[] people, int limit) {
        Arrays.sort(people);
        
        int left = 0;
        int right = people.length - 1;
        
        int answer = 0;
        while(left <= right) {
            if(people[left] + people[right] > limit) {
                answer++;
                right--;
            } else {
                answer++;
                left++;
                right--;
            }
        }
        
        return answer;
    }
}