package com.mta.javacourse.servlet;

import java.io.IOException;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mta.javacourse.exception.BalanceException;
import com.mta.javacourse.exception.PortfolioFullException;
import com.mta.javacourse.exception.StockAlreadyExistsException;
import com.mta.javacourse.exception.StockNotExistException;
import com.mta.javacourse.model.Portfolio;
import com.mta.javacourse.model.Stock;
import com.mta.javacourse.service.PortfolioService;

/**
 * @author Reut Barhoom
 * This class gets portfolio and print it's values.
 */

public class PortfolioServlet  extends HttpServlet {

	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		resp.setContentType("text/html"); {

			PortfolioService portfolioService = new PortfolioService();
			Portfolio portfolio;

			try{
				portfolio = portfolioService.getPortfolio();
				resp.getWriter().println(portfolio.getHtmlString() + "<br>"); 
			} catch (Exception e) {
				resp.getWriter().println(e.getMessage());
			}
		}
	}
}
