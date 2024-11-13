class Solution {
    
    static int videoLen;
    static int position;
    static int opStart;
    static int opEnd;
    
    public String solution(String video_len, String pos, String op_start, String op_end, String[] commands) {
        videoLen = toSec(video_len);
        position = toSec(pos); // 현재 위치
        opStart = toSec(op_start);
        opEnd = toSec(op_end);
        
        for(int i=0; i<commands.length; i++) {
            openingCheck();
            
            if(commands[i].equals("prev")) {
                if(position - 10 < 0) position = 0;
                else position -= 10;
            } else if(commands[i].equals("next")) {
                if(position + 10 > videoLen) position = videoLen;
                else position += 10;
            }
            
            openingCheck();
        }
        
        int m = position / 60;
        int s = position % 60;
        
        return String.format("%02d", m) + ":" + String.format("%02d", s);
    }
    
    static int toSec(String time) {
        String[] sArr = time.split(":");
        int m = Integer.parseInt(sArr[0]);
        int s = Integer.parseInt(sArr[1]);
        return m * 60 + s;
    }
    
    static void openingCheck() {
        if(position >= opStart && position <= opEnd) {
            position = opEnd;
        }
    }
}