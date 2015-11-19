package com.naver.test.mvc.ioc.case1;

import org.junit.Before;
import org.junit.Test;

public class IocTest {

	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void test() {
		Printer printer = new Printer();

		printer.setPrinter(new HPPrinter());
		printer.print();

		printer.setPrinter(new LGPrinter());
		printer.print();
	}
}
