package com.naver.test.mvc.ioc.case0;

public class Printer {
	public void printHP() {
		HPPrinter printer = new HPPrinter();
		printer.print();
	}

	public void printLG() {
		LGPrinter printer = new LGPrinter();
		printer.print();
	}
}
