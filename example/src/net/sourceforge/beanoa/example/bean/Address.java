package net.sourceforge.beanoa.example.bean;

import java.io.Serializable;

import net.sourceforge.beanoa.bean.Beanoa;
import net.sourceforge.beanoa.bean.Visitable;
import net.sourceforge.beanoa.bean.Visitor;

public final class Address implements Serializable, Visitable<Address>, Beanoa {
	private static final long serialVersionUID = 3148050930941249620L;

	private long id;
	
	private String country;
	private String state;
	
	private String line1;
	private String line2;
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	public String getLine1() {
		return line1;
	}
	public void setLine1(String line1) {
		this.line1 = line1;
	}
	public String getLine2() {
		return line2;
	}
	public void setLine2(String line2) {
		this.line2 = line2;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	
	public String toString(){
		return "" + country + ", " + state + ", " + line1;
	}
	
	@Override
	public int hashCode() {
		final int PRIME = 31;
		int result = 1;
		result = PRIME * result + ((country == null) ? 0 : country.hashCode());
		result = PRIME * result + ((line1 == null) ? 0 : line1.hashCode());
		result = PRIME * result + ((line2 == null) ? 0 : line2.hashCode());
		result = PRIME * result + ((state == null) ? 0 : state.hashCode());
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
		final Address other = (Address) obj;
		if (country == null) {
			if (other.country != null)
				return false;
		} else if (!country.equals(other.country))
			return false;
		if (line1 == null) {
			if (other.line1 != null)
				return false;
		} else if (!line1.equals(other.line1))
			return false;
		if (line2 == null) {
			if (other.line2 != null)
				return false;
		} else if (!line2.equals(other.line2))
			return false;
		if (state == null) {
			if (other.state != null)
				return false;
		} else if (!state.equals(other.state))
			return false;
		return true;
	}
	
	public <R> R accept(Visitor<Address, R> visitor) {
		return visitor.visit(this);
	}
	
}
