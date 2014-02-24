package net.sourceforge.beanoa.example.xml;

import net.sourceforge.beanoa.BeanoaDecoder;
import net.sourceforge.beanoa.BeanoaEncoder;
import net.sourceforge.beanoa.Wrapper;
import net.sourceforge.beanoa.example.bean.Customer;
import net.sourceforge.beanoa.util.BeanoaHome;
import net.sourceforge.beanoa.xml.XMLBeanoaDecoder;
import net.sourceforge.beanoa.xml.XMLBeanoaEncoder;

public final class XMLExample {

	public static void main(String...args){
		
		//configure
		BeanoaHome.configure();
		
		final Object _from = Customer.getInstance();
		
		System.out.println("Beanoa2 encoder");
		final Wrapper<String> _wrapper = new Wrapper<String>();
		final BeanoaEncoder<Object, String> _encoder2 = new XMLBeanoaEncoder(_wrapper);
		_encoder2.overall(_from);
		System.out.println(_wrapper.getValue());
		
		System.out.println("Beanoa2 decoder");
		final BeanoaDecoder<Object, String> _decoder2 = new XMLBeanoaDecoder(_wrapper);
		final Object _decoded2 = _decoder2.overall();
		System.out.println(_decoded2);
	}
}
