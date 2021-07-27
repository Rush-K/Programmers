/**
 * Programmers - DP Category
 * Problem Name : 정수 삼각형  
 * Writed by Rush.K
 */

package Level3;

public class IntegerTriangle {
	
	public static int solution(int[][] triangle) { // DP : 점화식 설정 
		int[][] answerTriangle = new int[triangle.length][triangle.length];
		int answer = 0;
		
		answerTriangle[0][0] = triangle[0][0];
		
		for (int i = 0; i < triangle.length - 1; i++) { // 각 자리 까지 합의 최댓값 저장 
			for (int j = 0; j < i + 1; j++) {
				answerTriangle[i + 1][j] = Math.max(answerTriangle[i + 1][j], answerTriangle[i][j] + triangle[i + 1][j]);
				answerTriangle[i + 1][j + 1] = Math.max(answerTriangle[i + 1][j + 1], answerTriangle[i][j] + triangle[i + 1][j + 1]);
			}
		}
		
		for (int i = 0; i < triangle.length; i++) // 결과 도출 
			answer = Math.max(answer, answerTriangle[triangle.length - 1][i]);
		
		return answer;
	}
	
	public static void main(String[] args) {
		int[][] triangle = {{7}, {3, 8}, {8, 1, 0}, {2, 7, 4, 4}, {4, 5, 2, 6, 5}};
		System.out.println(solution(triangle));
	}

}
