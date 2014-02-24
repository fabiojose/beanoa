package net.sourceforge.beanoa.webtier.form;

import net.sourceforge.beanoa.AbstractBeanoaEncoder;
import net.sourceforge.beanoa.BeanoaEncoder;
import net.sourceforge.beanoa.Wrapper;

public final class BeanoaSimpleHtmlFormGen extends AbstractBeanoaEncoder<String> implements BeanoaEncoder<Class<?>, String> {

	public BeanoaSimpleHtmlFormGen(final Wrapper<String> wrapper) {
		super(wrapper);

	}

	@Override
	public void overall(final Class<?> input) {

	}

	
}
