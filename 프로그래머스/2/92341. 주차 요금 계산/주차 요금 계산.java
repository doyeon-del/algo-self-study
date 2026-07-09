import java.util.*;

class Solution {
    public int[] solution(int[] fees, String[] records) {
        
        
        HashMap<String, Integer> inParking = new HashMap<>();
        HashMap<String, Integer> totalTime = new HashMap<>(); // 누적합이 뽀인트
        
        
        for (String record : records){
            
            int hour = Integer.parseInt(record.substring(0,2));
            int minute = Integer.parseInt(record.substring(3,5));
            String carNum = record.substring(6, 10);
            String inout = record.substring(11, 13);
            
            // 각 자동차의 in, out 정보를 어떻게 보관? Map에다가 넣어야 하나? 
            // 그리고 무조건 분 단위로 바꿔서 저장.
            
            int currentTime = hour * 60 + minute; 
            
            if (inout.equals("IN")){
                inParking.put(carNum, currentTime); // 입차시간 저장
                // totalTime.put(carNum, 0); // 0부터 시작.
                
            } else { // outtime
                // 기존 totalTime에서 out을 빼줘야 함. 
                int inTime = inParking.remove(carNum); // 해당 번호 차 제거 == 와 remove하면서 값을 바로 가져오는구나
                int parkingTime = currentTime - inTime;
                totalTime.put(carNum, totalTime.getOrDefault(carNum, 0) + parkingTime);
            }
 
        }
        
        // totalTimes 돌면서 주차 요금 계산만 해주면 됨. 
        
        // 주의 --- 출차된 내역이 없는 차들? 
       
            
        for (String key : inParking.keySet()){
                // 23:59에 출차된 것 ---- 23 * 60 + 59
                int inTime = inParking.get(key);
                int time = (23 * 60) + 59;
                int ptime = time - inTime;
                
                totalTime.put(key,totalTime.getOrDefault(key, 0) + ptime);
                
        }
            
        

        // 와 HashMap에서 그 키준 sort도 있었구나... Collections.sort 어케 쓰는건지 확실히 알아두자=====================
        List<String> keySet = new ArrayList<>(totalTime.keySet());

        // 키 값으로 오름차순 정렬 (작은 자동차)
        Collections.sort(keySet); // keySet은 작은 자동차 순으로 정렬된거임.

        // =======================
        
        int basicTime = fees[0];
        int basicFee = fees[1];
        int extraTime = fees[2];
        int extraFee = fees[3];
    
        int[] answer = new int[keySet.size()];
        
        for (int i=0; i<keySet.size(); i++){
            String carNum = keySet.get(i); // 이건 String값을 가져오는거임.
            int time = totalTime.get(carNum);
            
            int fee = basicFee; 
            
            if (time > basicTime){
                fee += ((time - basicTime + extraTime - 1) / extraTime) * extraFee;
                    
            }
            
            answer[i] = fee; 
        }
        
        
        
        return answer;
    }
}