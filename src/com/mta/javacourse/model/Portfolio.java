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

	private float balance = 0;
	private enum ALGO_RECOMMENDATION { DO_NOTHING, BUY, SELL };

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

	/** update the amount of balance */
	public void updateBalance (float amount){
		balance += amount;
	}

	/** adds a new stock to portfolio */
	public void addStock (Stock s){

		for (int i = 0; i < portfolioSize; i++){
			if (s.getSymbol() == stocks[i].getSymbol()){
				System.out.println (" This stock is allready exists ");
				break;
			}			
		}
		if ( portfolioSize < MAX_PORTFOLIO_SIZE ){
			stocks [portfolioSize] = s;
			stocksStatus [portfolioSize] = new StockStatus(s.getSymbol(), s.getBid(), s.getAsk(), new Date(s.getDate().getTime()), ALGO_RECOMMENDATION.DO_NOTHING, 0);
			stocksStatus [portfolioSize].setSymbol(s.getSymbol()); 
			stocksStatus [portfolioSize].setCurrentBid(s.getBid());
			stocksStatus [portfolioSize].setCurrentAsk(s.getAsk());	
			stocksStatus [portfolioSize].setDate(s.getDate());
			stocksStatus [portfolioSize].setRecommendation(ALGO_RECOMMENDATION.DO_NOTHING);
			stocksStatus [portfolioSize].setStockQuantity(0);
			portfolioSize++;
		}
		else{
			System.out.println (" Can’t add  new stock, portfolio can have only "+MAX_PORTFOLIO_SIZE+" stock ");
		}

	}

	/** remove stock (and sell it) from portfolio */
	public boolean removeStock(String stockSymbol){
		boolean sell = false; 

		for (int n = 0; n < portfolioSize; n++){
			if (stockSymbol == stocks[n].getSymbol()){
				sell = sellStock (stockSymbol, -1);
				if (sell == true){
					if(stockSymbol == stocks [portfolioSize].getSymbol()){
						portfolioSize--;
						return true;
					}
					else{
						for (int i = 0; i < portfolioSize; i++){
							if (stockSymbol == stocks[i].getSymbol()){	
								for (int index = i; index < portfolioSize; index++){
									stocks[index] = stocks[index+1];
									stocksStatus[index] = stocksStatus[index+1]; 	
								}
							}
						}
						portfolioSize--;
						return true;
					}
				}
				System.out.println (" The stock didn't sell so it can't be removed ");
				return false;
			}
		}
		System.out.println (" This stock does not exists ");
		return false;
	}


	/** sell stock from portfolio */
	public boolean sellStock (String symbol, int quantity){

		for (int i = 0; i < portfolioSize; i++){
			if (symbol == stocksStatus[i].getSymbol()){
				if (quantity == -1){
					updateBalance ((stocksStatus[i].getStockQuantity()) * (stocksStatus[i].getCurrentBid())); 
					stocksStatus[i].stockQuantity = 0;
					return true;
				}
				else if (quantity < -1){
					System.out.println (" Negative stock quantity is not an option ");
					return false;
				}
				else if((quantity > 0) && (stocksStatus[i].getStockQuantity() <= quantity )){
					updateBalance (quantity * (stocksStatus[i].getCurrentBid())); 						
					stocksStatus[i].stockQuantity -= quantity ;
					return true;	
				}
				else{
					System.out.println(" You don't have enough stocks to sail ");
					return false;
				}
			}
		}

		System.out.println (" This stock does not exists ");
		return false;
	}


	/** buy stock for portfolio */
	public boolean buyStock (String symbol,int quantity){

		for (int i = 0; i < portfolioSize; i++){
			if (symbol == stocksStatus[i].getSymbol()){
				if ((quantity == -1) && (balance - (stocksStatus[i].getCurrentAsk() * quantity) >= 0)){
					stocksStatus[i].stockQuantity = (int) ((balance) / (stocksStatus[i].getCurrentAsk()));
					updateBalance (-(balance)); 
					return true;
				}
				else if ((quantity > 0) && (balance - (stocksStatus[i].getCurrentAsk() * quantity) >= 0)){
					stocksStatus[i].stockQuantity += quantity;
					updateBalance (-((quantity) * (stocksStatus[i].getCurrentAsk()) )); 
					return true;
				}
				else{
					System.out.println(" Not enough balance to complete purchase ");
					return false;
				}
			}
		}

		System.out.println(" This stock does not exists ");
		return false;
	}

	/** return value of all stocks */
	public float getStocksValue (StockStatus[] stocksStatus){
		float stocksValue = 0;

		for (int i = 0; i < portfolioSize; i++){
			stocksValue += ((stocksStatus[i].getStockQuantity() )* (stocksStatus[i].getCurrentBid()));
		}

		return stocksValue; 
	}

	public float getBalance (){
		return balance;
	}

	/**  return value of all stocks and balance together */
	public float getTotalValue (StockStatus[] stocksStatus){
		float totalValue = 0;

		totalValue = (getBalance() + getStocksValue(stocksStatus));

		return totalValue;
	}

	/** prints the  portfolio's title, portfolio's value, stocks value and balance */
	public String getHtmlString(){

		String getHtmlString = " "+getTitle()+" <br> Total Potrfolio Value : "+getTotalValue(stocksStatus)+"$, Total Stocks Value : "+getStocksValue(stocksStatus)+"$, Balance : "+getBalance()+"$ ";

		return getHtmlString; 	
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


	/** inner class that checks the stocks status  */
	public class StockStatus {

		private String symbol;
		private float currentBid;
		private float currentAsk;
		private Date date;
		ALGO_RECOMMENDATION recommendation;
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
		public StockStatus(String symbol, float currentBid, float currentAsk, Date date, ALGO_RECOMMENDATION recommendation, int stockQuantity){

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

		public ALGO_RECOMMENDATION getRecommendation() {
			return recommendation;
		}

		public void setRecommendation(ALGO_RECOMMENDATION recommendation) {
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
