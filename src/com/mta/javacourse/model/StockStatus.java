package com.mta.javacourse.model;

import java.util.Date;

import com.mta.javacourse.model.Portfolio.ALGO_RECOMMENDATION;

/**
 * @author Reut Barhoom
 * This class inherited Stock class.
 *  It checks the stocks status and decides whether to buy, sell or do nothing.  
 */

public class StockStatus extends Stock{

	public ALGO_RECOMMENDATION recommendation;
	private int stockQuantity;
	
	/** c'tor */
	public StockStatus (String symbol, float ask, float bid, java.util.Date date, ALGO_RECOMMENDATION recommendation, int stockQuantity){
		
		setSymbol(symbol);
		setAsk (ask);
		setBid (bid);
		setDate (date);
		setRecommendation(recommendation);
		setStockQuantity(stockQuantity);
		
	}

	/** copy c'tor */
	public StockStatus (StockStatus stockStatus){
		super (stockStatus.getSymbol(), stockStatus.getAsk(), stockStatus.getBid(), new Date (stockStatus.getDate().getTime()));

		this.recommendation = stockStatus.recommendation ;
		this.stockQuantity = stockStatus.stockQuantity;

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

}
