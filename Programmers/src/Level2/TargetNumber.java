/**
 * Programmers - DFS/BFS Category
 * Problem Name : 타겟 넘버    
 * Writed by Rush.K
 */

package Level2;

public class TargetNumber {
	static int[] numbers;
	static int answer;
	
	public static void dfs(int sum, int target, int index, int n) {	
		if (index == n) {
			if (sum == target) answer++;
			return;
		}

		dfs(sum - numbers[index], target, index + 1, n); // - 연산 
		dfs(sum + numbers[index], target, index + 1, n); // + 연산 
	}
	
	public static int solution(int[] _numbers, int target) { // 간단히 dfs 활용 
		answer = 0;
		numbers = new int[_numbers.length];
		
		for (int i = 0; i < numbers.length; i++) numbers[i] = _numbers[i];
		
		dfs(0, target, 0, numbers.length);
		
		return answer;
	}
	public static void main(String[] args) {
		int[] nums = {1,1,1,1,1};
		int target = 3;
		System.out.println(solution(nums, target));
	}

}
