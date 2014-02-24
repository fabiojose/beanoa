package net.sourceforge.beanoa.example.webtier;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sourceforge.beanoa.BeanoaDecoder;
import net.sourceforge.beanoa.BeanoaEncoder;
import net.sourceforge.beanoa.Wrapper;
import net.sourceforge.beanoa.example.bean.Customer;
import net.sourceforge.beanoa.util.BeanoaHome;
import net.sourceforge.beanoa.webtier.cookie.CookieBeanoaDecoder;
import net.sourceforge.beanoa.webtier.cookie.CookieBeanoaEncoder;
import net.sourceforge.beanoa.xml.XMLBeanoaDecoder;
import net.sourceforge.beanoa.xml.XMLBeanoaEncoder;



public final class ServletTest extends HttpServlet {
	private static final long serialVersionUID = 4088359550731835508L;

	static{
		BeanoaHome.configure();
	};
	
	private void doWork(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		final Wrapper<Cookie> _wrapper = new Wrapper<Cookie>();
		
		final BeanoaEncoder<Object, Cookie> _encoder = new CookieBeanoaEncoder(_wrapper, new XMLBeanoaEncoder(new Wrapper<String>()));
		final BeanoaDecoder<Object, Cookie> _decoder = new CookieBeanoaDecoder(_wrapper, new XMLBeanoaDecoder(new Wrapper<String>()));
		
		if(null!= req && null!= req.getCookies()){
			for(Cookie _cok : req.getCookies()){
				System.out.println(_cok.getValue());
				_wrapper.setValue(_cok);
				
				System.out.println("Object: " + _decoder.overall());
			}
		}
		final Customer _customer = Customer.getInstance();
		
		_encoder.overall(_customer);
		resp.addCookie(_wrapper.getValue());
		
		resp.getWriter().println("CookieBeanoa");
		resp.getWriter().close();
		
		System.out.println("chegou!");
	}
	
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		 doWork(req, resp);
	}
	
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doWork(req, resp);
	}
}
