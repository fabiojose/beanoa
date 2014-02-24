package net.sourceforge.beanoa.jni.toolling;

import java.util.ArrayList;
import java.util.List;

public final class SetterMethod extends SourceItem {

	public static final String SET = "set";
	
	private Field base;
	public SetterMethod(final SourceContext context, final Field base, final List<SourceItem> inners) {
		super(context, inners);
		this.base = base;
		
		if(null== this.inners){
			this.inners = new ArrayList<SourceItem>(); 
		}
		this.inners.add(0, new Line(context, this.base.getName() + " = " + SourceGenerator.PREFIX + this.base.getName() + ";", null));
	}

	@Override
	public void start() {
		context.writeStart("void set" + base.getJavaBeanName() + "(" + base.getJnitype() + " " + SourceGenerator.PREFIX + base.getName() + "){");
		write(inners);
	}

	@Override
	public void end() {
		context.writeEnd("}");
	}

}
