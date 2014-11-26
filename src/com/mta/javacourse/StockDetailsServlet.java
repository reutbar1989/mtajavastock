package com.mta.javacourse;

import java.io.IOException;
import java.util.Date; 

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class StockDetailsServlet extends HttpServlet {
	
	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		resp.setContentType("text/html");
		
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
				
		stock2.setSymbol ("AAL");
		stock2.setAsk (5.5f);
		stock2.setBid (5.78f);
		stock2.setDate(dateD);
		
		stock3.setSymbol ("CAAS");
		stock3.setAsk (31.5f);
		stock3.setBid (31.2f);
		stock3.setDate (dateD);

		resp.getWriter().println (" My Stock: <p> ");
		resp.getWriter().println (" "+stock1.getHtmlDescription()+" <br> ");
		resp.getWriter().println (" "+stock2.getHtmlDescription()+" <br> ");
		resp.getWriter().println (" "+stock3.getHtmlDescription()+" <br> ");
		
	}

}
