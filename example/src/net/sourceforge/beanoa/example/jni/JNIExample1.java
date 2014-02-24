package net.sourceforge.beanoa.example.jni;

import java.util.ArrayList;
import java.util.List;

import net.sourceforge.beanoa.example.bean.Address;
import net.sourceforge.beanoa.example.bean.Customer;
import net.sourceforge.beanoa.jni.toolling.Field;
import net.sourceforge.beanoa.jni.toolling.GetterMethod;
import net.sourceforge.beanoa.jni.toolling.JMethodIDField;
import net.sourceforge.beanoa.jni.toolling.JNIClass;
import net.sourceforge.beanoa.jni.toolling.PrivateStatement;
import net.sourceforge.beanoa.jni.toolling.PublicStatement;
import net.sourceforge.beanoa.jni.toolling.SetterMethod;
import net.sourceforge.beanoa.jni.toolling.SourceContext;
import net.sourceforge.beanoa.jni.toolling.SourceGenerator;
import net.sourceforge.beanoa.jni.toolling.SourceItem;

public final class JNIExample1 {

	public static void main(String[] args) {
		final StringBuilder _builder = new StringBuilder();
		final SourceContext _context = new SourceContext(_builder);
		
		final SetterMethod _method = new SetterMethod(_context, new Field(_context, Integer.TYPE.getSimpleName(), "age"), null);
		final List<SourceItem> _inners = new ArrayList<SourceItem>();
		_inners.add(_method);
		_inners.add(new SetterMethod(_context, new Field(_context, String.class.getSimpleName(), "name"), null));
		_inners.add(new GetterMethod(_context, new Field(_context, String.class.getSimpleName(), "name"), null));
		
		final List<SourceItem> _cinners = new ArrayList<SourceItem>();
		final List<SourceItem> _fields = new ArrayList<SourceItem>();
		final Field _age = new Field(_context, Integer.TYPE, "age");
		_fields.add(_age);
		_fields.add(new JMethodIDField(_context, _age, null));
		
		final Field _name = new Field(_context, String.class, "name"); 
		_fields.add(_name);
		_fields.add(new JMethodIDField(_context, _name, null));
		
		final Field _home = new Field(_context, Address.class, "home");
		_fields.add(_home);
		_fields.add(new JMethodIDField(_context, _home, null));
		final PrivateStatement _private = new PrivateStatement(_context, _fields);
		_cinners.add(_private);
		
		final PublicStatement _public = new PublicStatement(_context, _inners);
		_cinners.add(_public);
		
		final JNIClass _class = new JNIClass(_context, "Customer", _cinners);
		
		_class.start();
		_class.end();
		
		final StringBuilder _new = new StringBuilder();
		final SourceGenerator _gen = new SourceGenerator(_new);
		_gen.generate(Customer.getInstance());
		System.out.println(_new.toString());
		

	}

}
