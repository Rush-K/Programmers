/**
 * Programmers - Stack/Queue Category
 * Problem Name : 주식가격 
 * Writed by Rush.K
 */

package Level2;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

class Stock { // 주식 클래스 선언 
	int index; // 주식 번호 
	int price; // 주식 가격 
	int time; // 주식 가격 떨어지지 않은 기간 
	boolean down; // 주식 하락 여부, true : 하락함 / false : 하락하지 않음 
	
	public Stock() { // 생성자 
		index = 0;
		price = 0;
		time = 0;
		down = false;
	}
	
	public Stock(int _index, int _price, int _time) { // 생성자 2 
		index = _index;
		price = _price;
		time = _time;
		down = false;
	}
}

public class StockPrice {
	public static Queue<Stock> Stocks; // prices 배열을 Queue에 저장 
	public static Stack<Stock> StockList; // 주식 가격 흐름을 위한 Stack
	public static Stack<Stock> UpdateStockList; // 업데이트된 주식 가격 흐름을 위한 Stack 

	
    public static int[] solution(int[] prices) {
        int[] answer = new int[prices.length];

        Stock stock = new Stock(); 
        Stock updateStock = new Stock(); 
        
        Stocks = new LinkedList<>();
        StockList = new Stack<>();
        UpdateStockList = new Stack<>();
        
        for (int i = 0; i < prices.length; i++) { // Stocks Queue 업데이트 
        	stock = new Stock(i, prices[i], 0);
        	Stocks.add(stock);
        }

        while (!Stocks.isEmpty()) { // Stocks Queue가 Empty일 때 까지 수행 
        	stock = Stocks.poll();
        	if (StockList.isEmpty()) {
        		StockList.add(stock);
        	} else {
				UpdateStockList.add(stock);
				
    			while (!StockList.isEmpty()) {
    				updateStock = StockList.pop();
    				if (updateStock.down == true) { // 이미 하락한 주식일 경우 
    					answer[updateStock.index] = updateStock.time;
    				} else {
    					if (updateStock.price > stock.price) { // 다음 주식 가격이 떨어질 경우 
    						updateStock.down = true;
    						updateStock.time = updateStock.time + 1;
        					UpdateStockList.add(updateStock);
    					} else {
    						updateStock.time = updateStock.time + 1;
        					UpdateStockList.add(updateStock);
    					}
    				}
    			}
    			
    			while (!UpdateStockList.isEmpty()) { // UpdateStockList Stack -> StockList Stack 
    				updateStock = UpdateStockList.pop();
    				StockList.add(updateStock);
    			}
        	}
        }
        
        while (!StockList.isEmpty()) { // answer 도출 
        	stock = StockList.pop();
        	answer[stock.index] = stock.time;
        }
        
        return answer;
    }
	public static void main(String[] args) {
		int[] prices = {1, 2, 3, 2, 3, 1};
		int[] answer = solution(prices);
		for (int a : answer) System.out.println(a);
	}

}
