/**
 * Programmers - Brute Force Category
 * Problem Name : 모의고사 
 * Writed by Rush.K
 */

package Level1;

import java.util.ArrayList;
import java.util.List;

public class MockExam {
	
	public static int[] solution(int[] answers) {
		int[][] personRule = {{1,2,3,4,5}, {2,1,2,3,2,4,2,5}, {3,3,1,1,2,2,4,4,5,5}}; // 답안 패턴 
		int[] person = new int[3];
		int max = 0;
		
		List<Integer> answer = new ArrayList<>();
		
		for (int i = 1; i < answers.length + 1; i++) { // 맞힌 개수 세기 
			if (answers[i - 1] == personRule[0][(i - 1) % personRule[0].length]) person[0]++;
			if (answers[i - 1] == personRule[1][(i - 1) % personRule[1].length]) person[1]++;
			if (answers[i - 1] == personRule[2][(i - 1) % personRule[2].length]) person[2]++;
		}
		
		for (int numOfCorrectAnswers : person) {
			max = Math.max(numOfCorrectAnswers, max);
		}
		
		for (int i = 0; i < person.length; i++) {
			if (max == person[i]) {
				answer.add(i + 1);
			}
		}
		
		return answer.stream().mapToInt(i -> i).toArray(); // 결과 반환 
	}
	
	public static void main(String[] args) {
		//int[] answers = {1,2,3,4,5};
		int[] answers = {1,3,2,4,2};
		
		int[] answer = solution(answers);
		for (int ans : answer) System.out.print(ans + " ");

	}

}
