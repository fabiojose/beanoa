package net.sourceforge.beanoa;

public class Wrapper<V> {

	private V value;
	public V getValue(){
		return value;
	}

	
	public void setValue(final V value){
		this.value = value;
	}
	
}
