package net.sourceforge.beanoa.example.jni;

import net.sourceforge.beanoa.example.bean.Customer;
import net.sourceforge.beanoa.example.bean.Telephone;

public final class JNIExample2 {

	native int write(int counter);
	native int doInjection(Customer customer);
	
	static {
		System.loadLibrary("Beanoadll");
	}
	
	public static void main(String...args){
		
		final JNIExample2 _jni = new JNIExample2();
		System.out.println(_jni.write(8));
		
		final Customer _customer = Customer.getInstance();
		System.out.println("Before doInjection " + _customer.getAge());
		System.out.println("Before doInjection " + _customer.getHome().getCountry());
		for(Telephone _telephone : _customer.getTelephone()){
			System.out.println(_telephone.getNumber());
		}
		
		final String _str = "String";
		_str.length();
		System.out.println(_customer.getName().charAt(0));
		System.out.println((int)_customer.getName().charAt(0));
		//point-of-integration
		System.out.println(_jni.doInjection(_customer));
		
		System.out.println("After  doInjection " + _customer.getAge());
		System.out.println("After  doInjection " + _customer.getHome().getCountry());
		for(Telephone _telephone : _customer.getTelephone()){
			System.out.println(_telephone.getNumber());
		}
	}
}
