package net.sourceforge.beanoa.webtier.cookie;

import javax.servlet.http.Cookie;

import net.sourceforge.beanoa.AbstractBeanoaEncoder;
import net.sourceforge.beanoa.BeanoaEncoder;
import net.sourceforge.beanoa.Wrapper;




public final class CookieBeanoaEncoder extends AbstractBeanoaEncoder<Cookie> implements BeanoaEncoder<Object, Cookie> {
	private static final String NAME = "CookieBeanoa";
	
	private BeanoaEncoder<Object, String> encoder;
	public CookieBeanoaEncoder(final Wrapper<Cookie> wrapper, final BeanoaEncoder<Object, String> encoder){
		super(wrapper);
		this.encoder = encoder;
	}
	
	public void overall(final Object origin) {
		
		encoder.overall(origin);
		getWrapper().setValue(new Cookie(NAME, encoder.getWrapper().getValue()));
	}

	public Cookie getResult(){
		return getWrapper().getValue();
	}
}
