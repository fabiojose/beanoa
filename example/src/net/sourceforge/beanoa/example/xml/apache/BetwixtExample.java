package net.sourceforge.beanoa.example.xml.apache;

import java.io.StringReader;
import java.io.StringWriter;


import net.sourceforge.beanoa.example.bean.Customer;
import net.sourceforge.beanoa.example.bean.TelephoneType;
import net.sourceforge.beanoa.xml.CustomObjectStringConverter;

import org.apache.commons.betwixt.io.BeanReader;
import org.apache.commons.betwixt.io.BeanWriter;
import org.apache.commons.betwixt.io.read.BeanCreationChain;
import org.apache.commons.betwixt.io.read.BeanCreationList;
import org.apache.commons.betwixt.io.read.ChainedBeanCreator;
import org.apache.commons.betwixt.io.read.ElementMapping;
import org.apache.commons.betwixt.io.read.ReadContext;
import org.apache.commons.betwixt.strategy.DecapitalizeNameMapper;

public class BetwixtExample {

	public static void main(String...args){
		
		final BetwixtExample _test = new BetwixtExample();
		final String _xml = _test.toXML();
		
		System.out.println(_xml);
		
		final Object _object = _test.toObject();
		
		System.out.println(_object);
		
		final String _input = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>" +
				"<customer xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xsi:noNamespaceSchemaLocation=\"schema1.xsd\">" +
  "<age>0</age>" +
  "<home>" +
    "<country>country</country>" +
    "<line1>line1</line1>" +
    "<line2>line2</line2>" +
    "<state>state</state>" +
  "</home>" +
  "<name>name</name>" +
  "<telephone>" +
    "<number>number</number>" +
    "<type>" +
      "<code>777</code>" +
    "</type>" +
  "</telephone>" +
  "<telephone>" +
    "<number>number</number>" +
    "<type>" +
      "<code>66</code>" +
    "</type>" +
  "</telephone>" +
  "<lastName>MORALES</lastName>"+
"</customer>";
		
		System.out.println(_test.toObject(_input));
		
	}
	
	public String toXML(){
		return toXML(Customer.getInstance());
	}
	
	public String toXML(final Object o){
		try{
			
			StringWriter _swriter = new StringWriter();
			BeanWriter _writer = new BeanWriter(_swriter);
			
			_writer.getXMLIntrospector().getConfiguration().setWrapCollectionsInElement(false);
			_writer.getXMLIntrospector().getConfiguration().setAttributesForPrimitives(false);
			_writer.getXMLIntrospector().getConfiguration().setElementNameMapper(new DecapitalizeNameMapper());
			_writer.getBindingConfiguration().setMapIDs(false);
			_writer.enablePrettyPrint();
			_writer.getBindingConfiguration().setObjectStringConverter(new CustomObjectStringConverter());
			
			_writer.write(o);
			
			_writer.close();
			return _swriter.toString();
			
		}catch(Exception _e){
			_e.printStackTrace();
		}
		
		return null;
	}
	
	public Object toObject(){
		return toObject(toXML());		
	}
	
	public Object toObject(final String xml){
		System.out.println("INPUT: " + xml);
		try{
			BeanReader _reader = new BeanReader();
			_reader.getXMLIntrospector().getConfiguration().setWrapCollectionsInElement(false);
			_reader.getXMLIntrospector().getConfiguration().setAttributesForPrimitives(false);
			_reader.getXMLIntrospector().getConfiguration().setElementNameMapper(new DecapitalizeNameMapper());
			_reader.getBindingConfiguration().setMapIDs(false);
			_reader.getBindingConfiguration().setObjectStringConverter(new CustomObjectStringConverter());
			
			BeanCreationList _chain = BeanCreationList.createStandardChain();
			_chain.insertBeanCreator(1, new ChainedBeanCreator(){
				public Object create(ElementMapping elementMapping, ReadContext context, BeanCreationChain chain) {
					System.out.println("chain: " + elementMapping.getType());
					System.out.println("atts.: " + elementMapping.getAttributes().getLength());
					System.out.println("descr: " + elementMapping.getDescriptor().getElementDescriptors().length);
					
					if(TelephoneType.class.equals(elementMapping.getType())){
						System.out.println("hasChildren " + elementMapping.getDescriptor().hasChildren());
						System.out.println("hasContent " + elementMapping.getDescriptor().hasContent() );
						//return TelephoneType.byCode(Integer.parseInt(elementMapping.getAttributes().getValue("code") ));
					}
					return chain.create(elementMapping, context);
				}
				
			});
			_reader.getReadConfiguration().setBeanCreationChain(_chain);
			
			_reader.registerBeanClass("customer", Customer.class);
			
			StringReader _sreader = new StringReader(xml);
			final Customer _ready = (Customer)_reader.parse(_sreader);
			return _ready;
			
		}catch(Exception _e){
			_e.printStackTrace();
		}
		
		return null;
	}
}
