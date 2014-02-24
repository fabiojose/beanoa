package net.sourceforge.beanoa.jni.toolling;

public final class Field extends SourceItem {

	private String type;
	private String name;
	private String jbname;
	private String jnitype;
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
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
		Field other = (Field) obj;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}

	public Field(final SourceContext context, final String type, final String name) {
		super(context, null);
		this.type = type;
		this.name = name.substring(0, 1).toLowerCase() + name.substring(1);
		
		if(null!= this.name){
			jbname = this.name.substring(0, 1).toUpperCase() + this.name.substring(1);
		}
		
		if(null!= this.type){
			jnitype = SourceGenerator.TYPE_MAPPING.get(this.type);
			if(null== jnitype){
				jnitype = this.type;
			}
		}
	}
	
	public Field(final SourceContext context, final Class<?> type, final String name) {
		this(context, type.getSimpleName(), name);
	}

	@Override
	public void start() {
		context.writeStart(getJnitype() + " " + getName() + ";");
	}

	@Override
	public void end() {

	}

	public String getType() {
		return type;
	}

	public String getName() {
		return name;
	}

	public String getJavaBeanName(){
		return jbname;
	}
	
	public String getJnitype(){
		return jnitype;
	}
}
