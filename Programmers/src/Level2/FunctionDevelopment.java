/**
 * Programmers - Stack/Queue Category
 * Problem Name : 기능개발  
 * Writed by Rush.K
 */

package Level2;

import java.util.List;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;


class Function { // 기능 클래스 
	int progress; // 진행 정도 
	int speed; // 진행 속도 
	
	public Function() {
		progress = 0;
		speed = 0;
	}
	
	public Function(int _progress, int _speed) {
		progress = _progress;
		speed = _speed;
	}
	
	public int UpdateNum() { // 진행 정도 100을 달성하기 위해 필요한 일수 
		int result = 0;
		if ((100 - progress) % speed == 0) {
			result = (100 - progress) / speed;
		} else {
			result = ((100 - progress) / speed) + 1;
		}
		return result;
	}
}

public class FunctionDevelopment {
	
	public static Queue<Function> Pipe; // 업데이트된 기능 큐 
	public static Queue<Function> functions; // 개발해야하는 기능 큐 
	public static List<Integer> answerList; // 업데이트 완료된 기능 개수 리스트 
	
    public static int[] solution(int[] progresses, int[] speeds) {
        int[] answer = {};
        int midAnswer = 0;
        int updateNum = 0;
    	Function func;

    	Pipe = new LinkedList<>();
    	functions = new LinkedList<>();
    	answerList = new ArrayList<>();
    	
    	for (int i = 0; i < progresses.length; i++) { // 기능 큐에 옮김 
    		func = new Function(progresses[i], speeds[i]);
    		functions.add(func);
    	}
    	
    	while (!functions.isEmpty()) {
            midAnswer = 0;
    		updateNum = functions.peek().UpdateNum();
    		while (!functions.isEmpty()) { // 기능 큐의 기능들을 업데이트하여 Pipe로 옮김 
    			func = functions.poll();
    			func.progress = func.progress + func.speed * updateNum;
    			Pipe.add(func);
    		}
    		
    		while (!Pipe.isEmpty()) {
    			func = Pipe.poll();
    			if (func.progress >= 100) { // 완성된 기능의 개수 구함 
    				midAnswer++;
    			}
    			else { // 미완성 기능부터 뒷쪽 기능들을 다시 기능 큐에 원복시킴 
    				functions.add(func);
    				while (!Pipe.isEmpty()) {
    					func = Pipe.poll();
    					functions.add(func);
    				}
    				break;
    			}
    		}
    		
    		if (midAnswer > 0) answerList.add(midAnswer); // 완성된 기능 개수를 answerList에 추가 
    	}
    	
    	answer = answerList.stream().mapToInt(i -> i).toArray(); // answerList to answer array
        return answer;
    }
    
	public static void main(String[] args) {
		int[] progresses = {93, 30, 55};
		int[] speeds = {1, 30, 5};
		int[] answer = solution(progresses, speeds);
		for (int t : answer) System.out.println(t);

	}

}
