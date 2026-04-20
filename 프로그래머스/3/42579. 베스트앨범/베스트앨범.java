import java.util.*;

class Solution {
    static class Song {
        String genre;
        int playCount;
        int index;
        
        Song (String genre, int playCount, int index) {
            this.genre = genre;
            this.playCount = playCount;
            this.index = index;
        }
    }
    public int[] solution(String[] genres, int[] plays) {
        Map<String, Integer> totalPlayCountByGenre = new HashMap<>();
        Map<String, List<Song>> songsByGenre = new HashMap<>();
        
        for (int index = 0; index < genres.length; index++){
            String genre = genres[index];
            int playCount = plays[index];
            
            totalPlayCountByGenre.put(
                genre,
                totalPlayCountByGenre.getOrDefault(genre, 0) + playCount
            );
            
            songsByGenre.putIfAbsent(genre, new ArrayList<>());
            songsByGenre.get(genre).add(new Song(genre, playCount, index));
        }
        
        List<String> genreOrder = new ArrayList<>(totalPlayCountByGenre.keySet());
        genreOrder.sort((genre1, genre2) -> 
                       totalPlayCountByGenre.get(genre2) - totalPlayCountByGenre.get(genre1)
                       );
        
        List<Integer> result = new ArrayList<>();
        
        for(String genre : genreOrder) {
            List<Song> songs = songsByGenre.get(genre);
            
            songs.sort((song1, song2) -> {
                if( song1.playCount == song2.playCount){
                    return song1.index - song2.index;
                }
                return song2.playCount - song1.playCount;
            });
            
            result.add(songs.get(0).index);
            
            if(songs.size() >= 2){
                result.add(songs.get(1).index);
            }
        }
            
        return result.stream().mapToInt(Integer::intValue).toArray();
    }
}