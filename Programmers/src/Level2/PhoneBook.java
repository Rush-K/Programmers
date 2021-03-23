package Level2;

import java.util.HashMap;

public class PhoneBook {

	public static HashMap<String, Integer> hashMap;
	
	public static boolean solution(String[] phone_book) {
		hashMap = new HashMap<String, Integer>();
		
		for (int i = 0; i < phone_book.length; i++) {
			for (int j = 1; j <= phone_book[i].length(); j++) {
				if (hashMap.get(phone_book[i].substring(0, j)) == null) {
					hashMap.put(phone_book[i].substring(0, j), 1);
				} else {
					hashMap.put(phone_book[i].substring(0, j), hashMap.get(phone_book[i].substring(0, j)) + 1);
				}
			}
		}
		
		for (int i = 0; i < phone_book.length; i++) {
			if (hashMap.get(phone_book[i]) > 1) return false;
		}
		
		return true;
	}
	
	public static void main(String[] args) {
		String[] phone_book = {"119", "97674223", "1195524421"};
		System.out.println(solution(phone_book));
	}

}
