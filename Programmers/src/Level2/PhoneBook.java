/**
 * Programmers - Hash Category
 * Problem Name : 전화번호 목록 
 * Writed by Rush.K
 */

package Level2;

import java.util.HashMap;

public class PhoneBook {

	public static HashMap<String, Integer> hashMap; // hashMap 선언 
	
	public static boolean solution(String[] phone_book) {
		hashMap = new HashMap<String, Integer>();
		
		for (int i = 0; i < phone_book.length; i++) { // ex) "119" -> "1", "11", "119" 를 HashMap에 삽입 
			for (int j = 1; j <= phone_book[i].length(); j++) {
				if (hashMap.get(phone_book[i].substring(0, j)) == null) { // HashMap에 존재하지 않는 값일 경우 
					hashMap.put(phone_book[i].substring(0, j), 1);
				} else { // HashMap에 이미 존재하는 경우 
					hashMap.put(phone_book[i].substring(0, j), hashMap.get(phone_book[i].substring(0, j)) + 1);
				}
			}
		}
		
		for (int i = 0; i < phone_book.length; i++) { // Hash 값이 2 이상일 경우 접두어 존재 
			if (hashMap.get(phone_book[i]) > 1) return false;
		}
		
		return true;
	}
	
	public static void main(String[] args) {
		String[] phone_book = {"119", "97674223", "1195524421"};
		System.out.println(solution(phone_book));
	}

}
