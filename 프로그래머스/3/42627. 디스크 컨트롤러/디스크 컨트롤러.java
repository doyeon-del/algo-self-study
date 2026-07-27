import java.util.*;

class Task {
    
    int num;
    int req;
    int len;
    
    public Task(int num, int req, int len){
        this.num = num;
        this.req = req;
        this.len = len;
    }
    
}

class Solution {
    public int solution(int[][] jobs) {
        
        PriorityQueue<Task> pq = new PriorityQueue<>((a,b) -> (
            a.len == b.len ?
            (a.req == b.req ? Integer.compare(a.num, b.num) : Integer.compare(a.req, b.req)) :
            Integer.compare(a.len, b.len)
        ));
        
        Arrays.sort(jobs, (a,b) -> Integer.compare(a[0], b[0]));
        
        int totalTime = 0; // 전체 반환 시간
        int curTime = 0; // 현재 시간
        
        int taskCount = 0;
        int jobIdx = 0;
        
        while(taskCount < jobs.length){
            
            while(jobIdx < jobs.length && jobs[jobIdx][0] <= curTime){
                pq.add(new Task(jobIdx, jobs[jobIdx][0], jobs[jobIdx][1]));
                jobIdx++;
                
                
            }
            
            
            if (pq.isEmpty()){
                curTime = jobs[jobIdx][0];
            } 
            
            else { // 현재 작업 처리 
                    Task cur = pq.poll();
                    curTime += cur.len;
                    totalTime += (curTime - cur.req);
                    taskCount++;
                    
                    
            }
        }
        
        
        
        return totalTime / jobs.length;
    }
}