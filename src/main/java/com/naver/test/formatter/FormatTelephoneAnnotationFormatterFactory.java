package com.naver.test.formatter;

import java.text.ParseException;
import java.util.HashSet;
import java.util.Locale;
import java.util.Set;

import org.springframework.format.AnnotationFormatterFactory;
import org.springframework.format.Formatter;
import org.springframework.format.Parser;
import org.springframework.format.Printer;

public class FormatTelephoneAnnotationFormatterFactory implements AnnotationFormatterFactory<FormatTelephone> {
	@Override
	public Set<Class<?>> getFieldTypes() {
		Set<Class<?>> fieldTypes = new HashSet<Class<?>>(1, 1);
		fieldTypes.add(String.class);
		return fieldTypes;
	}

	@Override
	public Parser<?> getParser(FormatTelephone annotation, Class<?> fieldType) {
		return new MaskFormatter(annotation.value());
	}

	@Override
	public Printer<?> getPrinter(FormatTelephone annotation, Class<?> fieldType) {
		return new MaskFormatter(annotation.value());
	}

	private static class MaskFormatter implements Formatter<String> {

		private javax.swing.text.MaskFormatter formatter;

		public MaskFormatter(String mask) {
			try {
				this.formatter = new javax.swing.text.MaskFormatter(mask);
				this.formatter.setValueContainsLiteralCharacters(false);

			} catch (ParseException e) {
				throw new IllegalStateException("Parse exception for " + mask, e);
			}
		}

		@Override
		public String print(String object, Locale locale) {
			try {
				return formatter.valueToString(object);
			} catch (ParseException e) {
				throw new IllegalArgumentException("Print failed " + formatter.getMask(), e);
			}
		}

		@Override
		public String parse(String text, Locale locale) throws ParseException {
			return formatter.valueToString(text);
		}
	}
}
