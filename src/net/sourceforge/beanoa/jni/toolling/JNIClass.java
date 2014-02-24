package net.sourceforge.beanoa.jni.toolling;

import java.util.List;

import net.sourceforge.beanoa.util.Constants;

public final class JNIClass extends SourceItem {

	private String name;
	public JNIClass(final SourceContext context, final String name, final List<SourceItem> inners) {
		super(context, inners);
		this.inners = inners;
		this.name = name;
		
		inject(this.inners);
	}
	
	void inject(final List<SourceItem> inners){
		for(SourceItem _inner : inners){
			if(_inner instanceof PrivateStatement){
				final PrivateStatement _private = (PrivateStatement)_inner;
				_private.getInners().add(Constants.INTEGER_0, new Field(context, SourceGenerator.JCLASS, SourceGenerator.JCLASS_VAR));
				_private.getInners().add(Constants.INTEGER_1, new Field(context, SourceGenerator.JOBJECT, SourceGenerator.JOBJECT_VAR));
				_private.getInners().add(Constants.INTEGER_2, new Field(context, SourceGenerator.JNIENV, SourceGenerator.JNIENV_VAR_POINTER));
				
			} else if (_inner instanceof PublicStatement){
				final PublicStatement _public = (PublicStatement)_inner;
				_public.getInners().add(Constants.INTEGER_0, new JNIClassConstructor(context, this, null));
			}
		}
	}

	@Override
	public void start() {
		context.writeStart("class " + name + " {");
		
		write(inners);
	}

	@Override
	public void end() {
		context.writeEnd("};");
	}

	String getName(){
		return name;
	}
}
