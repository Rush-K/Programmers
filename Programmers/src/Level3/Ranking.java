/**
 * Programmers - Graph Category
 * Problem Name : 순위     
 * Writed by Rush.K
 */

package Level3;

public class Ranking {

	public static int solution(int n, int[][] results) { // 플로이드 알고리즘 응용 
		int answer = 0;
		int[][] rankingMap = new int[n + 1][n + 1];
		boolean isGuessed = false;
		
		for (int i = 1; i <= n; i++) {
			for (int j = 1; j <= n; j++) {
				if (i == j) rankingMap[i][j] = 0;
				else rankingMap[i][j] = 2;
			}
		} // 2로 map 초기화 

		for (int[] result : results) {
			rankingMap[result[0]][result[1]] = 1; // result[0] win 일 경우 1 
			rankingMap[result[1]][result[0]] = 0; // result[0] lose 일 경우 0 
		}
		
		for (int i = 1; i <= n; i++) { // 순위는 단방향으로 흐름 
			for (int j = 1; j <= n; j++) {
				for (int k = 1; k <= n; k++) {
					if (rankingMap[j][k] == 2) { 
						if (rankingMap[j][i] == 1 && rankingMap[i][k] == 1) rankingMap[j][k] = 1;
						if (rankingMap[j][i] == 0 && rankingMap[i][k] == 0) rankingMap[j][k] = 0;
					}
				}
			}
		}
		
		for (int i = 1; i <= n; i++) { // 간선을 아직 모르는 경우는 제거 
			isGuessed = true;
			for (int j = 1; j <= n; j++) {
				if (rankingMap[i][j] == 2) {
					isGuessed = false;
					break;
				}
			}
			
			
			if (isGuessed) {
				answer++;
			}
		}
		
		return answer;
	}
	public static void main(String[] args) {
		int n = 5;
		int[][] results = {{4,3},{4,2},{3,2},{1,2},{2,5}};
		System.out.println(solution(n, results));

	}

}
