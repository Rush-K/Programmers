/**
 * Programmers - Stack/Queue Category
 * Problem Name : 프린터  
 * Writed by Rush.K
 */

package Level2;

import java.util.LinkedList;
import java.util.Queue;

class Document { // 프린터 큐에 삽입될 문서 클래스 정의 
	int location; // 위치 값 
	int priority; // 우선순위 값 
	
	public Document() { // 생성자 1 
		location = 0;
		priority = 0;
	}
	
	public Document(int _location, int _priority) { // 생성자 2 
		location = _location;
		priority = _priority;
	}
	
}

public class Printer {
	public static Queue<Document> documentList;
	public static Queue<Document> printer;
	
    public static int solution(int[] priorities, int location) {
        int answer = 1;
        int documentListMaxPriority = 0;
        Document document;
        
        documentList = new LinkedList<>();
        printer = new LinkedList<>();
        
        for (int i = 0; i < priorities.length; i++) { // 문서 리스트 작성 및 최고 우선순위 값 도출 
        	document = new Document(i, priorities[i]);
        	documentListMaxPriority = Math.max(documentListMaxPriority, priorities[i]);
        	documentList.add(document);
        }
        
        while (!documentList.isEmpty()) {
        	document = documentList.poll();
        	if (document.priority == documentListMaxPriority) { // 최고 우선순위 값과 같을 경우 
        		documentListMaxPriority = 0;
        		for (Document doc : documentList) documentListMaxPriority = Math.max(doc.priority, documentListMaxPriority); // 문서 리스트 최고 우선순위 값 재도출 
        		printer.add(document); // printer 큐로 옮김 
        	} else { // 최고 우선순위 값과 같지 않을 경우 
        		documentList.add(document); // 문서 리스트 큐 마지막으로 보냄 
        	}
        }
        
        for (Document doc : printer) { // 위치 찾기 
        	if (doc.location == location) {
        		break;
        	} else
        		answer++;
        }
        
        return answer;
    }
    
	public static void main(String[] args) {
		int[] priorities = {2, 1, 3, 2};
		int location = 2;
		
		System.out.println(solution(priorities, location));
	}

}
