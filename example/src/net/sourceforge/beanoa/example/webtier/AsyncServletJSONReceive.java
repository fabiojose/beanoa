package net.sourceforge.beanoa.example.webtier;

import java.io.BufferedInputStream;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sourceforge.beanoa.example.bean.Customer;
import net.sourceforge.beanoa.example.json.JSONExample1;

import org.json.JSONException;

public final class AsyncServletJSONReceive extends HttpServlet {
	private static final long serialVersionUID = -375391235331438997L;

	private void doWork(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		System.out.println("request.getContentLength(): " + request.getContentLength());
	
		final BufferedInputStream _stream = new BufferedInputStream(request.getInputStream());
		byte[] _buffer = new byte[request.getContentLength()];
		
		System.out.println(_stream.read(_buffer));
		System.out.println(_buffer.length);
		final String _jsons = new String(_buffer);
		System.out.println(_jsons);
		
		final JSONExample1 _json = new JSONExample1();
		
		Object _o = null;
		try{
			_o = _json.toObject(_jsons);
			System.out.println(_o);
			final Customer _customer = Customer.getInstance();
			System.out.println("equals? " + _customer.equals(_o));
		}catch(JSONException _e){
			_e.printStackTrace();
		}
		_stream.close();
	    
		response.getWriter().write("SUCESSO!");
		response.getWriter().close();
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doWork(request, response);
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doWork(request, response);
	}
}
