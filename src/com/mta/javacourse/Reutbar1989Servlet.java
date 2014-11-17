package com.mta.javacourse;
import java.io.IOException;
import javax.servlet.http.*;


@SuppressWarnings("serial")
public class Reutbar1989Servlet extends HttpServlet {
	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		resp.setContentType("text/html");
		
		int radius = 50;
		double area;
		
		area = radius*radius * Math.PI;
		
		String line1 = new String ("calculation 1: Area of circle with radius "+radius+" is: "+area+" square-cm");
		
		
		int hypotenuse = 50;
		double angleB;
		float opposite;
		
		angleB = 30 * (Math.PI/180);
		opposite = (float) (hypotenuse * Math.sin(angleB));
		
		String line2 = new String ("calculation 2: Length of Opposite where angle B is 30 degrees and Hypotenuse length is "+hypotenuse+" cm is: "+opposite+" cm");
		
		
		double base = 20;
		double exp = 13;
		long result;
		
		result = (long) Math.pow(base, exp);
		
		String line3 = new String ("calculation 3: Power of "+base+" with exp of "+exp+" is "+result+" ");
		

		String resultStr = line1 + "<br>"+ line2 + "<br>" +line3; 
		resp.getWriter().println(resultStr);
	}
}