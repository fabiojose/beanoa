package net.sourceforge.beanoa.flatfile.lab;

import java.io.FileInputStream;

import org.apache.commons.flatfile.Entity;
import org.apache.commons.flatfile.dsl.ParserEntityFactory;


public final class FlatfileTest {

	public static void main(String[] args) {
	
		try{
			final FileInputStream _fis = new FileInputStream("tests/test.dsl");
			final ParserEntityFactory _factory = new ParserEntityFactory(_fis);
			
			_factory.validate();
			
			final FileInputStream _mass = new FileInputStream("tests/test.mass");
			
			final Entity _name = _factory.getEntity("name");
			System.out.println(_name);
			
			final Entity _born = _factory.getEntity("dateYYYYMMDD");
			System.out.println(_born);
			
			_name.readFrom(_mass);
			System.out.println(new String(_name.getValue()));
			
			_born.readFrom(_mass);
			System.out.println(new String(_born.getValue()));
			
			_fis.close();
			_mass.close();
		}catch(Exception _e){
			_e.printStackTrace();
		}
	}
	

}
