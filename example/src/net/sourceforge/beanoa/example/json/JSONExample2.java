package net.sourceforge.beanoa.example.json;

import net.sourceforge.beanoa.BeanoaDecoder;
import net.sourceforge.beanoa.BeanoaEncoder;
import net.sourceforge.beanoa.Wrapper;
import net.sourceforge.beanoa.example.bean.Customer;
import net.sourceforge.beanoa.json.JSONBeanoaDecoder;
import net.sourceforge.beanoa.json.JSONBeanoaEncoder;
import net.sourceforge.beanoa.util.BeanoaHome;

public final class JSONExample2 {

	public static void main(String[] args) {
	
		BeanoaHome.configure();
		
		final Wrapper<String> _encoded = new Wrapper<String>();
		final BeanoaEncoder<Object, String> _encoder = new JSONBeanoaEncoder(_encoded);
		final Customer _customer = Customer.getInstance();
		
		_encoder.overall(_customer);
		System.out.println(_encoded.getValue());
		
		final BeanoaDecoder<Object, String> _decoder = new JSONBeanoaDecoder(_encoded);
		final Object _o = _decoder.overall();
		System.out.println(_o);
		System.out.println("equals? " + _customer.equals(_o));
	}

}
