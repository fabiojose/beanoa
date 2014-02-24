package net.sourceforge.beanoa.jni.toolling;

import java.util.ArrayList;
import java.util.List;

public final class Beanoa extends SourceItem {

	public Beanoa(final SourceContext context, final List<SourceItem> inners) {
		super(context, inners);
		build();
	}
	
	private void build(){
		inners = new ArrayList<SourceItem>();
		
		final List<SourceItem> _protected = new ArrayList<SourceItem>();
		_protected.add(new Field(context, SourceGenerator.JNIENV, SourceGenerator.JNIENV_VAR_POINTER));
		_protected.add(new Field(context, "char*", SourceGenerator.FULL_QUALIFIED_NAME_VAR));
		_protected.add(new Field(context, SourceGenerator.JMETHODID, "constructor"));
		_protected.add(new Field(context, SourceGenerator.JOBJECT, SourceGenerator.JOBJECT_VAR));
		_protected.add(new Field(context, SourceGenerator.JCLASS, SourceGenerator.JCLASS_VAR));
		
		
	}

	@Override
	public void start() {
		context.writeStart("class Beanoa {");
		/*
		protected:
			//jni attributes
			JNIEnv *jnienv;
			char *javat;
			jmethodID constructor;
			jobject target;
			jclass  javac;

			jmethodID getMethod(jclass _jclass, char *_name, char *_ass){
				return jnienv->GetMethodID(_jclass, _name, _ass);
			}

			void sinit(jobject _target){
				target = _target;
				javac = jnienv->GetObjectClass(_target);
			}

			jobject newTarget(){
				javac = jnienv->FindClass(javat);
				constructor = jnienv->GetMethodID(javac, "<init>", "()V");

				target = jnienv->NewObject(javac, constructor);

				return target;
			}

		public:
			Beanoa(JNIEnv *_jnienv){
				jnienv = _jnienv;
			}

			jobject getTarget(){
				return target;
			}
			*/
	}

	@Override
	public void end() {
		context.writeEnd("};");
	}

}
