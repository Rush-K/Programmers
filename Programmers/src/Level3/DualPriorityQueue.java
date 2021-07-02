/**
 * Programmers - Hash Category
 * Problem Name : 이중우선순위큐  
 * Writed by Rush.K
 */

package Level3;

import java.util.Comparator;
import java.util.PriorityQueue;

public class DualPriorityQueue {
	public static PriorityQueue<Integer> ascendingPriorityQueue; // 오름차순 우선순위 큐 
	public static PriorityQueue<Integer> descendingPriorityQueue; // 내림차순 우선순위 큐 
	
	public static int[] solution(String[] operations) {
		int[] answer = new int[2];
		
		ascendingPriorityQueue = new PriorityQueue<Integer>(new Comparator<Integer>() { // 오름차순 Comparator 사용 
			@Override
			public int compare(Integer o1, Integer o2) {
				return (int) o1 > (int) o2 ? 1 : -1;
			}
		});
		
		descendingPriorityQueue = new PriorityQueue<Integer>(new Comparator<Integer>() { // 내림차순 Comparator 사용 
			@Override
			public int compare(Integer o1, Integer o2) {
				return (int) o1 > (int) o2 ? -1 : 1;
			}
			
		});
		
		for (String operation : operations) { // 명령어 반영하기 
			switch (operation.split(" ")[0]) {
				case "I" : // I 명령어 : 추가 
					ascendingPriorityQueue.add(Integer.parseInt(operation.split(" ")[1]));
					continue;
				case "D" : // D 명령어 : 삭제 
					if (operation.split(" ")[1].contains("-")) { // 최솟값 삭제 
						ascendingPriorityQueue.poll();
					} else { // 최댓값 삭제 
						while (!ascendingPriorityQueue.isEmpty()) {
							descendingPriorityQueue.add(ascendingPriorityQueue.poll());
						}
						
						descendingPriorityQueue.poll();
						
						while (!descendingPriorityQueue.isEmpty()) {
							ascendingPriorityQueue.add(descendingPriorityQueue.poll());
						}
					}
					continue;
				default :
					continue;
			}
		}
		
		if (ascendingPriorityQueue.size() == 0) { // 큐에 남은 수가 없을 경우 
			answer[0] = 0;
			answer[1] = 0;
		} else if (ascendingPriorityQueue.size() == 1) { // 큐에 수가 하나일 경우 
			answer[0] = ascendingPriorityQueue.poll();
			answer[1] = answer[0];
		} else { // 그 외 경우 
			answer[1] = ascendingPriorityQueue.poll();
			while (ascendingPriorityQueue.size() > 1) {
				ascendingPriorityQueue.poll();
			}
			answer[0] = ascendingPriorityQueue.poll();
		}
		
		return answer; // 결과 반환 
	}
	public static void main(String[] args) {
		//String[] operations = {"I 16", "D 1"};
		String[] operations = {"I 7","I 5","I -5","D -1"};
		int[] answer = solution(operations);
		for (int i : answer) System.out.println(i);
	}

}
