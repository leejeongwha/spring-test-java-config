package com.naver.test.mvc.ioc.case3;

public class HPPrinter implements PrinterIF {
	@Override
	public void print() {
		System.out.println("HP Printer!!!");
	}
}
