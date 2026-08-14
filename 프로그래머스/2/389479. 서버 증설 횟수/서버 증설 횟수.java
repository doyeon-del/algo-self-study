class Solution {
    public int solution(int[] players, int m, int k) {
        
        
        int activeServers = 0;
        int[] expiredAt = new int[24+k]; // 23 + k 범위를 커버하기 위해
        int total = 0; 
        
        for (int t = 0; t < 24; t++) {
            // 1. 만료된 서버 반납
            activeServers -= expiredAt[t];

            // 2. 필요 증설 서버 계산
            int requiredServers = players[t] / m;

            // 3. 부족분 추가 증설
            if (requiredServers > activeServers) {
                int needNew = requiredServers - activeServers;
                total += needNew;
                activeServers += needNew;
                expiredAt[t + k] += needNew; // k시간 후 만료 처리
            }
        }
        
        
        return total;
    }
}