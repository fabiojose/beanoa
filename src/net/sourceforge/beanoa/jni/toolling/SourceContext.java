package net.sourceforge.beanoa.jni.toolling;

public final class SourceContext {

	private StringBuilder target;
	public SourceContext(final StringBuilder target){
		this.target = target;
	}
	
	public void writeStart(final String start){
		target.append(SourceGenerator.NEW_LINE + SourceGenerator.TAB + start);
	}
	
	public void writeEnd(final String end){
		target.append(SourceGenerator.NEW_LINE + SourceGenerator.TAB + end);
	}
}
