package net.sourceforge.beanoa.example.webtier;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sourceforge.beanoa.example.bean.Customer;
import net.sourceforge.beanoa.example.json.JSONExample1;

import org.json.JSONException;

public final class AsyncServetJSONResponse extends HttpServlet {
	private static final long serialVersionUID = -1710945106889975403L;

	private void doWork(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		
		response.setContentType("text/plain");
		final PrintWriter _writer = response.getWriter();
		try{
			final Customer _customer = Customer.getInstance();
			System.out.println(_customer);
			
			final JSONExample1 _json = new JSONExample1();
			
			_writer.write(_json.toJSON(_customer));
			
		}catch(JSONException _e){
			response.setContentType("text/plain");
			_e.printStackTrace(_writer);
			
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
