package net.sourceforge.beanoa.bean;

public interface Visitor<T, R> {

	R visit(T t);
	
}
