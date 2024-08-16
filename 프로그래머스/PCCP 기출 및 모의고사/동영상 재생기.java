/**
<기출문제2 1번>
*/

class Solution {
    
    static int length; // 비디오 전체 길이
    static int current; // 비디오 시작 위치 겸 최종 위치
    static int opStart; // 오프닝 시작 위치
    static int opEnd; // 오프닝 종료 위치

    public String solution(String video_len, String pos, String op_start, String op_end, String[] commands) {
        length = toSec(video_len);
        current = toSec(pos);
        opStart = toSec(op_start);
        opEnd = toSec(op_end);

        // 명령 실행 전후로 현재 위치가 오프닝 구간에 포함되는지 확인
        for(int i=0; i<commands.length; i++) {
            checkCurrent();

            if(commands[i].equals("prev")) {
                current -= 10;
                if(current < 0) current = 0;
            } else if(commands[i].equals("next")) {
                current += 10;
                if(current > length) current = length;
            }

            checkCurrent();
        }

        int m = current / 60;
        int s = current % 60;

        return String.format("%02d", m) + ":" + String.format("%02d", s);
    }

    public int toSec(String time) {
        String[] str = time.split(":");
        int m = Integer.parseInt(str[0]);
        int s = Integer.parseInt(str[1]);

        return m * 60 + s;
    }

    public void checkCurrent() {
        if(current >= opStart && current <= opEnd) {
            current = opEnd;
        }
    }
}
