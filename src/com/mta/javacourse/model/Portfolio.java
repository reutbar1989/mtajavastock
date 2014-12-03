/**
 * 
 */
package com.mta.javacourse.model;

import java.util.Date;

/**
 * @author Reut Barhoom
 * This class defines variables, methods of adding stock, getting stock.
 * It also has an inner class (StockStatus).
 */

public class Portfolio {
	
	private final static int MAX_PORTFOLIO_SIZE = 5;
	public String title;
	private int portfolioSize = 0; 
	
	public Stock[] stocks = new Stock[MAX_PORTFOLIO_SIZE];	;
	public StockStatus[] stocksStatus = new StockStatus [MAX_PORTFOLIO_SIZE];
	
	public void addStock (Stock s){
		stocks [portfolioSize] = s;
		portfolioSize++; 
	}
	
	public Stock[] getStocks(){
		return stocks;
	}
	
	public String getHtmlString(){	
		String getHtmlString = " <h1>portfolio�title</h1> "+stocks[0].getHtmlDescription()+" <br> "+stocks[1].getHtmlDescription()+" <br> "+stocks[2].getHtmlDescription()+" <br> ";
		return getHtmlString;		
	}
	
	public class StockStatus {
		private final static int DO_NOTHING = 0;
		private final static int BUY = 1;
		private final static int SELL = 2;
	
		private String symbol;
		private float currentBid;
		private float currentAsk;
		private Date date;
		private int recommendation;
		private int stockQuantity;	
	}	
}
