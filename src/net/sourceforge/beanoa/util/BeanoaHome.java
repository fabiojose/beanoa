package net.sourceforge.beanoa.util;

import net.sourceforge.beanoa.xml.XMLBeanoaDecoder;

public final class BeanoaHome {

	private static boolean logging;
	
	private BeanoaHome(){
	}
	
	public static void configure(){
		
		try{
			XMLBeanoaDecoder.registerBeanClass("customer", Class.forName("net.sourceforge.beanoa.example.bean.Customer"));
		}catch(ClassNotFoundException _e){
			throw new RuntimeException(_e.getMessage(), _e);
		}
		
		setLogging(false);
	}
	
	public static void setLogging(final boolean flag){
		logging = flag;
	}
	
	public static boolean isLogging(){
		return logging;
	}
}
