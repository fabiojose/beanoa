package net.sourceforge.beanoa.bean;

public interface Visitable<T> {

	<R> R accept(Visitor<T, R> visitor);
	
}
