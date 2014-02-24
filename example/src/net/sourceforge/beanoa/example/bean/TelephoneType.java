package net.sourceforge.beanoa.example.bean;

import java.io.Serializable;

import net.sourceforge.beanoa.bean.Beanoa;
import net.sourceforge.beanoa.bean.Visitable;
import net.sourceforge.beanoa.bean.Visitor;

public class TelephoneType implements Serializable, Visitable<TelephoneType>, Beanoa{
	private static final long serialVersionUID = 444850262974540668L;
	
	public static final TelephoneType CELLPHONE = new TelephoneType(100);
	public static final TelephoneType HOME = new TelephoneType(200);
	
	private int code;
	public TelephoneType(){
		
	}
	
	public TelephoneType(final int code){
		this.code = code;
	}
	
	public void setCode(final int code){
		this.code = code;
	}
	
	public int getCode(){
		return code;
	}
	
	public static final TelephoneType byCode(final int code){
		
		switch(code){
			case 100:
				return CELLPHONE;
			
			case 200:
				return HOME;
		}
		
		return null;
	}
	
	public String toString(){
		return "" + code;
	}
	
	@Override
	public int hashCode() {
		final int PRIME = 31;
		int result = 1;
		result = PRIME * result + code;
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
		final TelephoneType other = (TelephoneType) obj;
		if (code != other.code)
			return false;
		return true;
	}

	public <R> R accept(Visitor<TelephoneType, R> visitor) {
		return visitor.visit(this);
	}
	
	
}