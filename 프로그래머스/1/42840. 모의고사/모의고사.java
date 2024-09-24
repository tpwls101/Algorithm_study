import java.util.*;

class Solution {
    public int[] solution(int[] answers) {
        int[] person1 = { 1, 2, 3, 4, 5 };
        int[] person2 = { 2, 1, 2, 3, 2, 4, 2, 5 };
        int[] person3 = { 3, 3, 1, 1, 2, 2, 4, 4, 5, 5 };
        
        int count1 = 0;
        int count2 = 0;
        int count3 = 0;
        
        for(int i=0; i<answers.length; i++) {
            if(answers[i] == person1[i % person1.length]) count1++;
            if(answers[i] == person2[i % person2.length]) count2++;
            if(answers[i] == person3[i % person3.length]) count3++;
        }
        
        int max = Math.max(Math.max(count1, count2), count3);
        
        List<Integer> list = new ArrayList<>();
        if(count1 == max) list.add(1);
        if(count2 == max) list.add(2);
        if(count3 == max) list.add(3);
        
        int[] answer = new int[list.size()];
        for(int i=0; i<list.size(); i++) {
            answer[i] = list.get(i);
        }
        
        return answer;
    }
}