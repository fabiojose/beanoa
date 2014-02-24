package net.sourceforge.beanoa.xml;

import java.util.logging.Logger;

import net.sourceforge.beanoa.util.BeanoaHome;

import org.apache.commons.betwixt.expression.Context;
import org.apache.commons.betwixt.strategy.DefaultObjectStringConverter;

@SuppressWarnings("rawtypes")
public final class CustomObjectStringConverter extends DefaultObjectStringConverter {
	private static final long serialVersionUID = 4495231610160830337L;
	private static final Logger LOGGER = Logger.getLogger(CustomObjectStringConverter.class.getName());

	public String objectToString(Object object, Class type, String flavour, Context context) {
		
		if(BeanoaHome.isLogging()){
			LOGGER.info("object.: " + object);
			LOGGER.info("type...: " + type);
			LOGGER.info("flavour: " + flavour);
		}
				
		return super.objectToString(object, type, flavour, context);
	}
	
	public Object stringToObject(String value, Class type, String flavour, Context context) {
		
		if(BeanoaHome.isLogging()){
			LOGGER.info(type + " = " + value);
		}
		
		return super.stringToObject(value, type, flavour, context);
	}
}
