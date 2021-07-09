/**
 * Programmers - Greedy Category
 * Problem Name : 체육복   
 * Writed by Rush.K
 */

package Level1;

public class GymSuit {
	public static int solution(int n, int[] lost, int[] reserve) {
		int answer = 0;
		int[] student = new int[n + 2]; // 1번째 학생과 마지막 학생 고려 
		
		for (int i = 1; i < student.length - 1; i++) student[i] = 1; // 모두 한 벌로 초기화 
		
		for (int i = 0; i < reserve.length; i++) student[reserve[i]] = 2; // 여벌 학생 반영 
		
		for (int i = 0; i < lost.length; i++) { // 체육복 도난당한 학생 반영 
			if (student[lost[i]] == 2) {
				student[lost[i]]--;
			} else {
				student[lost[i]] = 0;
			}
		}
		
		for (int i = 1; i < student.length - 1; i++) { // 빌려주기 반영 
			if (student[i] == 0) {
				if (student[i - 1] == 2) {
					student[i - 1]--;
					student[i]++;
					continue;
				} else if (student[i + 1] == 2) {
					student[i + 1]--;
					student[i]++;
					continue;
				}
			}
		}
		
		for (int s : student) { // 체육복 존재 학생 파악 
			if (s > 0) {
				answer++;
			}
		}
		
		return answer; // 결과 반환 
	}
	public static void main(String[] args) {
		int n = 5;
		int[] lost = {2,4};
		int[] reserve = {1,3,5};
		System.out.println(solution(n,lost,reserve));

	}

}
