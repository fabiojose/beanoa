package net.sourceforge.beanoa.json;

import java.util.logging.Logger;


import net.sourceforge.beanoa.AbstractBeanoaDecoder;
import net.sourceforge.beanoa.BeanoaDecoder;
import net.sourceforge.beanoa.Wrapper;
import net.sourceforge.beanoa.util.Constants;
import net.sourceforge.beanoa.xml.XMLBeanoaDecoder;

import org.json.JSONException;
import org.json.JSONObject;
import org.json.XML;


public final class JSONBeanoaDecoder extends AbstractBeanoaDecoder<String> implements BeanoaDecoder<Object, String> {
	private static final Logger LOGGER = Logger.getLogger(JSONBeanoaDecoder.class.getName());
	
	public JSONBeanoaDecoder(final Wrapper<String> wrapper){
		super(wrapper);
	}
	
	public Object overall() {

		Object _result = null;
		try {
			final JSONObject _jsono = new JSONObject(wrapper.getValue());
			final String _sxml = XML.toString(_jsono);

			final Wrapper<String> _wxml = new Wrapper<String>();
			_wxml.setValue(_sxml);
			
			final BeanoaDecoder<Object, String> _xml = new XMLBeanoaDecoder(_wxml);
			_result = _xml.overall();
			
		} catch (JSONException _e) {
			LOGGER.throwing(getClass().getName(), Constants.OVERALL, _e);
		}

		return _result;
	}

}
