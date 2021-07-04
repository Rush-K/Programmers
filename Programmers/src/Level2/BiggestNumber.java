/**
 * Programmers - Sort Category
 * Problem Name : 가장 큰 수 
 * Writed by Rush.K
 */

package Level2;

import java.util.Comparator;
import java.util.PriorityQueue;

public class BiggestNumber {
	public static PriorityQueue<String> priorityQueue;

    public static String solution(int[] numbers) {
        String answer = "";
        int zeroCount = 0; // 모든 입력 수가 0일 경우를 판단하기 위한 변수 
        
        priorityQueue = new PriorityQueue<String>(new Comparator<String>() { // compare 메소드에 따른 정렬 
			@Override
			public int compare(String o1, String o2) { // compare 메소드 overriding 
				String o1Pluso2 = o1 + o2;
				String o2Pluso1 = o2 + o1;
				
				return -o1Pluso2.compareTo(o2Pluso1);
			}
        	
        });
        
        for (int number : numbers) {
        	if (number == 0) zeroCount++;
        }
        
        if (zeroCount == numbers.length) return "0";
        
        for (int number : numbers) {
        	priorityQueue.add(String.valueOf(number));
        }
        
        while (!priorityQueue.isEmpty()) {
        	answer += priorityQueue.poll();
        }
        
       
        return answer;
    }

	public static void main(String[] args) {
		//int[] numbers = {6, 10, 2};
		//int[] numbers = {3303, 330};
		int[] numbers = {40, 405};
		//int[] numbers = {0,0,0,0,0};
		//int[] numbers = {90,908,89,898,10,101,1,8,9};
		System.out.println(solution(numbers));
	}
}
