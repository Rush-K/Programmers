/**
 * Programmers - DFS/BFS Category
 * Problem Name : 단어 변환    
 * Writed by Rush.K
 */

package Level3;

import java.util.LinkedList;
import java.util.Queue;

class Word { // 단어 클래스 
	String word;
	boolean[] visited;
	int depth;

	public Word(String _word, int n) {
		word = _word;
		depth = 0;
		visited = new boolean[n];
	}
	
	public void copyVisited(boolean[] _visited) {
		for (int i = 0; i < visited.length; i++) {
			visited[i] = _visited[i];
		}
	}
}

public class WordConversion {
	
	public static int solution(String begin, String target, String[] words) { // 큐를 이용한 BFS 알고리즘 
		Queue<Word> q = new LinkedList<Word>();
		Word beginWord = new Word(begin, words.length);
		Word tempWord = new Word("", words.length);
		int matchCount = 0;
		
		q.add(beginWord);
		
		while (!q.isEmpty()) {
			beginWord = q.poll();
			
			for (int i = 0; i < words.length; i++) {
				matchCount = 0;
				if (beginWord.visited[i] == false) { // 방문했던 단어가 아닐 경우 실행 
					
					for (int j = 0; j < beginWord.word.length(); j++) { // 알파벳 변경 가능 단어 찾기 
						if (matchCount > 1) break;
						if (!words[i].substring(j, j + 1).equals(beginWord.word.substring(j, j + 1))) matchCount++;
					}
					
					if (matchCount == 1) { // 변경 가능 단어 큐에 삽입 
						tempWord = new Word(words[i], words.length);
						tempWord.copyVisited(beginWord.visited);
						tempWord.visited[i] = true;
						tempWord.depth = beginWord.depth + 1;
						q.add(tempWord);
					}
					
					if (beginWord.word.equals(target)) return beginWord.depth; // 타겟 단어에 도달 
				}
			}
		}
		
		return 0;
	}
	
	public static void main(String[] args) {
		String begin = "hit";
		String target = "cog";
		String[] words = {"hot", "dot", "dog", "lot", "log", "cog"};
		System.out.println(solution(begin,target,words));
	}
}
