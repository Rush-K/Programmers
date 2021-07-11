/**
 * Programmers - Greedy Category
 * Problem Name : 조이스틱    
 * Writed by Rush.K
 */

package Level2;

public class Joystick {
	public static int solution(String name) {
		int answer = 0;
		String keyword ="";
		int minPath = name.length() - 1;
		
		for (int i = 0; i < name.length(); i++) keyword += "A";
		
		for (int i = 0; i < name.length(); i++) {
			answer += Math.min((int) name.substring(i, i + 1).toCharArray()[0] - (int) keyword.substring(i, i + 1).toCharArray()[0]
					, 91 - (int) name.substring(i, i + 1).toCharArray()[0]); // 상하 횟수 업데이트 
			
			int index = i + 1;
			
			while (index < name.length() && name.substring(index, index + 1).equals("A")) index++;
			minPath = Math.min(minPath, (2 * i) + name.length() - index); // 핵심, 좌우 횟수 업데이트  	
		}
		
		answer += minPath;
		
		return answer;
	}
	public static void main(String[] args) {
		//System.out.println(solution("JANAANA"));
		String str = "ABAAAABAAAABB";
		System.out.println(solution(str));
		//System.out.println(str.split("A").length);
	}

}
