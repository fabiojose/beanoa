package net.sourceforge.beanoa.jni.toolling;

import java.util.List;

public final class JMethodIDField extends SourceItem {

	private Field base;
	public JMethodIDField(final SourceContext context, final Field base, final List<SourceItem> inners) {
		super(context, inners);
		this.base = base;
	}

	@Override
	public void start() {
		context.writeStart(SourceGenerator.JMETHODID + " " + getBaseName() + ";");
	}

	@Override
	public void end() {
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((base == null) ? 0 : base.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		JMethodIDField other = (JMethodIDField) obj;
		if (base == null) {
			if (other.base != null)
				return false;
		} else if (!base.equals(other.base))
			return false;
		return true;
	}
	
	Field getBase(){
		return base;
	}
	
	String getBaseName(){
		return SourceGenerator.JMETHODID_PREFIX + base.getName();
	}
}
