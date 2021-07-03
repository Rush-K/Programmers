/**
 * Programmers - Sort Category
 * Problem Name : K번째 수  
 * Writed by Rush.K
 */

package Level1;

import java.util.PriorityQueue;

public class KthNumber {
	
    public static int[] solution(int[] array, int[][] commands) { // PriorityQueue를 이용 
        
    	PriorityQueue<Integer> tempArray;
    	int[] answer = new int[commands.length];
        int answerCount = 0;
        
        tempArray = new PriorityQueue<Integer>();
        
        for (int[] command : commands) {
        	for (int i = command[0] - 1; i < command[1]; i++) {
        		tempArray.add(array[i]);
        	}
        	
        	int count = 1;
        	while (count < command[2]) {
        		tempArray.poll();
        		count++;
        	}
        	answer[answerCount++] = tempArray.poll();
        	while (!tempArray.isEmpty()) tempArray.poll();
        }
        
        return answer;
        
    }
	public static void main(String[] args) {
		int[] array = {1, 5, 2, 6, 3, 7, 4};
		int[][] commands = {{2, 5, 3}, {4, 4, 1}, {1, 7, 3}};
		int[] result = solution(array,commands);
		for (int i : result) System.out.println(i + " ");
	}

}
