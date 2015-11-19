package com.naver.test.mvc.ioc.case1;

public class Printer {
	private PrinterIF printer;

	public void setPrinter(PrinterIF printer) {
		this.printer = printer;
	}

	public void print() {
		this.printer.print();
	}
}
