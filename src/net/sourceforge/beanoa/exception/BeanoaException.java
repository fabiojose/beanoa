package net.sourceforge.beanoa.exception;

public class BeanoaException extends RuntimeException {
	private static final long serialVersionUID = -2923663017504672051L;


	public BeanoaException(){
		
	}
	
	public BeanoaException(final String msg){
		super(msg);
	}
	
	public BeanoaException(final Throwable cause){
		super(cause);
	}
	
	public BeanoaException(final String msg, final Throwable cause){
		super(msg, cause);
	}
}
