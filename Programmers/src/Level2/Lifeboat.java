/**
 * Programmers - Greedy Category
 * Problem Name : 구명보트   
 * Writed by Rush.K
 */

package Level2;

import java.util.Arrays;

public class Lifeboat {

	public static int solution(int[] people, int limit) {
		int boat = 0;
		int leftIndex = 0;
		int rightIndex = people.length - 1;
		
		Arrays.sort(people); // 오름차순 정렬 
		
		while (leftIndex < rightIndex) { // 양 사이드의 수 합과 limit와 비교 
			int sum = people[leftIndex] + people[rightIndex];
			if (sum > limit) {
				boat++;
				rightIndex--;
			} else {
				boat++;
				rightIndex--;
				leftIndex++;
			}
			
			if (leftIndex == rightIndex) { // 인덱스가 같아질 경우 보트 하나 추가 
				boat++;
			}
		}
		return boat;
	}
	
	public static void main(String[] args) { // p.s. 단순하게 생각해보자! 
		int[] people = {40,40,40,40,50,60,70,80,90};
		int limit = 100;
		System.out.println(solution(people, limit));
	}
}
