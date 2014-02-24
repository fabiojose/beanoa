package net.sourceforge.beanoa.jni.toolling;

import java.util.ArrayList;
import java.util.List;

public final class GetterMethod extends SourceItem {

	public static final String GET = "get";
	private Field base;
	public GetterMethod(final SourceContext context, final Field base, final List<SourceItem> inners) {
		super(context, inners);
		this.base = base;
		
		if(null== this.inners){
			this.inners = new ArrayList<SourceItem>();
		}
		this.inners.add(0, new Line(context, "return " + this.base.getName() + ";", null));
	}

	@Override
	public void start() {
		context.writeStart(base.getJnitype() + " get" + base.getJavaBeanName() + "() {");
		write(inners);
	}

	@Override
	public void end() {
		context.writeEnd("}");
	}

}
