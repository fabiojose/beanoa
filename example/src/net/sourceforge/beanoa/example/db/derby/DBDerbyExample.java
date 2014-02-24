package net.sourceforge.beanoa.example.db.derby;

import net.sourceforge.beanoa.BeanoaDecoder;
import net.sourceforge.beanoa.BeanoaEncoder;
import net.sourceforge.beanoa.Wrapper;
import net.sourceforge.beanoa.db.DataBaseBeanoaDecoder;
import net.sourceforge.beanoa.db.DataBaseBeanoaEncoder;
import net.sourceforge.beanoa.db.InstanceID;
import net.sourceforge.beanoa.example.bean.Customer;
import net.sourceforge.beanoa.json.JSONBeanoaDecoder;
import net.sourceforge.beanoa.json.JSONBeanoaEncoder;
import net.sourceforge.beanoa.util.BeanoaHome;
import net.sourceforge.beanoa.xml.XMLBeanoaDecoder;
import net.sourceforge.beanoa.xml.XMLBeanoaEncoder;

public final class DBDerbyExample {

	public static final String DRIVER = "org.apache.derby.jdbc.EmbeddedDriver";
	public static final String DB = "tests/derby/beanoa";
	public static final String URL = "jdbc:derby:" + DB + ";create=true";
	public static final String INSERT = "insert into \"APP\".\"SERIALS\" (\"HASH\", \"TYPE\", \"SERIAL\") values(?, ?, ?)";
	public static final String SELECT = "select * from \"APP\".\"SERIALS\" where \"APP\".\"SERIALS\".\"HASH\" = ?";
	public static final String SEQUENCE_ID = "serialsid";
	public static final String NEXT_ID = "values next value for " + SEQUENCE_ID;
	
	public static void main(String[] args) {
		
		BeanoaHome.configure();
		
		///JSON
		System.out.println("JSON");
		final Wrapper<InstanceID> _wrapper = new Wrapper<InstanceID>();
		final Wrapper<String> _jsonw = new Wrapper<String>();
		final BeanoaEncoder<Object, InstanceID> _encoderjson = new DataBaseBeanoaEncoder(_wrapper, new JSONBeanoaEncoder(_jsonw));
		
		final Customer _inputjson = Customer.getInstance();
		_encoderjson.overall(_inputjson);
		System.out.println(_jsonw.getValue());
		System.out.println(_wrapper.getValue().value());
		
		final Wrapper<String> _jsonw2 = new Wrapper<String>();
		final BeanoaDecoder<Object, InstanceID> _decoderjson = new DataBaseBeanoaDecoder(_wrapper, new JSONBeanoaDecoder(_jsonw2));
		Object _output = _decoderjson.overall();
		System.out.println(_output);
		System.out.println("equals? " + _inputjson.equals(_output));
		
		///XML
		System.out.println("XML");
		final Wrapper<String> _xmlw = new Wrapper<String>();
		final BeanoaEncoder<Object, InstanceID> _encoderxml = new DataBaseBeanoaEncoder(_wrapper, new XMLBeanoaEncoder(_xmlw));
		
		final Customer _inputxml = Customer.getInstance();
		_inputxml.setName("MARIA MARIA");
		_encoderxml.overall(_inputxml);
		System.out.println(_xmlw.getValue());
		System.out.println(_wrapper.getValue().value());
		
		final Wrapper<String> _xmlw2 = new Wrapper<String>();
		final BeanoaDecoder<Object, InstanceID> _decoderxml = new DataBaseBeanoaDecoder(_wrapper, new XMLBeanoaDecoder(_xmlw2));
		_output = _decoderxml.overall();
		System.out.println(_output);
		System.out.println("equals? " + _inputxml.equals(_output));
	}

}
