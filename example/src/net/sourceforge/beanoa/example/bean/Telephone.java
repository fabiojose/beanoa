package net.sourceforge.beanoa.example.bean;

import java.io.Serializable;

import net.sourceforge.beanoa.bean.Beanoa;
import net.sourceforge.beanoa.bean.Visitable;
import net.sourceforge.beanoa.bean.Visitor;


public final class Telephone implements Serializable, Visitable<Telephone>, Beanoa{
	private static final long serialVersionUID = -4636056918956503146L;
	
	private TelephoneType type;
	private String number;
	
	public Telephone(){
		setNumber("NO NUMBER");
	}
	
	public String getNumber() {
		return number;
	}
	public void setNumber(String number) {
		this.number = number;
	}
	public TelephoneType getType() {
		return type;
	}
	public void setType(TelephoneType type) {
		this.type = type;
	}
	
	public String toString(){
		
		return "" + type + ", " + number;
	}
	
	@Override
	public int hashCode() {
		final int PRIME = 31;
		int result = 1;
		result = PRIME * result + ((number == null) ? 0 : number.hashCode());
		result = PRIME * result + ((type == null) ? 0 : type.hashCode());
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
		final Telephone other = (Telephone) obj;
		if (number == null) {
			if (other.number != null)
				return false;
		} else if (!number.equals(other.number))
			return false;
		if (type == null) {
			if (other.type != null)
				return false;
		} else if (!type.equals(other.type))
			return false;
		return true;
	}
	
	public <R> R accept(Visitor<Telephone, R> visitor) {
		return visitor.visit(this);
	}
}
