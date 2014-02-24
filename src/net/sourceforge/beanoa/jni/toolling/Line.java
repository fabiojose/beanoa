package net.sourceforge.beanoa.jni.toolling;

import java.util.List;

public final class Line extends SourceItem {

	private String line;
	public Line(final SourceContext context, final String line, final List<SourceItem> inners) {
		super(context, inners);
		this.line = line;
	}

	@Override
	public void start() {
		context.writeStart(line);
	}

	@Override
	public void end() {
	}

}
