import java.util.*;

class Solution {
    public int[] solution(String[] operations) {
        PriorityQueue<Integer> minPq = new PriorityQueue<>(); // 최솟값용 (오름차순)
        PriorityQueue<Integer> maxPq = new PriorityQueue<>(Collections.reverseOrder()); // 최댓값용 (내림차순)

        for (String op : operations) {
            String[] parts = op.split(" ");
            String command = parts[0];
            int num = Integer.parseInt(parts[1]);

            if (command.equals("I")) {
                minPq.add(num);
                maxPq.add(num);
            } else if (command.equals("D")) {
                if (minPq.isEmpty()) continue; // 큐가 비어있으면 무시

                if (num == 1) {
                    // 최댓값 삭제
                    int max = maxPq.poll();
                    minPq.remove(max); // 다른 큐에서도 제거
                } else {
                    // 최솟값 삭제
                    int min = minPq.poll();
                    maxPq.remove(min); // 다른 큐에서도 제거
                }
            }
        }

        if (minPq.isEmpty()) {
            return new int[]{0, 0};
        } else {
            return new int[]{maxPq.peek(), minPq.peek()}; // [최댓값, 최솟값]
        }
    }
}