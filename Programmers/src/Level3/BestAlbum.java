/**
 * Programmers - Hash Category
 * Problem Name : 베스트앨범  
 * Writed by Rush.K
 */

package Level3;

import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class BestAlbum {
	public static HashMap<String, Integer> genreHashMap; // 장르당 재생횟수 저장을 위한 HashMap
	public static List<Map.Entry<String, Integer>> genreList; // 재생횟수가 가장 많은 순서로 장르가 저장된 List 
	public static HashMap<Integer, Integer> songHashMap; // 각 장르의 노래 당 재생횟수 저장을 위한 HashMap
	public static List<Map.Entry<Integer, Integer>> songList; // 재생횟수가 가장 많은 순서로 노래가 저장된 List
	public static List<Integer> indexList; // 최종 결과가 저장된 List 
	
	public static int[] solution(String[] genres, int[] plays) {
		int[] answer = {};
		int num = 0;
		indexList = new LinkedList<>(); // 초기값 설정 
		
		genreHashMap = new HashMap<String, Integer>();

		
		for (int i = 0; i < genres.length; i++) { // genreHashMap Update :: 각 장르당 재생횟수를 합산하여 저장 
			if (genreHashMap.get(genres[i]) == null) genreHashMap.put(genres[i], plays[i]);
			else genreHashMap.put(genres[i], genreHashMap.get(genres[i]) + plays[i]);
		}
		
		genreList = new LinkedList<>(genreHashMap.entrySet()); // genreHashMap 정보를 genreList로 옮김 
		
		Collections.sort(genreList, new Comparator<Map.Entry<String, Integer>>() { // 재생횟수가 많은 순으로 Sorting
            @Override
            public int compare(Map.Entry<String, Integer> o1, Map.Entry<String, Integer> o2) {
                int comparision = (o1.getValue() - o2.getValue()) * -1;
                return comparision == 0 ? o1.getKey().compareTo(o2.getKey()) : comparision;
            }
		});
		
		for (Map.Entry<String, Integer> item : genreList) { // 각 장르에 속하는 노래 상위 2개 추출 
			songHashMap = new HashMap<Integer, Integer>();
			
			for (int i = 0; i < genres.length; i++) { // 노래 재생횟수를 songHashMap에 저장 
				if (genres[i].equals(item.getKey())) {
					songHashMap.put(i, plays[i]);
				}
			}
			
			songList = new LinkedList<>(songHashMap.entrySet()); // songHashMap 정보를 songList로 옮김 
			
			Collections.sort(songList, new Comparator<Map.Entry<Integer, Integer>>() { // 재생횟수가 많은 순으로 Sorting
	            @Override
	            public int compare(Map.Entry<Integer, Integer> o1, Map.Entry<Integer, Integer> o2) {
	                int comparision = (o1.getValue() - o2.getValue()) * -1;
	                return comparision == 0 ? o1.getKey().compareTo(o2.getKey()) : comparision;
	            }
			});
			
			num = 0;
			for (Map.Entry<Integer, Integer> index : songList) { // 상위 2개 노래만 최종 결과 List에 추가 
				if (num != 2) {
					indexList.add(index.getKey());
					num++;
				} else {
					break;
				}
			}
		}
		
		answer = indexList.stream().mapToInt(i -> i).toArray(); // Convert List to int array
		
		return answer; // return result
	}
	public static void main(String[] args) {
		String[] genres = {"classic", "classic", "classic", "classic", "classic", "pop"};
		int[] plays = {500, 500, 600, 150, 800, 3000};
		
		int[] a = solution(genres, plays);
		
		for (int i = 0; i < a.length; i++) System.out.println(a[i]);
	}

}
