package net.sourceforge.beanoa.jni.toolling;

import java.util.List;

public final class PrivateStatement extends SourceItem {

	public PrivateStatement(final SourceContext context, final List<SourceItem> inners) {
		super(context, inners);
	}

	@Override
	public void start() {
		context.writeStart("private:");
		write(inners);
	}

	@Override
	public void end() {
	}

}
