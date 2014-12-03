package com.mta.javacourse.service;

import java.io.IOException;
import java.util.Date;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mta.javacourse.model.Portfolio;
import com.mta.javacourse.model.Stock;

/**
 * @author Reut Barhoom
 * This class define a new potrfolio and sets values into it.   
 */

public class PortfolioService {

	Portfolio myPortfolio = new Portfolio();

	public Portfolio getPortfolio(){

		Stock stock1 = new Stock();
		Stock stock2 = new Stock();
		Stock stock3 = new Stock();
		Date dateD = new java.util.Date();

		dateD.setDate(15);
		dateD.setMonth(10);

		dateD.setYear(114);
		dateD.setHours(0);
		dateD.setMinutes(0);
		dateD.setSeconds(0);		

		stock1.setSymbol ("PIH");
		stock1.setAsk (12.4f);
		stock1.setBid (13.1f);
		stock1.setDate (dateD);

		myPortfolio.addStock (stock1);

		stock2.setSymbol ("AAL");
		stock2.setAsk (5.5f);
		stock2.setBid (5.78f);
		stock2.setDate(dateD);

		myPortfolio.addStock (stock2);

		stock3.setSymbol ("CAAS");
		stock3.setAsk (31.5f);
		stock3.setBid (31.2f);
		stock3.setDate (dateD);

		myPortfolio.addStock (stock3);

		return myPortfolio;
	}

}
