package net.sourceforge.beanoa.example.webtier.cookie;

import javax.servlet.http.Cookie;

import net.sourceforge.beanoa.example.json.JSONExample1;

import org.json.JSONException;


public final class CookieExample {

	public Cookie toCookie(final Object bean){
		
		final JSONExample1 _json = new JSONExample1();
		String _sjson = null;
		
		try{
			_sjson = _json.toJSON(bean);
			
		}catch(JSONException _e) {
			_e.printStackTrace();
		}
		
		return new Cookie("CookieJSON", _sjson);
	}
	
	public Object toObject(final Cookie cookie){
		
		final JSONExample1 _json = new JSONExample1();
		Object _result = null;
		
		try{
			_result = _json.toObject(cookie.getValue());
			
		}catch(JSONException _e){
			_e.printStackTrace();
		}
		
		return _result;
	}
}
