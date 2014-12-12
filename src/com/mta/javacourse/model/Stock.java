package com.mta.javacourse.model;

/**
 * @author Reut Barhoom
 * since December 2014 
 */

public class Stock{	
	
	private String symbol;
	private float ask;
	private float bid;
	private java.util.Date date;
	
	/** c'tor  */
	public Stock (){}

	/** copy c'tor  */
	public Stock (Stock stock){
		
		setSymbol (stock.getSymbol());
		setAsk (stock.getAsk());
		setBid(stock.getBid());
		date = new java.util.Date (stock.date.getTime());
		
	}
	
	/** c'tor  */
	public Stock(String symbol, float ask, float bid, java.util.Date date){
		
		setSymbol(symbol);
		setAsk (ask);
		setBid (bid);
		setDate (date);
		
	}
	
	public String getSymbol(){
		return symbol;
	}
	
	public void setSymbol(String symbolS){
		symbol = symbolS;
	}
	
	public float getAsk(){
		return ask;
	}
	
	public void setAsk(float askA){
		ask = askA;	
	}
	
	public float getBid(){
		return bid;
	}
	
	public void setBid(float bidB){
		bid = bidB;	
	}
	public java.util.Date getDate(){
		return date;
	}
	
	public void setDate(java.util.Date dateD){
		date = dateD;
	}
	
	/** prints all of stock's parameters */
	public String getHtmlDescription(){
		String stockHtmlDetailString = "<b> Stock symbol </b>: "+getSymbol()+", <b> Ask </b>: "+getAsk()+", <b> Bid </b>: "+getBid()+", <b> Date </b>:" +date;
		return stockHtmlDetailString; 			
	}
	
}
