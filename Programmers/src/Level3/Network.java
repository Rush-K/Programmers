/**
 * Programmers - DFS/BFS Category
 * Problem Name : 네트워크   
 * Writed by Rush.K
 */

package Level3;

public class Network {
	public static boolean[] computer;
	
	public static void findNetwork(int computerIndex, boolean[] visited, int[][] computers) { // DFS 사용 
		boolean connect = false;
		visited[computerIndex] = true;
		
		for (int i = 0; i < computers.length; i++) {
			if (visited[i] == false) {
				if (computers[computerIndex][i] == 1 && i != computerIndex) {
					findNetwork(i, visited, computers);
					connect = true;
				}	
			}
			if (i == computers.length - 1 && connect == false) { // DFS 탐색 종료 후 방문 노드 반영 
				for (int b = 0; b < visited.length; b++) {
					if (visited[b]) {
						computer[b] = true;
					}
				}
				return;
			}
		}
	}
	
	public static int solution(int n, int[][] computers) {
		int answer = 0;
		computer = new boolean[n];
		boolean[] visited = new boolean[n];
		
		for (int i = 0; i < n; i++) { // 네트워크 갯수 세기 
			if (computer[i] == false) {
				findNetwork(i, visited, computers);
				answer++;	
			}
		}
		
		return answer;
	}
	public static void main(String[] args) {
		int n = 3;
		int[][] computers = {{1,1,0},{1,1,1},{0,1,1}};
		System.out.println(solution(n, computers));
	}

}
