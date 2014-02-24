package net.sourceforge.beanoa.example.bean;

import java.io.Serializable;
import java.util.Collection;
import java.util.HashSet;

import net.sourceforge.beanoa.bean.Beanoa;
import net.sourceforge.beanoa.bean.Visitable;
import net.sourceforge.beanoa.bean.Visitor;

public final class Customer implements Serializable, Visitable<Customer>, Beanoa{
	private static final long serialVersionUID = 7384808943087610937L;
	
	private String name;
	private String lastName;
	private int age;
	private Collection<Telephone> telephone;
	private Address home;
	
	public static final Customer getInstance(){
		
		Customer _customer = new Customer();
		_customer.setAge(28);
		_customer.setName("JOSEPH");
		_customer.setTelephone(new HashSet<Telephone>());
		
		Telephone _cellphone = new Telephone();
		_cellphone.setNumber("55566-9999");
		_cellphone.setType(TelephoneType.CELLPHONE);
		
		_customer.getTelephone().add(_cellphone);
		
		Telephone _home = new Telephone();
		_home.setNumber("22266-9999");
		_home.setType(TelephoneType.HOME);
		
		_customer.getTelephone().add(_home);
		
		Address _ahome = new Address();
		_ahome.setCountry("BRAZIL");
		_ahome.setState("SP");
		_ahome.setLine1("AV. PAULISTA, 1500");
		
		_customer.setHome(_ahome);
		
		return _customer;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public String getLastName(){
		return lastName;
	}
	
	public void setLastName(String lastName){
		this.lastName = lastName;
	}

	public Collection<Telephone> getTelephone() {
		if(null== telephone){
			telephone = new HashSet<Telephone>();
			
		}
		return telephone;
	}

	public void setTelephone(Collection<Telephone> telephones) {
		this.telephone = telephones;
	}
	
	public void addTelephone(Telephone telephone){
		getTelephone().add(telephone);
	}
	
	public Address getHome() {
		return home;
	}

	public void setHome(Address home) {
		this.home = home;
	}

	public String toString(){
		return "" + name + " (" + lastName + "), " + age + ", " + telephone + ", home: " + home;
	}

	@Override
	public int hashCode() {
		final int PRIME = 31;
		int result = 1;
		result = PRIME * result + ((name == null) ? 0 : name.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		final Customer other = (Customer) obj;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}

	public <R> R accept(Visitor<Customer, R> visitor) {
		
		return visitor.visit(this);
	}

}
