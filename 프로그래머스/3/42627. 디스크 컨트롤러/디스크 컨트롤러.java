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
        
        PriorityQueue<Task> pq = new PriorityQueue<>((a,b) -> 
         a.len == b.len ? 
        (a.req == b.req ? Integer.compare(a.num, b.num) : Integer.compare(a.req, b.req)) :
                                                     Integer.compare(a.len, b.len));
        
        Arrays.sort(jobs, (a,b) -> Integer.compare(a[0], b[0])); // 요청 시간이 빠른 것부터 나열
        int totalTime = 0;
        int taskCount = 0; 
        int jobIdx = 0;
        int curTime = 0;
        
        while (taskCount < jobs.length){
            
                        while(jobIdx < jobs.length && jobs[jobIdx][0] <= curTime){

                pq.add(new Task(jobIdx, jobs[jobIdx][0], jobs[jobIdx][1]));
                jobIdx++;
            }
            
            if (pq.isEmpty()){
                curTime = jobs[jobIdx][0];
            } else {
                Task cur = pq.poll();
                taskCount++;
                curTime += cur.len;
                totalTime += (curTime - cur.req);
            }
            
        }
        
                return totalTime / taskCount;
    }
}