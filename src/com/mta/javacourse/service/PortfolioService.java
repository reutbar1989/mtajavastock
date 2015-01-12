package com.mta.javacourse.service;

import java.io.IOException;
import java.util.Date;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mta.javacourse.exception.BalanceException;
import com.mta.javacourse.exception.PortfolioFullException;
import com.mta.javacourse.exception.StockAlreadyExistsException;
import com.mta.javacourse.exception.StockNotExistException;
import com.mta.javacourse.model.Portfolio;
import com.mta.javacourse.model.Stock;

/**
 * @author Reut Barhoom
 * This class define a new potrfolio and sets values into it.
 * Responsible for my stock portfolio, and checks every day the stock status.
 */

public class PortfolioService {

	Portfolio myPortfolio = new Portfolio();

	public Portfolio getPortfolio() throws BalanceException, PortfolioFullException, StockAlreadyExistsException, StockNotExistException {

		myPortfolio.setTitle("Exercise 7 portfolio");
		
		myPortfolio.updateBalance(10000);
				
		Stock stock1 = new Stock();
		Stock stock2 = new Stock();
		Stock stock3 = new Stock();
	
		Date dateD = new java.util.Date();

		dateD.setDate(15);
		dateD.setMonth(11);

		dateD.setYear(114);
		dateD.setHours(0);
		dateD.setMinutes(0);
		dateD.setSeconds(0);		

		stock1.setSymbol ("PIH");
		stock1.setAsk (10f);
		stock1.setBid (8.5f);
		stock1.setDate (dateD);

		myPortfolio.addStock (stock1);

		stock2.setSymbol ("AAL");
		stock2.setAsk (30f);
		stock2.setBid (25.5f);
		stock2.setDate(dateD);

		myPortfolio.addStock (stock2);

		stock3.setSymbol ("CAAS");
		stock3.setAsk (20f);
		stock3.setBid (15.5f);
		stock3.setDate (dateD);

		myPortfolio.addStock (stock3);
		myPortfolio.addStock (stock3);

		myPortfolio.buyStock("PIH", 20);
		myPortfolio.buyStock("AAL", 30);
		myPortfolio.buyStock("CAAS", 40);
		
		myPortfolio.sellStock("AAL", -1);
		myPortfolio.removeStock("CAAS");
		
		
		return myPortfolio;
	}

}
