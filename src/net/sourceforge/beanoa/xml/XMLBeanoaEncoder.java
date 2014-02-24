package net.sourceforge.beanoa.xml;

import java.beans.IntrospectionException;
import java.io.IOException;
import java.io.StringWriter;
import java.util.logging.Logger;


import net.sourceforge.beanoa.AbstractBeanoaEncoder;
import net.sourceforge.beanoa.BeanoaEncoder;
import net.sourceforge.beanoa.Wrapper;
import net.sourceforge.beanoa.util.Constants;

import org.apache.commons.betwixt.io.BeanWriter;
import org.apache.commons.betwixt.strategy.DecapitalizeNameMapper;
import org.xml.sax.SAXException;


public class XMLBeanoaEncoder extends AbstractBeanoaEncoder<String> implements BeanoaEncoder<Object, String> {
	private static final Logger LOGGER = Logger.getLogger(XMLBeanoaEncoder.class.getName());

	public XMLBeanoaEncoder(final Wrapper<String> wrapper) {
		super(wrapper);
	}

	public void overall(final Object input) {
		
		StringWriter _swriter = new StringWriter();
		BeanWriter _writer = new BeanWriter(_swriter);

		try {
			_writer.getXMLIntrospector().getConfiguration().setWrapCollectionsInElement(false);
			_writer.getXMLIntrospector().getConfiguration().setAttributesForPrimitives(false);
			_writer.getXMLIntrospector().getConfiguration().setElementNameMapper(new DecapitalizeNameMapper());
			_writer.getBindingConfiguration().setMapIDs(false);
			_writer.enablePrettyPrint();
			_writer.getBindingConfiguration().setObjectStringConverter(new CustomObjectStringConverter());

			_writer.write(input);
			_writer.close();
			
		} catch (IOException _e) {
			LOGGER.throwing(getClass().getName(), Constants.OVERALL, _e);
			
		} catch (IntrospectionException _e) {
			LOGGER.throwing(getClass().getName(), Constants.OVERALL, _e);
			
		} catch (SAXException _e) {
			
			LOGGER.throwing(getClass().getName(), Constants.OVERALL, _e);
		}
		
		getWrapper().setValue(_swriter.toString());
	}

	public String getResult(){
		return getWrapper().getValue();
	}
}
