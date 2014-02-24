package net.sourceforge.beanoa.jni.toolling;

import java.util.ArrayList;
import java.util.List;

public final class JNIClassConstructor extends SourceItem {

	private JNIClass jniclass;
	public JNIClassConstructor(final SourceContext context, final JNIClass jniclass, final List<SourceItem> inners) {
		super(context, inners);
		this.jniclass = jniclass;
	}
	
	protected void write(final List<SourceItem> inners){
		
		if(null== this.inners){
			this.inners = new ArrayList<SourceItem>();
		}
		
		for(SourceItem _inner : inners){
			if(_inner instanceof PrivateStatement){			
				for(SourceItem _field : _inner.getInners()){
					if(_field instanceof Field){
						final Field _privf = (Field)_field;
						
						if(_privf.getName().equals(SourceGenerator.JOBJECT_VAR)){
							this.inners.add(new Line(context, SourceGenerator.JOBJECT_VAR + " = " + SourceGenerator.PREFIX + SourceGenerator.JOBJECT_VAR + ";", null));
							
						} else if(_privf.getName().equals(SourceGenerator.JNIENV_VAR_POINTER)){
							this.inners.add(new Line(context, SourceGenerator.JNIENV_VAR + " = " + SourceGenerator.PREFIX + SourceGenerator.JNIENV_VAR + ";", null));
							
						} else if(_privf.getName().equals(SourceGenerator.JCLASS_VAR)){
							this.inners.add(new Line(context, SourceGenerator.JCLASS_VAR + " = " + SourceGenerator.PREFIX + SourceGenerator.JNIENV_VAR + "->GetObjectClass(" + SourceGenerator.PREFIX + SourceGenerator.JOBJECT_VAR + ");", null));
							
						}
					} else if(_field instanceof JMethodIDField){
						final JMethodIDField _mfi = (JMethodIDField)_field;
						this.inners.add(new Line(context, _mfi.getBaseName() + " = NULL;", null));
					}
				}
			}
		}
		
		super.write(this.inners);
	}

	@Override
	public void start() {
		context.writeStart(jniclass.getName() + " (" + SourceGenerator.JOBJECT + " " + SourceGenerator.PREFIX + SourceGenerator.JOBJECT_VAR + ", " + SourceGenerator.JNIENV + " *" + SourceGenerator.PREFIX + SourceGenerator.JNIENV_VAR + "){");
		write(jniclass.getInners());
	}

	@Override
	public void end() {
		context.writeEnd("}");
	}

}
