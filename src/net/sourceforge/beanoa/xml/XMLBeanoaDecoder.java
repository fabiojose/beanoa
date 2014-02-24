package net.sourceforge.beanoa.xml;

import java.beans.IntrospectionException;
import java.io.IOException;
import java.io.StringReader;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.logging.Logger;


import net.sourceforge.beanoa.AbstractBeanoaDecoder;
import net.sourceforge.beanoa.BeanoaDecoder;
import net.sourceforge.beanoa.Wrapper;
import net.sourceforge.beanoa.util.Constants;

import org.apache.commons.betwixt.io.BeanReader;
import org.apache.commons.betwixt.strategy.DecapitalizeNameMapper;
import org.xml.sax.SAXException;

@SuppressWarnings("rawtypes")
public final class XMLBeanoaDecoder extends AbstractBeanoaDecoder<String> implements BeanoaDecoder<Object, String> {
	private static final Logger LOGGER = Logger.getLogger(XMLBeanoaDecoder.class.getSimpleName());
	private static final Map<String, Class> REGISTERS = new HashMap<String, Class>();
	
	public XMLBeanoaDecoder(Wrapper<String> wrapper) {
		super(wrapper);
	}

	public Object overall() {
		
		BeanReader _reader = new BeanReader();
		_reader.getXMLIntrospector().getConfiguration().setWrapCollectionsInElement(false);
		_reader.getXMLIntrospector().getConfiguration().setAttributesForPrimitives(false);
		_reader.getXMLIntrospector().getConfiguration().setElementNameMapper(new DecapitalizeNameMapper());
		_reader.getBindingConfiguration().setMapIDs(false);
		_reader.getBindingConfiguration().setObjectStringConverter(new CustomObjectStringConverter());

		Object _ready = null;
		try {
			final Set<String> _keys = REGISTERS.keySet();
			for (String _key : _keys) {
				_reader.registerBeanClass(_key, REGISTERS.get(_key));
			}
			StringReader _sreader = new StringReader(wrapper.getValue());
			_ready = _reader.parse(_sreader);

		} catch (IntrospectionException _e) {
			LOGGER.throwing(getClass().getName(), Constants.OVERALL, _e);
			
		} catch (IOException _e) {
			LOGGER.throwing(getClass().getName(), Constants.OVERALL, _e);
			
		} catch (SAXException _e) {
			LOGGER.throwing(getClass().getName(), Constants.OVERALL, _e);
			
		}

		return _ready;
	}

	public static void registerBeanClass(final String tag, Class clazz) {
		REGISTERS.put(tag, clazz);
	}
}
