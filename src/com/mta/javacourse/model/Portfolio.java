/**
 * 
 */
package com.mta.javacourse.model;

import java.util.Date;

/**
 * @author Reut Barhoom
 * This class defines variables, methods of adding stock, getting stock, remove stock.
 * It also has an inner class (StockStatus).
 */

public class Portfolio {

	private final static int MAX_PORTFOLIO_SIZE = 5;
	public String title;
	private int portfolioSize = 0; 

	public Stock[] stocks = new Stock[MAX_PORTFOLIO_SIZE];	
	public StockStatus[] stocksStatus = new StockStatus [MAX_PORTFOLIO_SIZE];

	/** c'tor  */
	public Portfolio(){}

	/** copy c'tor  */
	public Portfolio (Portfolio portfolio){

		this.setTitle (portfolio.getTitle());
		this.setPortfolioSize (portfolio.getPortfolioSize());	
	
		for (int i = 0; i < portfolio.portfolioSize; i++){
			stocks[i] = new Stock (portfolio.getStocks()[i]);
		}

		for (int n = 0; n < portfolio.portfolioSize; n++){
			stocksStatus[n] = new StockStatus (portfolio.getStocksStatus()[n]);
		}	
	}

	/** c'tor  */
	public Portfolio(String title, Stock[] stocks, StockStatus[] stocksStatus, int portfolioSize){

		setTitle(title);
		setStocks(stocks);
		setStocksStatus(stocksStatus);
		setPortfolioSize(portfolioSize);

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

	/** adds a new stock to portfolio */
	public void addStock (Stock s){
		stocks [portfolioSize] = s;
		portfolioSize++; 
	}

	/** remove stock from portfolio */
	public void removeStock(int index){ 
		if(index == portfolioSize)
		{
			this.portfolioSize--;
		}
		else  
		{ 
			this.portfolioSize--; 
			for(int i = index; i <= portfolioSize-1; i++) 
			{ 
				this.stocks[i] = this.stocks[i+1]; 
			} 
		} 
	}	 

	public void setStocks (Stock[] stocks){
		this.stocks = stocks;
	}

	public Stock[] getStocks(){
		return stocks;
	}

	public void setStocksStatus(StockStatus[] stocksStatus){
		this.stocksStatus = stocksStatus;
	}
	
	private StockStatus[] getStocksStatus() {
		return stocksStatus;
	}

	/** prints the stocks with a line space */
	public String getHtmlString(){
		String getHtmlString = getTitle()+ "<br>" ;
		for (int i = 0; i < portfolioSize; i++){
			getHtmlString += " <b> Stock </b> "+(i+1)+" : "+stocks[i].getHtmlDescription()+" <br> " ;
		}
		return getHtmlString; 	
	}
	

	/** inner class that checks the stocks status  */
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

		/** c'tor  */
		public StockStatus (){}

		/** copy c'tor  */
		public StockStatus (StockStatus stockStatus){

			if(this.symbol != null){

				symbol = stockStatus.symbol;
				this.setCurrentBid (stockStatus.getCurrentBid());
				this.setCurrentAsk (stockStatus.getCurrentAsk());
				date = new Date (stockStatus.date.getTime());
				this.setRecommendation (stockStatus.getRecommendation());
				this.setStockQuantity (stockStatus.getStockQuantity());
			}

		}
		
		/** c'tor  */
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

		public StockStatus[] getStocksStatus(){
			return stocksStatus;
		}
	}	
}
