package net.sourceforge.beanoa.example.webtier;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sourceforge.beanoa.example.bean.Customer;
import net.sourceforge.beanoa.example.xml.apache.BetwixtExample;

public final class AsyncServetXMLResponse extends HttpServlet {
	private static final long serialVersionUID = -3684888061393546140L;

	private void doWork(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		
		response.setContentType("application/xml");
		final PrintWriter _writer = response.getWriter();
		
		try{
			final Customer _customer = Customer.getInstance();
			final BetwixtExample _xml = new BetwixtExample();
			
			_writer.write(_xml.toXML(_customer));
			
		}finally{
			_writer.flush();
			_writer.close();
		}
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		doWork(request, response);
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doWork(request, response);
	}
}
