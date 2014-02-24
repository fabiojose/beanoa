package net.sourceforge.beanoa.json;

import java.util.logging.Logger;


import net.sourceforge.beanoa.AbstractBeanoaEncoder;
import net.sourceforge.beanoa.BeanoaEncoder;
import net.sourceforge.beanoa.Wrapper;
import net.sourceforge.beanoa.util.Constants;
import net.sourceforge.beanoa.xml.XMLBeanoaEncoder;

import org.json.JSONException;
import org.json.XML;


public final class JSONBeanoaEncoder extends AbstractBeanoaEncoder<String> implements BeanoaEncoder<Object, String> {
	private static final Logger LOGGER = Logger.getLogger(JSONBeanoaEncoder.class.getName());
	
	public JSONBeanoaEncoder(final Wrapper<String> wrapper){
		super(wrapper);
	}
	
	public void overall(final Object origin) {

		final Wrapper<String> _out = new Wrapper<String>();		
		final BeanoaEncoder<Object, String> _xml = new XMLBeanoaEncoder(_out);
		_xml.overall(origin);
		
		final String _sxml = _out.getValue();
		String _json = null;
		
		try{
			_json = XML.toJSONObject(_sxml).toString();
			
			getWrapper().setValue(_json);
		}catch(JSONException _e){
			LOGGER.throwing(getClass().getName(), Constants.OVERALL, _e);
		}
	}

	public String getResult(){
		return getWrapper().getValue();
	}
}
