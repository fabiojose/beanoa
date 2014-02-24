package net.sourceforge.beanoa;

public abstract class AbstractBeanoaEncoder<T> {

	private Wrapper<T> wrapper;
	public AbstractBeanoaEncoder(final Wrapper<T> wrapper){
		this.wrapper = wrapper;
	}
	
	public Wrapper<T> getWrapper(){
		return wrapper;
	}
	
	public void setWrapper(final Wrapper<T> wrapper){
		this.wrapper = wrapper;
	}
	
}
