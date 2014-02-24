package net.sourceforge.beanoa.example.json;

import net.sourceforge.beanoa.example.bean.Customer;
import net.sourceforge.beanoa.example.xml.apache.BetwixtExample;

import org.json.JSONException;
import org.json.JSONObject;
import org.json.XML;


public final class JSONExample1 {

	public static void main(String...args){
		
		final Customer _customer = Customer.getInstance();
		final JSONExample1 _test = new JSONExample1();
		
		try{
			
			System.out.println("JavaBean - inicio: " + _customer);
			System.out.println("JavaBean - inicio (hash): " + _customer.hashCode());
			
			final String _jsonString = _test.toJSON(_customer);
			System.out.println(_jsonString);
			
			/*
			final BetwixtTest _xtier = new BetwixtTest();
			final String _xml = _xtier.toXML(_customer);
			System.out.println(_xml);
			
			final JSONObject _jobject = XML.toJSONObject(_xml);
			System.out.println(_jobject);
			
			final String _xmljson = XML.toString(_jobject);
			System.out.println(_xmljson);
			
			final Object _returns = _xtier.toObject(_xmljson);
			System.out.println(_returns);
			*/
			
			final Object _returns = _test.toObject(_jsonString);
			System.out.println("JavaBean -    fim: " + _returns);
			System.out.println("JavaBean -    fim (hash): " + _returns.hashCode());
			System.out.println("equals? " + _customer.equals(_returns));
		}catch(Exception _e){
			_e.printStackTrace();
		}
	}
	
	public String toJSON(final Object o) throws JSONException{
		
		final BetwixtExample _xml = new BetwixtExample();
		final String _sxml = _xml.toXML(o);
				
		return XML.toJSONObject(_sxml).toString();
	}
	
	public Object toObject(final String jsonString) throws JSONException{
		
		final JSONObject _jsono = new JSONObject(jsonString);
		
		final BetwixtExample _xml = new BetwixtExample();
		final String _sxml = XML.toString(_jsono);
		
		
		return _xml.toObject(_sxml);
	}
}
