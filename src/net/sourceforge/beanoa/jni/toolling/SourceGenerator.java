package net.sourceforge.beanoa.jni.toolling;

import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sourceforge.beanoa.util.Constants;

public final class SourceGenerator {

	public static final String NEW_LINE = "\n";
	public static final String TAB_SPACE = "  ";
	public static String TAB = TAB_SPACE;
	
	public static final String PREFIX = "__";
	public static final String INSTANCE = "instance";
	
	public static final String JMETHODID = "jmethodID";
	public static final String JMETHODID_PREFIX = "__m";
	
	public static final String JOBJECT = "jobject";
	public static final String JOBJECT_VAR = "ajobject";
	
	public static final String JCLASS = "jclass";
	public static final String JCLASS_VAR = "ajclass";
	
	public static final String FULL_QUALIFIED_NAME_VAR = "afqn";
	
	public static final String JNIENV = "JNIEnv";
	public static final String JNIENV_VAR = "ajnienv";
	public static final String JNIENV_VAR_POINTER = "*" + JNIENV_VAR;
	
	public static final String JINT = "jint";
	public static final String JSTRING = "jstring";
	public static final String JBOOLEAN = "jboolean";
	public static final String JBYTE = "jbyte";
	public static final String JCHAR = "jchar";
	public static final String JSHORT = "jshort";
	public static final String JLONG = "jlong";
	public static final String JFLOAT = "jfloat";
	public static final String JDOUBLE = "jdouble";
	public static final String VOID = "void";
	
	public static final Map<String, String> TYPE_MAPPING = new HashMap<String, String>();
	static{
		TYPE_MAPPING.put(Integer.TYPE.getSimpleName(), JINT);
		TYPE_MAPPING.put(Integer.class.getSimpleName(), JINT);
		TYPE_MAPPING.put(String.class.getSimpleName(), JSTRING);
		TYPE_MAPPING.put(Boolean.TYPE.getSimpleName(), JBOOLEAN);
		TYPE_MAPPING.put(Boolean.class.getSimpleName(), JBOOLEAN);
		TYPE_MAPPING.put(Byte.TYPE.getSimpleName(), JBYTE);
		TYPE_MAPPING.put(Byte.class.getSimpleName(), JBYTE);
		TYPE_MAPPING.put(Character.TYPE.getSimpleName(), JCHAR);
		TYPE_MAPPING.put(Character.class.getSimpleName(), JCHAR);
		TYPE_MAPPING.put(Short.TYPE.getSimpleName(), JSHORT);
		TYPE_MAPPING.put(Short.class.getSimpleName(), JSHORT);
		TYPE_MAPPING.put(Long.TYPE.getSimpleName(), JLONG);
		TYPE_MAPPING.put(Long.class.getSimpleName(), JLONG);
		TYPE_MAPPING.put(Float.TYPE.getSimpleName(), JFLOAT);
		TYPE_MAPPING.put(Float.class.getSimpleName(), JFLOAT);
		TYPE_MAPPING.put(Double.TYPE.getSimpleName(), JDOUBLE);
		TYPE_MAPPING.put(Double.class.getSimpleName(), JDOUBLE);
		TYPE_MAPPING.put(Void.TYPE.getSimpleName(), VOID);
		
		TYPE_MAPPING.put(JMETHODID, JMETHODID);
		TYPE_MAPPING.put(JOBJECT, JOBJECT);
		TYPE_MAPPING.put(JCLASS, JCLASS);
		TYPE_MAPPING.put(JNIENV, JNIENV);
	};
	
	private StringBuilder target;
	public SourceGenerator(final StringBuilder target){
		this.target = target;
	}
	
	private void generate(final Class<?> bclass){
		if(null!= bclass){
			final SourceContext _context = new SourceContext(target);
			
			final List<SourceItem> _fields = new ArrayList<SourceItem>();
			final List<SourceItem> _fieldsmid = new ArrayList<SourceItem>();
			final List<SourceItem> _getters = new ArrayList<SourceItem>();
			final List<SourceItem> _setters = new ArrayList<SourceItem>();
			
			final List<Class<?>> _dependecies = new ArrayList<Class<?>>();
 			
			final Method[] _methods = bclass.getMethods();
			boolean _genfield = Boolean.FALSE;
			for(Method _method : _methods){
				if(!Modifier.isStatic(_method.getModifiers()) &&
				   !Modifier.isNative(_method.getModifiers())){
					
					Class<?> _classt = null;
 					String _type = null;
					String _name = null;
					boolean _hasSetter = Boolean.FALSE;
					boolean _hasGetter = Boolean.FALSE;
					if(_method.getName().startsWith(GetterMethod.GET)){
						//is a getter
						_genfield = Boolean.TRUE;
						_hasGetter = Boolean.TRUE;
						_classt = _method.getReturnType();
						_type = _method.getReturnType().getSimpleName();
						_name = _method.getName().substring(GetterMethod.GET.length());
						
					} if (_method.getName().startsWith(SetterMethod.SET)){
						//is a setter
						_genfield = Boolean.TRUE;
						_hasSetter = Boolean.TRUE;
						_classt = _method.getParameterTypes()[Constants.INTEGER_0];
						_type = _method.getParameterTypes()[Constants.INTEGER_0].getSimpleName();
						_name = _method.getName().substring(SetterMethod.SET.length());
					}
					
					if(_genfield){
						Field _field = new Field(_context, _type, _name); 
						if(!_fields.contains(_field)){
							_fields.add(_field);
						} else {
							_field = (Field)_fields.get( _fields.indexOf(_field) );
						}
						
						JMethodIDField _mid = new JMethodIDField(_context, _field, null);
						if(!_fieldsmid.contains(_mid)){
							_fieldsmid.add(_mid);
						}
						
						if(null== TYPE_MAPPING.get(_type)){
							if(!_dependecies.contains(_classt)){
								_dependecies.add(_classt);
							}
						}
						
						if(_hasGetter){
							_getters.add(new GetterMethod(_context, _field, null));
						}
						
						if(_hasSetter){
							_setters.add(new SetterMethod(_context, _field, null));
						}
						
						_genfield = Boolean.FALSE;
					}
				}
			}
			
			if(!_dependecies.isEmpty()){
				for(Class<?> _dependency : _dependecies){
					generate(_dependency);
				}
			}
			
			final List<SourceItem> _gs = new ArrayList<SourceItem>();
			_gs.addAll(_getters);
			_gs.addAll(_setters);
			
			final List<SourceItem> _prvfields = new ArrayList<SourceItem>();
			_prvfields.addAll(_fieldsmid);
			_prvfields.addAll(_fields);
			
			final PrivateStatement _private = new PrivateStatement(_context, _prvfields);
			final PublicStatement  _public = new PublicStatement(_context, _gs);
			
			final List<SourceItem> _statements = new ArrayList<SourceItem>();
			_statements.add(_private);
			_statements.add(_public);
			
			final JNIClass _class = new JNIClass(_context, bclass.getSimpleName(), _statements);
			_class.start();
			_class.end();
		}else {
			throw new NullPointerException();
		}
	}
	
	public void generate(final Object bean){
		if(null!= bean){			
			generate(bean.getClass());
		} else {
			throw new NullPointerException();
		}
	}
}
