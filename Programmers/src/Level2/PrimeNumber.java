/**
 * Programmers - Brute Force Category
 * Problem Name : 소수 찾기  
 * Writed by Rush.K
 */

package Level2;

import java.util.HashMap;

public class PrimeNumber {
	public static HashMap<Integer, Boolean> numList; // 숫자와 그 숫자의 소수 여부를 저장하는 HashMap
	
	public static boolean isPrimeNumber(int num) { // 소수 판별 : 에라토스테네스의 체 
		boolean[] numbers = new boolean[num + 1];
		for (int i = 2; i < numbers.length; i++) numbers[i] = true;
		
		int root = (int) Math.sqrt(num);
		
		for (int i = 2; i <= root; i++) {
			if (numbers[i] == true) {
				for (int j = i; i * j <= num; j++) {
					numbers[i * j] = false;
				}
			}
		}
		
		if (numbers[num] == true) {
			return true;
		} else {
			return false;
		}
	}
	
	public static void permutation(int[] nums, int depth, int n, int r) { // 순열을 HashMap에 저장 
		if (depth == r) {
			String num = ""; // 순열 결과 
			for (int i = 0; i < r; i++) num += String.valueOf(nums[i]);
			if (numList.get(Integer.parseInt(num)) == null) {
				numList.put(Integer.parseInt(num), isPrimeNumber(Integer.parseInt(num)));
			}
		}
		
		for (int i = depth; i < n; i++) {
			swap(nums, depth, i);
			permutation(nums, depth + 1, n, r);
			swap(nums, depth, i);
		}
	}
	
	public static void swap(int[] nums, int depth, int i) {
		int temp = nums[depth];
		nums[depth] = nums[i];
		nums[i] = temp;
	}
	
	public static int solution(String numbers) {
		int answer = 0;
		int[] convertedNumbers = new int[numbers.length()];
		
		numList = new HashMap<Integer, Boolean>();

		for (int i = 0; i < numbers.length(); i++) convertedNumbers[i] = Integer.parseInt(numbers.substring(i, i + 1));
		
		for (int i = 1; i <= convertedNumbers.length; i++) { // 만들 수 있는 순열 전부 HashMap 저장 
			permutation(convertedNumbers, 0, convertedNumbers.length, i);
		}
		
		for (int key : numList.keySet()) { // 소수 갯수 세기 
			if (numList.get(key) == true) {
				answer++;
			}
		}
		return answer;
	}
	
	public static void main(String[] args) {
		String numbers = "17";
		System.out.println(solution(numbers));
	}

}
