/**
 * Programmers - Hash Category
 * Problem Name : 완주하지 못한 선수 
 * Writed by Rush.K
 */

package Level1;

import java.util.Hashtable;

public class UnfinishedPlayer {
    public static Hashtable<String, Integer> hashTable;

    public static String solution(String[] participant, String[] completion) {
    	hashTable = new Hashtable<String, Integer>(); // HashTable 선
    	String answer = "";
    	
    	for (String temp : participant) { // participant 요소를 키로 활용하여 HashTable에 put
    		if (hashTable.containsKey(temp)) { // 이미 존재하는 participant가 있을 경
    			int count = (int)hashTable.get(temp) + 1;
    			hashTable.put(temp, count);
    		} else { // 새로운 participant 추가 
        		hashTable.put(temp, 1);
    		}
    	}
    	
    	for (String temp : completion) { // completion 요소가 hashTable에 있으면 value 값 -1
    		hashTable.put(temp, (int)hashTable.get(temp) - 1);
    	}
    	
    	for (String temp : participant) { // value가 1인 Key를 찾
    		if ((int)hashTable.get(temp) == 1) {
    			answer = temp;
    			break;
    		}
    	}
    	
    	return answer;
    	
    } 
    
	public static void main(String[] args) {
		
		String[] participant = new String[4];
		participant[0] = "mislav";
		participant[1] = "stanko";
		participant[2] = "mislav";
		participant[3] = "ana";
		
		String[] completion = new String[3];
		completion[0] = "stanko";
		completion[1] = "ana";
		completion[2] = "mislav";
		
		String result = solution(participant, completion);
		System.out.println(result);
	}

}
