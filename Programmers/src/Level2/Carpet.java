/**
 * Programmers - Brute Force Category
 * Problem Name : 카펫   
 * Writed by Rush.K
 */

package Level2;

public class Carpet {
	public static int[] solution(int brown, int yellow) {
		int[] answer = new int[2];
		int total = brown + yellow;
		int rootTotal = (int) Math.sqrt(total);
		
		for (int i = 1; i <= rootTotal; i++) {
			if (total % i == 0) { // 공약수 
				int horizontal = total / i;
				int vertical = i;
				if ((horizontal - 2) * (vertical - 2) == yellow) { // 해당 가로세로가 일치하는지 확인 
					answer[0] = horizontal;
					answer[1] = vertical;
					break;
				}
			}
		}
		return answer;
	}
	public static void main(String[] args) {
		int brown = 24;
		int yellow = 24;
		int[] answer = solution(brown, yellow);
		for (int ans : answer) {
			System.out.print(ans + " ");
		}

	}

}
