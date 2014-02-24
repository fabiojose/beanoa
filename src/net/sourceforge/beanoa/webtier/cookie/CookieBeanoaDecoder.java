package net.sourceforge.beanoa.webtier.cookie;

import javax.servlet.http.Cookie;

import net.sourceforge.beanoa.AbstractBeanoaDecoder;
import net.sourceforge.beanoa.BeanoaDecoder;
import net.sourceforge.beanoa.Wrapper;


public final class CookieBeanoaDecoder extends AbstractBeanoaDecoder<Cookie> implements BeanoaDecoder<Object, Cookie> {
	
	private BeanoaDecoder<Object, String> decoder;
	public CookieBeanoaDecoder(final Wrapper<Cookie> wrapper, final BeanoaDecoder<Object, String> decoder){
		super(wrapper);
		this.decoder = decoder;
	}
	
	public Object overall() {
		
		final Wrapper<String> _input = new Wrapper<String>();
		_input.setValue(wrapper.getValue().getValue());
		
		decoder.setWrapper(_input);
		final Object _result = decoder.overall();
		
		return _result;
	}
}
