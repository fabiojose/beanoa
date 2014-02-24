package net.sourceforge.beanoa.db;

public final class InstanceID {

	private long value;
	public InstanceID(final long value){
		this.value = value;
	}
	
	public int intValue(){
		return new Long(value).intValue();
	}
	
	public long value(){
		return value;
	}
}
