package com.naver.test.mvc.ioc.case2;

public class HPPrinter implements PrinterIF {
	@Override
	public void print() {
		System.out.println("HP Printer!!!");
	}
}
