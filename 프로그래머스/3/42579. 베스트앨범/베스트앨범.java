import java.util.*;

class Music implements Comparable<Music>{
    
    int num;
    int value; 
    String genre;
    
    public Music(int num, int value, String genre){
        this.num = num;
        this.value = value;
        this.genre = genre;
    }
    
    @Override
    public int compareTo(Music o){
        if (this.value == o.value){
            return Integer.compare(this.num, o.num);
        }
        return Integer.compare(o.value, this.value);
    }
    
}

class Solution {
    public int[] solution(String[] genres, int[] plays) {
        
        int n = plays.length;
        List<Music> musicList = new ArrayList<>();
        
        for (int i=0; i<n; i++){
            
            String genre = genres[i];
            int play = plays[i];
            musicList.add(new Music(i, play, genre));
            
        }
        
        
        
        HashMap<String, Integer> genreCount = new HashMap<>();
        
        for (Music music : musicList){
            genreCount.put(music.genre, genreCount.getOrDefault(music.genre, 0) + music.value);
        }
        //------
        
        List<String> keys = new ArrayList<>(genreCount.keySet());
        
        keys.sort((String g1, String g2) -> Integer.compare(genreCount.get(g2), genreCount.get(g1)));
        
        
        List<Integer> answerList = new ArrayList<>();
        
        for (String g : keys){ // 정렬된 장르 기준으로 answerList에 집어 넣기 (1차 정렬)
            
            List<Music> flist = new ArrayList<>();
            
            for (Music m : musicList){
                if(m.genre.equals(g)){
                    flist.add(m);
                }
            }
            
            // 같은 장르 안에서의 정렬도 필요하기 때문에 2차 정렬
            Collections.sort(flist);
            
            // 현재 장르에서 상위 2개의 뮤직 아이디값만 넣어주기
            int count = 0; 
            for (Music m : flist){
                answerList.add(m.num);
                count++;
                if(count==2) break;
            }
            
            
        }
        
        int[] answer = new int[answerList.size()];
        
        for (int i=0; i<answerList.size(); i++){
            answer[i] = answerList.get(i);
        }
        
        
        return answer;
    }
}