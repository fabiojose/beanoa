package net.sourceforge.beanoa;

public interface BeanoaEncoder<T, R> {

	void overall(T input);
	
	Wrapper<R> getWrapper();
	void setWrapper(Wrapper<R> wrapper);
}
