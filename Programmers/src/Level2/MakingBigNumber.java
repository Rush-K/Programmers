/**
 * Programmers - Greedy Category
 * Problem Name : 큰 수 만들기  
 * Writed by Rush.K
 */

package Level2;

public class MakingBigNumber {

	public static String solution(String number, int k) {
		String answer = "";
		int selectNum = number.length() - k; // 뽑아내야할 수의 갯
		int index = 0;
		
		for (int i = selectNum; i > 0; i--) {
			int maxNum = 0;
			
			for (int j = index; j < number.length() - (i - 1); j++) { // 특정 범위 내에서 가장 큰 수 뽑기 
				if (maxNum < Integer.parseInt(number.substring(j, j + 1))) {
					maxNum = Integer.parseInt(number.substring(j, j + 1));
					index = j + 1;
				} 
				if (maxNum == 9) break; // 9보다 큰 자릿수는 없으므로, break
			}
			answer += String.valueOf(maxNum);
		}
		return answer; // 결과 반환 
	}
	
	public static void main(String[] args) {
		String number = "00000000000000100000";
		int k = 3;
		System.out.println(solution(number, k));
	}

}
