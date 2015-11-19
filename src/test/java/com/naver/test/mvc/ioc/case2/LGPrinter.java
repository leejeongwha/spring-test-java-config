package com.naver.test.mvc.ioc.case2;

public class LGPrinter implements PrinterIF {
	@Override
	public void print() {
		System.out.println("LG Printer!!!");
	}
}
