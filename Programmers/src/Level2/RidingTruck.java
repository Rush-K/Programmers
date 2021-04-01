/**
 * Programmers - Stack/Queue Category
 * Problem Name : 다리를 지나는 트럭  
 * Writed by Rush.K
 */

package Level2;

import java.util.LinkedList;
import java.util.Queue;

class Truck { // 트럭 클래스 : 트럭의 정보를 갖고 있음 
	int weight; // 트럭의 무게 
	int ridingTime; // 트럭의 운행 시간 
	
	public Truck() { // 생성자 
		weight = 0;
		ridingTime = 0;
	}
	
	public Truck(int _weight, int _ridingTime) { // 생성자 2 
		weight = _weight;
		ridingTime = _ridingTime;
	}
}

public class RidingTruck {
	public static Queue<Integer> truckList; // 다리를 지나야 하는 트럭들의 대기 큐 
	public static Queue<Truck> bridge; // 다리를 지나고 있는 트럭들의 큐 
	public static Queue<Truck> updateBridge; // 다리 큐를 업데이트하기 위한 큐 
	
	public static int solution(int bridge_length, int weight, int[] truck_weights) {
		
		Truck truckInBridge = new Truck(); // 다리 큐에 넣을 트럭 초기화 
		int answer = 0;
		int truck = 0;
		int bridgeTotalWeight = 0; // 다리를 지나고 있는 트럭들의 무게 합 
		
		
		truckList = new LinkedList<Integer>();
		bridge = new LinkedList<Truck>();
		updateBridge = new LinkedList<Truck>();
		
		for (int item : truck_weights) truckList.add(item);
		
		truckInBridge = new Truck(truckList.poll(), 1); // 첫 트럭 다리 큐에 넣어주기 
		bridge.add(truckInBridge);
		answer++;
		
		while (!bridge.isEmpty()) { // 다리 큐에 트럭이 한 대도 없을 때 까지 반복 
			bridgeTotalWeight = 0;
			
			while(!bridge.isEmpty()) {
				truckInBridge = bridge.poll();
				if (truckInBridge.ridingTime != bridge_length) { // 다리를 건너지 못한 트럭 updateBridge 큐로 이동 
					truckInBridge.ridingTime = truckInBridge.ridingTime + 1;
					bridgeTotalWeight += truckInBridge.weight; // 다리 위 트럭들의 무게 합 구하기 
					updateBridge.add(truckInBridge);
				}
			}
			
			if (truckList.peek() != null) { // 다리 중량에 맞는 트럭일 경우 updateBridge 큐에 삽입 
				if (bridgeTotalWeight + truckList.peek() <= weight) {
					truck = truckList.poll();
					Truck entryTruck = new Truck(truck, 1);
					updateBridge.add(entryTruck);
				}
			}
			
			while(!updateBridge.isEmpty()) { // updateBridge 큐의 트럭들을 다시 Bridge 큐로 이동시킴 
				truckInBridge = updateBridge.poll();
				bridge.add(truckInBridge);
			}
			answer = answer + 1;
		}
		
		
		return answer;
	}
	public static void main(String[] args) {
		int[] truck_weights = {10};
		int weight = 100;
		int bridge_length = 100;
		System.out.println(solution(bridge_length, weight, truck_weights));
	}

}
