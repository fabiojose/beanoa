package net.sourceforge.beanoa;


public abstract class AbstractBeanoaDecoder<T> {

	protected Wrapper<T> wrapper;
	public AbstractBeanoaDecoder(final Wrapper<T> wrapper){
		this.wrapper = wrapper; 
	}
	
	public void setWrapper(final Wrapper<T> wrapper){
		this.wrapper = wrapper;
	}
	
	public Wrapper<T> getWrapper(){
		return wrapper;
	}
}
