package net.sourceforge.beanoa.jni.toolling;

import java.util.List;

public abstract class SourceItem {

	protected SourceContext context;
	protected List<SourceItem> inners;

	public SourceItem(final SourceContext context, final List<SourceItem> inners) {
		this.context = context;
		this.inners = inners;
	}

	public abstract void start();

	public abstract void end();

	private static void doIndentation() {
		SourceGenerator.TAB += SourceGenerator.TAB_SPACE;
	}

	private static void UndoIndentation() {
		SourceGenerator.TAB = SourceGenerator.TAB.substring(0, SourceGenerator.TAB.length() - SourceGenerator.TAB_SPACE.length());
	}
	
	List<SourceItem> getInners(){
		return inners;
	}

	protected void write(final List<SourceItem> inners) {
		if (null != inners) {
			doIndentation();
			for (SourceItem _inner : inners) {
				_inner.start();
				_inner.end();
			}
			UndoIndentation();
		}
	}

	protected void start(final SourceItem inner) {
		if (null != inner) {
			doIndentation();
			inner.start();
		}
	}

	protected void end(final SourceItem inner) {
		if (null != inner) {
			inner.end();
			UndoIndentation();
		}
	}
}
