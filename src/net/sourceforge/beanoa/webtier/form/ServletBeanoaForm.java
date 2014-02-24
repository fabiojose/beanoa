package net.sourceforge.beanoa.webtier.form;

import java.io.IOException;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public final class ServletBeanoaForm extends HttpServlet {
	private static final long serialVersionUID = -2413534216892847913L;

	private void doWork(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
	
		System.out.println(request.getParameterMap());
		final Set<String> _p = request.getParameterMap().keySet();
		for(String _name : _p){
			final String[] _values = request.getParameterValues(_name);
			System.out.println(_name);
			for(String _value : _values){
				System.out.println("\t" + _value);
			}
		}
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		doWork(request, response);
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doWork(request, response);
	}
}
