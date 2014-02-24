package net.sourceforge.beanoa;

public interface BeanoaDecoder<T, R> {

	T overall();
	
	void setWrapper(Wrapper<R> wrapper);
	Wrapper<R> getWrapper();
}
