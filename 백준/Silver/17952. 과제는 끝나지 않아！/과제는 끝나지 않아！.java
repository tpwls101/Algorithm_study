import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

public class Main {
    
    static class Work {
        int score; // 업무 만점점수
        int time; // 걸리는 시간
        
        public Work(int score, int time) {
            this.score = score;
            this.time = time;
        }
    }
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        int N = Integer.parseInt(br.readLine()); // 이번 분기의 분
        
        Stack<Work> stack = new Stack<>(); // 스택 생성 (남은 업무 저장)
        
        int answer = 0; // 김삼성이 받을 이번 분기 업무 평가 점수
        
        String[] s;
        for(int i=0; i<N; i++) {
            s = br.readLine().split(" ");
            if(s[0].equals("1")) { // 업무가 있을 때
                int score = Integer.parseInt(s[1]);
                int time = Integer.parseInt(s[2]);
                
                // 1초간 업무처리를 하고 남은 업무가 있으면 스택에 저장
                if(time - 1 != 0) {
                    stack.push(new Work(score, time-1));
                } else { // 업무를 다 처리했으면 점수 get
                    answer += score;
                }
            } else { // 업무가 없을 때
                // 기존에 하던 업무 마저 처리
                if(!stack.isEmpty()) { // isEmpty !!!!!!!!!!!!!!!!!!
                	Work work = stack.pop();
                	// 남은 업무가 있으면 다시 스택에 저장
                    if(work.time - 1 != 0) {
                        stack.push(new Work(work.score, work.time-1));
                    } else { // 업무를 다 처리했으면 점수 get
                        answer += work.score;
                    }
                }
            }
        }
        
        System.out.println(answer);
        
    }

}