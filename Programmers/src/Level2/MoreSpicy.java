/**
 * Programmers - Heap Category
 * Problem Name : 더 맵게 
 * Writed by Rush.K
 */

package Level2;

import java.util.Comparator;
import java.util.PriorityQueue;

public class MoreSpicy {
	
	public static PriorityQueue<Integer> scovilleQueue; // 우선순위 큐 선언 
	
    public static int solution(int[] scoville, int K) {
        int answer = 0;
        int scovilleOne = 0;
        int scovilleTwo = 0;
        int newScoville = 0;
        
        scovilleQueue = new PriorityQueue<>(new Comparator<Integer>() { // 우선순위 큐 초기화 
        	@Override
			public int compare(Integer o1, Integer o2) { // 오름차순을 위한 Comparator
				int comparsion = o1 - o2;
				return comparsion;
			}
        });
        
        for (int i = 0; i < scoville.length; i++) scovilleQueue.add(scoville[i]); // 큐에 값 넣기 
        
        while (scovilleQueue.peek() < K) {
        	if (scovilleQueue.size() == 1) { // K 이하의 값이면서 큐에 값이 하나만 있을 경우 -1 반환 
        		return -1;
        	} else { // 값이 두개 이상일 경우, 재료 합성 진행 
            	scovilleOne = scovilleQueue.poll();
            	scovilleTwo = scovilleQueue.poll();
            	newScoville = scovilleOne + (scovilleTwo * 2);
            	scovilleQueue.add(newScoville);
            	answer++; // 진행 횟수 
        	}
        }
        
        return answer;
    }
    
	public static void main(String[] args) {
		int[] scoville = {1,0,1};
		int K = 7;
		int a = solution(scoville, K);
		System.out.println(a);
	}

}
