package com.naver.test.mvc.ioc.case2;

public class PrinterFactory {
	public PrinterIF getPrinter(String type) {
		if ("HP".equals(type)) {
			return new HPPrinter();
		} else if ("LG".equals(type)) {
			return new LGPrinter();
		} else {
			return null;
		}
	}
}
