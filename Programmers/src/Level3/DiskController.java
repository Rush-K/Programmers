/**
 * Programmers - Hash Category
 * Problem Name : 디스크 컨트롤러   
 * Writed by Rush.K
 */

package Level3;

import java.util.Comparator;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;

class Job { // 작업 클래스 
	int startTime; // 작업 시작 시간 
	int workingTime; // 작업 수행 시간 
	
	public Job(int _startTime, int _workingTime) { // 생성자 
		startTime = _startTime;
		workingTime = _workingTime;
	}
}

public class DiskController {
    public static PriorityQueue<Job> todoList; // 요청 시간 별 정렬 작업 리스트 
    public static PriorityQueue<Job> candoList; // 수행 가능한 작업 중 수행 시간 별 정렬 작업 리스트 
    public static Queue<Job> tempList; // 임시 작업 저장 리스트 
    public static Queue<Job> doneList; // 작업 완료 리스트 
    
	public static int solution(int[][] jobs) {
        int answer = 0;
        int time = 0;
        Job job;
        
        todoList = new PriorityQueue<Job>(new Comparator<Job>() { // 요청 시간 별 정렬 
        	@Override
        	public int compare(Job o1, Job o2) {
        		return o1.startTime > o2.startTime ? 1 : -1;
        	}
        });
        
        candoList = new PriorityQueue<Job>(new Comparator<Job>() { // 수행 시간 별 정렬 
        	@Override
        	public int compare(Job o1, Job o2) {
        		return o1.workingTime > o2.workingTime ? 1: -1;
        	}
        });
        
        tempList = new LinkedList<>();
        doneList = new LinkedList<>();
        
        for (int i = 0; i < jobs.length; i++) { // argument -> todoList로 옮기기 
        	job = new Job(jobs[i][0], jobs[i][1]);
        	todoList.add(job);
        }
        
        job = todoList.poll(); // 첫 작업 추가 
        time = job.startTime;
        candoList.add(job);
        
        while(!candoList.isEmpty()) { // 작업 해결 후 다음 작업 업데이트 반복 
        	job = candoList.poll();
        	doneList.add(job);
        	time += job.workingTime;
        	
        	while (!todoList.isEmpty()) {
        		job = todoList.poll();
        		if (job.startTime <= time) { // 수행 시간이 알맞는 작업 추출 
        			candoList.add(job);
        		} else {
        			tempList.add(job);
        		}
        	}

        	while (!tempList.isEmpty()) { // 수행 시간이 알맞지 않은 작업 롤백  
        		job = tempList.poll();
        		todoList.add(job);
        	}
        	
        	if (candoList.isEmpty()) { // 수행 시간이 전 작업과 간격이 있는 경우 
            	if (!todoList.isEmpty()) {
            		job = todoList.poll();
            		candoList.add(job);
            	}
        	}
        }
        
        job = doneList.poll(); // 수행 시간 평균 구하기 
        time = job.startTime + job.workingTime;
        answer += job.workingTime;
        
        while (!doneList.isEmpty()) {
        	job = doneList.poll();
        	if (time >= job.startTime) {
        		answer += time - job.startTime + job.workingTime;
            	time += job.workingTime;
        	} else {
        		answer += job.workingTime;
        		time += job.workingTime + job.startTime - time;
        	}
        }
        
        return answer / jobs.length;
    }
    
	public static void main(String[] args) {
		
		//int[][] jobs = {{0, 10}, {2, 10}, {9, 10}, {15, 2}};
		int[][] jobs = {{0, 3}, {1, 9}, {2, 6}};
		//int[][] jobs = {{0, 10}, {2, 12}, {9, 19}, {15, 17}};
		//int[][] jobs = {{10, 10}, {30, 10}, {50, 2}, {51, 2}};
		System.out.println(solution(jobs));
		
	}
}
