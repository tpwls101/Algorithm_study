import java.util.*;

// HashMap에 차번호가 없으면 (차번호, 입차시간) 저장
// 수정 : 그냥 "IN"이면 (차번호, 입차시간) 저장
// OUT이면 주차시간 계산해서 다른 HashMap에 (차번호, 주차시간) 저장 + map에서 제거

// 최적화 및 오류 수정
// 1. 차번호별 주차시간을 TreeMap에 저장
//      HashMap에 저장 후 List에 주차요금을 따로 저장해 정렬할 필요 없음
// 2. EntrySet이든 KeySet이든 for문 돌리는 와중에 제거하지 말 것
//      ConcurrentModificationException 발생

class Solution {
    public int[] solution(int[] fees, String[] records) {
        Map<String, Integer> inTime = new HashMap<>(); // (차번호, 입차시간)
        Map<String, Integer> parkingTime = new TreeMap<>(); // (차번호, 주차시간) -> TreeMap으로 차번호 작은순으로 자동 정렬
        
        for(int i=0; i<records.length; i++) {
            String[] record = records[i].split(" ");
            int time = toMin(record[0]);
            String carNum = record[1];
            String type = record[2];
            
            if(type.equals("IN")) {
                inTime.put(carNum, time);
            } else {
                int duration = time - inTime.get(carNum); // 주차시간 계산
                parkingTime.put(carNum, parkingTime.getOrDefault(carNum, 0) + duration); // 기존에 주차했던 차는 시간 더해서 저장
                inTime.remove(carNum);
            }
        }
        
        // 1. 입차했는데 출차 안한 차가 있다면 23:59 출차로 계산
        // 주의 : 주차시간만 계산하고 굳이 map에서 제거할 필요가 없음
        // 오히려 ketSet()을 for문 돌리는 중 제거하면 ConcurrentModificationException 발생
        for(String carNum : inTime.keySet()) {
            int duration = toMin("23:59") - inTime.get(carNum);
            parkingTime.put(carNum, parkingTime.getOrDefault(carNum, 0) + duration);
        }
        
        int[] answer = new int[parkingTime.size()];
        int idx = 0;
        
        // 2. 주차시간에 따른 주차요금 계산
        for(String carNum : parkingTime.keySet()) {
            int time = parkingTime.get(carNum);
            int fee = fees[1]; // 기본요금으로 초기화
            
            if(time > fees[0]) { // 추가 요금 계산
                fee += (int) Math.ceil((double)(time - fees[0]) / fees[2]) * fees[3];
            }
            
            answer[idx++] = fee;
        }
        
        return answer;
    }
    
    public int toMin(String time) {
        String[] s = time.split(":");
        int h = Integer.parseInt(s[0]);
        int m = Integer.parseInt(s[1]);
        
        return h*60 + m;
    }
}
