class Solution {
    public String solution(String video_len, String pos, String op_start, String op_end, String[] commands) {
        String answer = "";
        
        int position = toSec(pos); // 현재 위치
        int opStart = toSec(op_start);
        int opEnd = toSec(op_end);
        int videoLen = toSec(video_len);
        
        for(int i=0; i<commands.length; i++) {
            if(position >= opStart && position <= opEnd) {
                position = opEnd;
            }
            
            if(commands[i].equals("prev")) {
                if(position - 10 < 0) {
                    position = 0;
                } else {
                    position -= 10;
                }
            } else if(commands[i].equals("next")) {
                if(position + 10 > videoLen) {
                    position = videoLen;
                } else {
                    position += 10;
                }
            }
            
            if(position >= opStart && position <= opEnd) {
                position = opEnd;
            }
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
}