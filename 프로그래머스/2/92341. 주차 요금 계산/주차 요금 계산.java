import java.util.*;

// HashMap에 차번호가 없으면 (차번호, 입차시간) 저장
// 이미 차번호가 있고 OUT이면 주차시간 계산해서 다른 HashMap에 (차번호, 주차시간) 저장 + map에서 제거

class Solution {
    
    class ParkingFee {
        String carNum;
        int fee;
        
        ParkingFee(String carNum, int fee) {
            this.carNum = carNum;
            this.fee = fee;
        }
    }
    
    public int[] solution(int[] fees, String[] records) {
        Map<String, Integer> inTime = new HashMap<>();
        Map<String, Integer> parkingTime = new HashMap<>();
        
        for(int i=0; i<records.length; i++) {
            String[] record = records[i].split(" ");
            int time = toMin(record[0]);
            String carNum = record[1];
            
            // if(!inTime.containsKey(carNum) && records[2].equals("IN")) { // 입차하는 차의
            if(record[2].equals("IN")) {
                inTime.put(carNum, time); // (차번호, 입차시간) 저장
            } else { // 이미 주차했다면 출차
                int tmp = time - inTime.get(carNum); // 주차시간 계산
                parkingTime.put(carNum, parkingTime.getOrDefault(carNum, 0) + tmp); // 기존에 주차했던 차는 시간 더해서 저장
                inTime.remove(carNum);
            }
        }
        
        // 입차했는데 출차 안한 차가 있다면 23:59 출차로 계산
        for(Map.Entry<String, Integer> entry : inTime.entrySet()) {
            String carNum = entry.getKey();
            int time = entry.getValue();
            int tmp = toMin("23:59") - time; // 주차시간 계산
            
            parkingTime.put(carNum, parkingTime.getOrDefault(carNum, 0) + tmp); // 기존에 주차했던 차는 시간 더해서 저장
            // inTime.remove(carNum);
        }
        
        List<ParkingFee> list = new ArrayList<>();
        
        // 차별 주차시간 확인
        for(Map.Entry<String, Integer> entry : parkingTime.entrySet()) {
            String carNum = entry.getKey();
            int time = entry.getValue();
            // System.out.println(carNum + " / " + time);
            
            int fee = 0;
            if(time <= fees[0]) fee = fees[1];
            else {
                // int tmpp = (int) Math.ceil((double)(time - fees[0]) / fees[2]);
                // System.out.println("올림 : " + tmpp);
                fee = fees[1] + (int) Math.ceil((double)(time - fees[0]) / fees[2]) * fees[3];
            }
            // System.out.println(fee);
            
            list.add(new ParkingFee(carNum, fee));
        }
        
        // 차량번호 작은 순으로 정렬
        Collections.sort(list, (o1, o2) -> o1.carNum.compareTo(o2.carNum));
        
        int[] answer = new int[list.size()];
        for(int i=0; i<list.size(); i++) {
            // System.out.println(list.get(i).carNum + ", " + list.get(i).fee);
            answer[i] = list.get(i).fee;
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