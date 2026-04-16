import java.util.*;

class Solution {
    
    public class TotalPlay {
        String genre;
        int totalPlay;
        
        TotalPlay(String genre, int totalPlay) {
            this.genre = genre;
            this.totalPlay = totalPlay;
        }
    }
    
    public class Song {
        int index;
        int play;
        
        Song(int index, int play) {
            this.index = index;
            this.play = play;
        }
    }
    
    public List solution(String[] genres, int[] plays) {
        Map<String, Integer> map = new HashMap<>(); // 장르별 총 재생 횟수
        
        for(int i=0; i<genres.length; i++) {
            map.put(genres[i], map.getOrDefault(genres[i], 0) + plays[i]);
        }
        
        List<TotalPlay> list = new ArrayList<>();
        
        for(String genre : map.keySet()) {
            list.add(new TotalPlay(genre, map.get(genre)));
        }
        
        Collections.sort(list, (o1, o2) -> o2.totalPlay - o1.totalPlay);
        
        List<Integer> answerList = new ArrayList<>();
        
        for(TotalPlay tp : list) {
            List<Song> songList = new ArrayList<>(); // 장르별 노래의 재생 횟수 저장
            
            for(int i=0; i<genres.length; i++) {
                if(genres[i].equals(tp.genre)) {
                    songList.add(new Song(i, plays[i]));
                }
            }
            
            Collections.sort(songList, (o1, o2) -> o2.play - o1.play);
            
            if(songList.size() < 2) {
                answerList.add(songList.get(0).index);
            } else {
                for(int i=0; i<2; i++) {
                    answerList.add(songList.get(i).index);
                }
            }
        }
        
        return answerList;
    }
}