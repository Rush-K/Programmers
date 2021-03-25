/**
 * Programmers - Hash Category
 * Problem Name : 위장  
 * Writed by Rush.K
 */

package Level2;

import java.util.HashMap;

public class Camouflage {
	public static HashMap<String, Integer> hashMap; // HashMap 선언 
	
	public static int solution(String[][] clothes) {
		hashMap = new HashMap<String, Integer>(); // key : String, value : Integer
		
		int answer = 1;
		
		for (int i = 0; i < 30; i++) { // 30개의 옷까지 가능 
			try { // clothes 가 존재할 경우 
				if (hashMap.get(clothes[i][1]) == null) { // 입력된 clothes가 아닐 경우 
					hashMap.put(clothes[i][1], 1);
				} else { // 이미 입력된 clothes인 경우 
					hashMap.put(clothes[i][1], hashMap.get(clothes[i][1]) + 1);
				}	
			} catch (Exception e) { // clothes 가 존재하지 않을 경우 
				break; // 반복문 종료 
			}
		}
		
		for (String key : hashMap.keySet()) { // hash value를 전부 곱함 
			answer = answer * (hashMap.get(key) + 1);
		}
		
		return (answer - 1); // 아무것도 안 입은 경우의 수 1개를 빼줌 
	}
	public static void main(String[] args) {
		String[][] clothes = {{"yellowhat", "headgear"}, {"bluesunglasses", "eyewear"}, {"green_turban", "headgear"}};
		System.out.println(solution(clothes));
	}
}
