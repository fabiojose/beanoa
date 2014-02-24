package net.sourceforge.beanoa.jni.toolling;

import java.util.List;

public final class ProtectedStatement extends SourceItem {

	public ProtectedStatement(final SourceContext context, final List<SourceItem> inners) {
		super(context, inners);
	}

	@Override
	public void start() {
		context.writeStart("protected:");
		write(inners);
	}

	@Override
	public void end() {

	}

}
