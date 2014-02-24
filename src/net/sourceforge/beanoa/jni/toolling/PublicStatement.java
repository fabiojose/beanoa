package net.sourceforge.beanoa.jni.toolling;

import java.util.List;

public final class PublicStatement extends SourceItem {

	public PublicStatement(final SourceContext context, final List<SourceItem> inners) {
		super(context, inners);
	}

	@Override
	public void start() {
		context.writeStart("public:");
		write(inners);
	}

	@Override
	public void end() {
	}

}
