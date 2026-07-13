import java.util.*;
class Truck {
    
    int pos; 
    int weight;
    
    public Truck(int pos, int weight){
        this.pos = pos;
        this.weight = weight;
    }
    
}
class Solution {
    public int solution(int bridge_length, int weight, int[] truck_weights) {
        
        Deque<Integer> truckQ = new ArrayDeque<>();
        
        for (int t : truck_weights){
            truckQ.add(t);
        }
        
        int time = 0;
        int currCount = 0; // 현재 다리 위에 있는 트럭 수 
        int currWeight = 0; // 현재 총 무게 
        
         Deque<Truck> bridgeQ = new ArrayDeque<>();
        
        while(!truckQ.isEmpty() || !bridgeQ.isEmpty()){
            
            time++;
            
            for (Truck t : bridgeQ){
                t.pos++;
            }
            
            if (!bridgeQ.isEmpty() && bridgeQ.peekFirst().pos >= bridge_length){
                Truck arrived = bridgeQ.poll();
                currWeight -= arrived.weight;
                currCount--;
            }
            
            
            // ============================
            if (!truckQ.isEmpty()){
                int nextWeight = truckQ.peek();
            
            if (currCount+1 <= bridge_length && currWeight+nextWeight <= weight){
                truckQ.poll();
                
                bridgeQ.add(new Truck(0, nextWeight)); // bridge 마지막에 트럭 넣기
                
                currCount++; // bridge 총 트럭수+1
                currWeight += nextWeight;  // bridg 총 무게수 + 현재 트럭 무게만큼
                
            }
                
            }
            
            
            
        }
        
        return time;
    }
}