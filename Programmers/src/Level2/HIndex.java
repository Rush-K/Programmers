/**
 * Programmers - Sort Category
 * Problem Name : H-Index  
 * Writed by Rush.K
 */

package Level2;

import java.util.Arrays;

public class HIndex {
	public static int solution(int[] citations) {
		int answer = 0;
		Arrays.sort(citations); // 오름차순 정렬 
		
		if (citations[citations.length - 1] == 1) {
			return 1;
		}
		
		for (int j = 0; j <= citations[0]; j++) { // 첫번째 수일 경우 
			if (citations.length >= j) {
				answer = Math.max(answer, j);
			}
		}
		
		for (int i = 0; i < citations.length - 1; i++) { // 두번째 수부터 ~ 
			for (int j = citations[i]; j <= citations[i + 1]; j++) {
				if (j == citations[i]) {
					if (citations.length - i >= citations[i]) {
						answer = Math.max(answer, citations[i]);
					}
				} else {
					if (citations.length - (i + 1) >= j) {
						answer = Math.max(answer, j);
					}
				}
			}
		}
		return answer;
	}
	
	public static void main(String[] args) {
		int[] citations = {22, 42};
		System.out.println(solution(citations));

	}

}
