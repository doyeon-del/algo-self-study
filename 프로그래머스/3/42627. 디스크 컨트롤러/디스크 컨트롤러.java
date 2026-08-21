import java.util.*;

class Task {
    
    int num;
    int request;
    int len; // 작업의 소요 시간 
    
    public Task(int num, int request, int len){
        this.num = num;
        this.request = request;
        this.len = len;
    }
}


class Solution {
    public int solution(int[][] jobs) {
        int answer = 0;
        // 대기큐를 우선순위 큐로 만들기
        
        PriorityQueue<Task> pq = new PriorityQueue<>(
            // 세가지 우선 순위 조건 바로 넣어주기 
            (a, b) -> (
                a.len != b.len ? Integer.compare(a.len, b.len) : (
                a.request != b.request ? Integer.compare(a.request, b.request) : (
                Integer.compare(a.num, b.num)))
            )
        );
        
        
        // 요청되는 시간 기준 오름차순으로 정렬
        Arrays.sort(jobs, (a,b) -> Integer.compare(a[0], b[0]));
        

        int totalTime = 0 ;
        int curTime = 0; 
        int jobIdx = 0;
        int count = 0; 
        
        while (count < jobs.length){
            
            while (jobIdx < jobs.length && jobs[jobIdx][0] <= curTime){
                pq.add(new Task(jobIdx, jobs[jobIdx][0], jobs[jobIdx][1]));
                jobIdx++;
            }
            
            if (pq.isEmpty()){
                curTime = jobs[jobIdx][0]; // 현재 작업 요청 시간으로 이동 
            } else {
                Task task = pq.poll();
                curTime += task.len;
                totalTime += curTime - task.request;
                count++;
                
            }
        }
        
        
        return totalTime / jobs.length;
        
        
    }
}