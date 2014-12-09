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
	
	public Portfolio(){}
	
	public Portfolio (Portfolio portfolio){
		
		this.setTitle (portfolio.getTitle());
		this.setPortfolioSize (portfolio.getPortfolioSize());	
		
		for (int i = 0; i < portfolio.portfolioSize; i++){
			stocks[i] = new Stock (portfolio.getStocks()[i]);
		}
			
		stocksStatus = new StockStatus (portfolio.stockStatus());
	
	}
	
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public int getPortfolioSize() {
		return portfolioSize;
	}

	public void setPortfolioSize(int portfolioSize) {
		this.portfolioSize = portfolioSize;
	}

	public void addStock (Stock s){
		stocks [portfolioSize] = s;
		portfolioSize++; 
	}
	
	public Stock[] getStocks(){
		return stocks;
	}
	
	/** prints the stocks with a line space */
	
	public String getHtmlString(){	
		String getHtmlString = " <h1>portfolio title</h1> "+stocks[0].getHtmlDescription()+" <br> "+stocks[1].getHtmlDescription()+" <br> "+stocks[2].getHtmlDescription()+" <br> ";
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
		
		public StockStatus (){}
		
		public StockStatus (StockStatus stockStatus){
			
			symbol = stockStatus.symbol;
			this.setCurrentBid (stockStatus.getCurrentBid());
			this.setCurrentAsk (stockStatus.getCurrentAsk());
			date = new Date (stockStatus.date.getTime());
			this.setRecommendation (stockStatus.getRecommendation());
			this.setStockQuantity (stockStatus.getStockQuantity());
		
		}
		
		public StockStatus(String symbol, float currentBid, float currentAsk, Date date, int recommendation, int stockQuantity){
			
			setSymbol(symbol);
			setCurrentBid (currentBid);
			setCurrentAsk (currentAsk);
			setDate (date);
			setRecommendation (recommendation);
			setStockQuantity (stockQuantity);
			
		}

		public String getSymbol() {
			return symbol;
		}

		public void setSymbol(String symbol) {
			this.symbol = symbol;
		}

		public float getCurrentBid() {
			return currentBid;
		}

		public void setCurrentBid(float currentBid) {
			this.currentBid = currentBid;
		}

		public float getCurrentAsk() {
			return currentAsk;
		}

		public void setCurrentAsk(float currentAsk) {
			this.currentAsk = currentAsk;
		}

		public Date getDate() {
			return date;
		}

		public void setDate(Date date) {
			this.date = date;
		}

		public int getRecommendation() {
			return recommendation;
		}

		public void setRecommendation(int recommendation) {
			this.recommendation = recommendation;
		}

		public int getStockQuantity() {
			return stockQuantity;
		}

		public void setStockQuantity(int stockQuantity) {
			this.stockQuantity = stockQuantity;
		}
	}	
}
