/**
 * 
 */
package com.mta.javacourse.model;

import java.util.Date;

import com.mta.javacourse.exception.BalanceException;
import com.mta.javacourse.exception.PortfolioFullException;
import com.mta.javacourse.exception.StockAlreadyExistsException;
import com.mta.javacourse.exception.StockNotExistException;

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
	public void addStock (Stock stock) throws PortfolioFullException, StockAlreadyExistsException {

		for (int i = 0; i < portfolioSize; i++){
			if (stock.getSymbol().equals(stocksStatus[i].getSymbol())){
				throw new StockAlreadyExistsException(stock.getSymbol());
			}			
		}
		if ( portfolioSize < MAX_PORTFOLIO_SIZE ){
			stocksStatus [portfolioSize] = new StockStatus(stock.getSymbol(), stock.getAsk(), stock.getBid(), new Date(stock.getDate().getTime()), ALGO_RECOMMENDATION.DO_NOTHING, 0);
			portfolioSize++;
		}
		else{
			throw new PortfolioFullException(); 
		}

	}

	/** remove stock (and sell it) from portfolio and returns true or false if the remove succeed or not */
	public void removeStock(String stockSymbol) throws StockNotExistException{

		for (int n = 0; n < portfolioSize; n++){
			if (stockSymbol.equals(stocksStatus[n].getSymbol())){
				sellStock (stockSymbol, -1);
				if(stockSymbol.equals(stocksStatus [portfolioSize-1].getSymbol())){
					stocksStatus [portfolioSize-1] = null;
					portfolioSize--;
					return;
				}
				else{
					for (int i = 0; i < portfolioSize; i++){
						if (stockSymbol.equals(stocksStatus[i].getSymbol())){	
							for (int index = i; index < portfolioSize; index++){
								stocksStatus[index] = stocksStatus[index+1]; 	
							}
							stocksStatus [portfolioSize-1] = null;
							portfolioSize--;
							return;
						}
					}
				}
			}

		}
		
		throw new StockNotExistException(stockSymbol);
	}


	/** sell stock from portfolio and returns true or false if the sell succeed or not */
	public void sellStock (String symbol, int quantity) throws StockNotExistException {

		for (int i = 0; i < portfolioSize; i++){
			if (symbol.equals(stocksStatus[i].getSymbol())){
				if (quantity == -1){
					updateBalance ((stocksStatus[i].getStockQuantity()) * (stocksStatus[i].getBid())); 
					stocksStatus[i].setStockQuantity(0);
					return;
				}
				else if (quantity < -1){
					System.out.println (" Negative stock quantity is not an option ");
					return;
				}
				else if((quantity > 0) && (stocksStatus[i].getStockQuantity() <= quantity )){
					updateBalance (quantity * (stocksStatus[i].getBid())); 						
					stocksStatus[i].setStockQuantity (stocksStatus[i].getStockQuantity() - (quantity));
					return;	
				}
				else{
					System.out.println(" Not enough stocks to sell ");
					return;
				}
			}
		}

		throw new StockNotExistException(symbol);
	}


	/** buy stock for portfolio and returns	 true or false if the purchase succeed or not */
	public void buyStock (String symbol,int quantity) throws StockNotExistException, BalanceException {

		for (int i = 0; i < portfolioSize; i++){
			if (symbol.equals(stocksStatus[i].getSymbol())){
				if ((quantity == -1) && (balance - (stocksStatus[i].getAsk() * quantity) >= 0)){
					stocksStatus[i].setStockQuantity ((int) ((balance) / (stocksStatus[i].getAsk())));
					updateBalance (-(balance)); 
					return;
				}
				else if ((quantity > 0) && (balance - (stocksStatus[i].getAsk() * quantity) >= 0)){
					stocksStatus[i].setStockQuantity (stocksStatus[i].getStockQuantity() + (quantity));
					updateBalance (-((quantity) * (stocksStatus[i].getAsk()) )); 
					return;
				}
				else{
					throw new BalanceException((quantity * stocksStatus[i].getAsk()), getBalance());
				}
			}
		}

		throw new StockNotExistException(symbol);
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
	public float getTotalValue (){
		float totalValue = 0;

		totalValue = (getBalance() + getStocksValue(stocksStatus));

		return totalValue;
	}

	/** prints portfolio's title and portfolio's description: total value, stocks value and balance */
	public String getHtmlString(){

		String getHtmlString = " "+getTitle()+" <br> <br> <b> Total Portfolio Value </b> : "+getTotalValue()+"$, <b> Total Stocks Value </b> : "+getStocksValue(stocksStatus)+"$, <b> Balance </b> : "+getBalance()+"$ <br> <br> ";

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
