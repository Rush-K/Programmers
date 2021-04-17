package Level3;

import java.util.Comparator;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;

class Job {
	int startTime;
	int workingTime;
	
	public Job(int _startTime, int _workingTime) {
		startTime = _startTime;
		workingTime = _workingTime;
	}
}

public class DiskController {
    public static PriorityQueue<Job> todoList;
    public static PriorityQueue<Job> candoList;
    public static Queue<Job> workingList;
    public static Queue<Job> doneList;
    
	public static int solution(int[][] jobs) {
        int answer = 0;
        int time = 0;
        Job job;
        
        todoList = new PriorityQueue<Job>(new Comparator<Job>() {
        	@Override
        	public int compare(Job o1, Job o2) {
        		return o1.startTime > o2.startTime ? 1 : -1;
        	}
        });
        
        candoList = new PriorityQueue<Job>(new Comparator<Job>() {
        	@Override
        	public int compare(Job o1, Job o2) {
        		return o1.workingTime > o2.workingTime ? 1: -1;
        	}
        });
        
        workingList = new LinkedList<>();
        doneList = new LinkedList<>();
        
        for (int i = 0; i < jobs.length; i++) {
        	job = new Job(jobs[i][0], jobs[i][1]);
        	todoList.add(job);
        }
        
        job = todoList.poll();
        workingList.add(job);
        time = job.startTime;
        
        while (!workingList.isEmpty()) {
        	job = workingList.poll();
        	time += job.workingTime;
        	doneList.add(job);

        	while ((job = todoList.peek()) != null) { 
        		if (job.startTime <= time) {
        			job = todoList.poll();
            		candoList.add(job); 
        		} else {
        			break;
        		}
        	}
        	
        	if (!candoList.isEmpty()) {
        		workingList.add(candoList.poll());
        	} else {
        		if (workingList.isEmpty()) {
        			if (!todoList.isEmpty()) {
        				workingList.add(todoList.poll());
        			}
        		}
        	}
        }
        
        
        time = 0;
        
        while (!doneList.isEmpty()) {
        	job = doneList.poll();
        	if (job.startTime <= time) {
            	answer = answer + job.workingTime + (time - job.startTime);
        	} else {
        		answer = answer + job.startTime + job.workingTime;
        	}
        	time += job.workingTime;
        }
        
        return answer / 3;
    }
    
	public static void main(String[] args) {
		int[][] jobs = {{0,3}, {1,9}, {2,6}};
		System.out.println(solution(jobs));
		
	}
}
