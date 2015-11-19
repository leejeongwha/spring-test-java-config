package com.naver.test.mvc.ioc.case3;

public class LGPrinter implements PrinterIF {
	@Override
	public void print() {
		System.out.println("LG Printer!!!");
	}
}
