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

	public StockStatus[] stocksStatus = new StockStatus [MAX_PORTFOLIO_SIZE];

	private float balance = 0;
	public enum ALGO_RECOMMENDATION { DO_NOTHING, BUY, SELL };

	/** c'tor  */
	public Portfolio(){}

	/** copy c'tor  */
	public Portfolio (Portfolio portfolio){

		this.setTitle (portfolio.getTitle());
		this.setPortfolioSize (portfolio.getPortfolioSize());	

		for (int n = 0; n < portfolio.portfolioSize; n++){
			stocksStatus[n] = new StockStatus (portfolio.getStocksStatus()[n]);
		}	
	}

	/** c'tor  */
	public Portfolio(String title, Stock[] stocks, StockStatus[] stocksStatus, int portfolioSize, float balance){

		setTitle(title);
		setStocksStatus(stocksStatus);
		setPortfolioSize(portfolioSize);
		setBalance (balance);

	}

	/** update the amount of balance */
	public void updateBalance (float amount){
		balance += amount;
	}

	/** adds a new stock to portfolio */
	public void addStock (Stock stock){

		for (int i = 0; i < portfolioSize; i++){
			if (stock.getSymbol() == stocksStatus[i].getSymbol()){
				System.out.println (" This stock is allready exists ");
				break;
			}			
		}
		if ( portfolioSize < MAX_PORTFOLIO_SIZE ){
			//stocks [portfolioSize] = s;
			stocksStatus [portfolioSize] = new StockStatus(stock.getSymbol(), stock.getBid(), stock.getAsk(), new Date(stock.getDate().getTime()), ALGO_RECOMMENDATION.DO_NOTHING, 0);
			stocksStatus [portfolioSize].setSymbol(stock.getSymbol()); 
			stocksStatus [portfolioSize].setBid(stock.getBid());
			stocksStatus [portfolioSize].setAsk(stock.getAsk());	
			stocksStatus [portfolioSize].setDate(stock.getDate());
			stocksStatus [portfolioSize].setRecommendation(ALGO_RECOMMENDATION.DO_NOTHING);
			stocksStatus [portfolioSize].setStockQuantity(0);
			portfolioSize++;
		}
		else{
			System.out.println (" Can’t add  new stock, portfolio can have only "+MAX_PORTFOLIO_SIZE+" stock ");
		}

	}

	/** remove stock (and sell it) from portfolio and returns true or false if the remove succeed or not */
	public boolean removeStock(String stockSymbol){
		boolean sell = false; 

		for (int n = 0; n < portfolioSize; n++){
			if (stockSymbol == stocksStatus[n].getSymbol()){
				sell = sellStock (stockSymbol, -1);
				if (sell == true){
					if(stockSymbol == stocksStatus [portfolioSize-1].getSymbol()){
						stocksStatus [portfolioSize-1] = null;
						portfolioSize--;
						return true;
					}
					else{
						for (int i = 0; i < portfolioSize; i++){
							if (stockSymbol == stocksStatus[i].getSymbol()){	
								for (int index = i; index < portfolioSize; index++){
									stocksStatus[index] = stocksStatus[index+1]; 	
								}
								stocksStatus [portfolioSize-1] = null;
								portfolioSize--;
								return true;
							}
						}
					}
				}
				System.out.println (" The stock didn't sell so it can't be removed ");
				return false;
			}
		}
		System.out.println (" This stock does not exists ");
		return false;
	}


	/** sell stock from portfolio and returns true or false if the sell succeed or not */
	public boolean sellStock (String symbol, int quantity){

		for (int i = 0; i < portfolioSize; i++){
			if (symbol == stocksStatus[i].getSymbol()){
				if (quantity == -1){
					updateBalance ((stocksStatus[i].getStockQuantity()) * (stocksStatus[i].getBid())); 
					stocksStatus[i].setStockQuantity(0);
					return true;
				}
				else if (quantity < -1){
					System.out.println (" Negative stock quantity is not an option ");
					return false;
				}
				else if((quantity > 0) && (stocksStatus[i].getStockQuantity() <= quantity )){
					updateBalance (quantity * (stocksStatus[i].getBid())); 						
					stocksStatus[i].setStockQuantity (stocksStatus[i].getStockQuantity() - (quantity));
					return true;	
				}
				else{
					System.out.println(" Not enough stocks to sell ");
					return false;
				}
			}
		}

		System.out.println (" This stock does not exists ");
		return false;
	}


	/** buy stock for portfolio and returns	 true or false if the purchase succeed or not */
	public boolean buyStock (String symbol,int quantity){

		for (int i = 0; i < portfolioSize; i++){
			if (symbol == stocksStatus[i].getSymbol()){
				if ((quantity == -1) && (balance - (stocksStatus[i].getAsk() * quantity) >= 0)){
					stocksStatus[i].setStockQuantity ((int) ((balance) / (stocksStatus[i].getAsk())));
					updateBalance (-(balance)); 
					return true;
				}
				else if ((quantity > 0) && (balance - (stocksStatus[i].getAsk() * quantity) >= 0)){
					stocksStatus[i].setStockQuantity (stocksStatus[i].getStockQuantity() + (quantity));
					updateBalance (-((quantity) * (stocksStatus[i].getAsk()) )); 
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

	/** returns value of all stocks in portfolio */
	public float getStocksValue (Stock[] stocks){
		float stocksValue = 0;

		for (int i = 0; i < portfolioSize-1; i++){
			stocksValue = stocksValue + stocksStatus[i].getStockQuantity() * stocksStatus[i].getBid();
		}

		return stocksValue; 
	}

	/**  return value of all stocks in portfolio and balance together */
	public float getTotalValue (Stock[] stocks){
		float totalValue = 0;

		totalValue = (getBalance() + getStocksValue(stocks));

		return totalValue;
	}

	/** prints portfolio's title and portfolio's description: total value, stocks value and balance */
	public String getHtmlString(){

		String getHtmlString = " "+getTitle()+" <br> <br> <b> Total Portfolio Value </b> : "+getTotalValue(stocksStatus)+"$, <b> Total Stocks Value </b> : "+getStocksValue(stocksStatus)+"$, <b> Balance </b> : "+getBalance()+"$ <br> <br> ";

		for(int i = 0; i < portfolioSize; i++){
			getHtmlString += " <b> Stock </b> "+(i+1)+": "+stocksStatus[i].getHtmlDescription()+" <br> ";
		}
			
		return getHtmlString; 	
	}
	

	public float getBalance (){
		return balance;
	}
	
	public void setBalance(float balance) {
		this.balance = balance;
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

	public StockStatus[] getStocksStatus() {
		return stocksStatus;
	}
	
	public void setStocksStatus(StockStatus[] stocksStatus){
		this.stocksStatus = stocksStatus;
	}

}
