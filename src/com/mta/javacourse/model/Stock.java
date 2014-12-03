package com.mta.javacourse.model;

public class Stock{	
	
	private String symbol;
	private float ask;
	private float bid;
	private java.util.Date date;
	
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
	
	public String getHtmlDescription(){
		String stockHtmlDetailString = "<b> Stock symbol </b>: "+getSymbol()+", <b> Ask </b>: "+getAsk()+", <b> Bid </b>: "+getBid()+", <b> Date </b>:" +date;
		return stockHtmlDetailString; 			
	}
	
}
