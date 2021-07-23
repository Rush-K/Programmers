/**
 * Programmers - DP Category
 * Problem Name : N으로 표현      
 * Writed by Rush.K
 */

package Level3;

import java.util.HashSet; // HashSet : 중복 제거되는 배열로 사용 

public class Nexpression {
	
	static HashSet<Integer>[] countList;
	
	public static int solution(int N, int number) {
		// Let, N을 k번 사용해서 만들 수 있는 수 => (N, k) = Sigma (n = 1 to k - 1) [(N, n) operation (N, k - n)]
		
		countList = new HashSet[9];
		for (int i = 1; i < countList.length; i++) countList[i] = new HashSet<Integer>();
		
		countList[1].add(N); // 1번 배열 초기화 
		
		if (number == N) return 1;
		
		for (int i = 2; i <= 8; i++) { // 2번 배열부터 만들 수 있는 수 저장 
			String sequenceNum = "";
			for (int j = 1; j <= i; j++) sequenceNum = sequenceNum + String.valueOf(N);
			countList[i].add(Integer.parseInt(sequenceNum)); // 연속되는 수 저장 
			
			for (int j = 1; j < i; j++) { // 사칙연산 수 저장 
				for (Integer num : countList[j]) {
					for (Integer num_two : countList[i - j]) {
						countList[i].add(num + num_two);
						countList[i].add(num * num_two);
						countList[i].add(num - num_two);
						if (num_two != 0) countList[i].add(num / num_two);
					}
				}
			}
			
			for (Integer num : countList[i]) { // 찾는 수가 있으면 결과 반환 
				if (num == number) return i;
			}
		}
		
		return -1; // 8 을 넘을 시, -1 반환 
	}
	
	public static void main(String[] args) {
		int number = 5;
		int N = 5;
		System.out.println(solution(N,number));
	}

}
