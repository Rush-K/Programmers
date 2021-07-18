/**
 * Programmers - Graph Category
 * Problem Name : 가장 먼 노드   
 * Writed by Rush.K
 */

package Level3;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

class Node { // 정점 클래스 
	public int number; // 해당 정점 번호 
	public int depth; // 해당 정점의 깊이 
	public boolean[] visited; // 해당 정점에 이르기까지 방문한 정점 기록 
	
	public Node(int n, int _number) {
		number = _number;
		depth = 0;
		visited = new boolean[n];
		visited[number] = true;
	}
	
	public Node(Node _node) {
		number = _node.number;
		depth = _node.depth;
		visited = new boolean[_node.visited.length];
		for (int i = 0; i < visited.length; i++) visited[i] = _node.visited[i];
	}
	
	public void update(int _number, int _depth, int _index) {
		number = _number;
		depth = _depth;
		visited[_index] = true;
	}
}

class Vertax { // 경로(간선) 클래스 
	public int start; // 시작 정점 
	public int end; // 끝 정점 
	
	public Vertax(int _start, int _end) {
		start = _start;
		end = _end;
	}
}

public class farthestNode {
	
	public static Queue<Node> q; // BFS를 위한 Queue 선언 
	
	public static int solution(int n, int[][] edge) {
		ArrayList<Vertax> map = new ArrayList<Vertax>();
		Vertax vertax;
		
		int[] nodeList = new int[n + 1];
		int max = 0;
		int answer = 0;
        boolean qExists = false;
		
		for (int i = 0; i < edge.length; i++) {
			vertax = new Vertax(edge[i][0], edge[i][1]);
			map.add(vertax);
			vertax = new Vertax(edge[i][1], edge[i][0]);
			map.add(vertax);
		}

		for (int i = 1; i < n + 1; i++) nodeList[i] = Integer.MAX_VALUE;
		
		q = new LinkedList<Node>();
		// 위 까지, 필요한 변수들 초기화 
		Node node = new Node(n + 1, 1);
		q.add(node);
		// 첫 시작 정점인 1 노드 큐에 삽입 
		while (!q.isEmpty()) { // BFS 실시 
			Node temp = q.poll();
			for (Vertax v : map) {
				
				if (v.start == temp.number) {
					if (temp.visited[v.end] == false && nodeList[v.end] == Integer.MAX_VALUE) { // 이미 업데이트된 정점은 다시 탐색할 필요가 없음 
						nodeList[v.end] = temp.depth + 1;
						node = new Node(temp);
						node.update(v.end, temp.depth + 1, v.end);
						q.add(node);
					}
				}
				
			}
		}
		
		for (int i = 2; i < n + 1; i++) { // 결과 세기 
			if (nodeList[i] > max) {
				max = nodeList[i];
				answer = 1;
			} else if (nodeList[i] == max) {
				answer++;
			}
		}
		
		return answer; // 반환 
	}
	
	public static void main(String[] args) {
		int n = 6;
		int[][] vertex = {{3,6},{4,3},{3,2},{1,3},{1,2},{2,4},{5,2}};
		System.out.println(solution(n, vertex));
		
	}

}
