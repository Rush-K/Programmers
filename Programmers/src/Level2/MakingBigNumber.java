package Level2;

public class MakingBigNumber {

	public static String solution(String number, int k) {
		String answer = "";
		int selectNum = number.length() - k;
		int index = 0;
		
		for (int i = selectNum; i > 0; i--) {
			int maxNum = 0;
			
			for (int j = index; j < number.length() - (i - 1); j++) {
				if (maxNum < Integer.parseInt(number.substring(j, j + 1))) {
					maxNum = Integer.parseInt(number.substring(j, j + 1));
					index = j + 1;
				} 
				if (maxNum == 9) break;
			}
			answer += String.valueOf(maxNum);
		}
		return answer;
	}
	
	public static void main(String[] args) {
		String number = "00000000000000100000";
		int k = 3;
		System.out.println(solution(number, k));
	}

}
