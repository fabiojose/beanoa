package net.sourceforge.beanoa.example.xml;

import java.beans.XMLEncoder;

import net.sourceforge.beanoa.example.bean.Customer;

public final class XMLEncoderExample {

	public static void main(String...args){
		
		Customer _customer = Customer.getInstance();
		
		try{
			final XMLEncoder _encoder = new XMLEncoder(System.out);
			_encoder.writeObject(_customer);
			_encoder.close();
		}catch(Exception _e){
			_e.printStackTrace();
		}
	}
}
