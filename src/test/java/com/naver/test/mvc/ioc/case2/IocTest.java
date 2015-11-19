package com.naver.test.mvc.ioc.case2;

import org.junit.Before;
import org.junit.Test;

public class IocTest {

	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void test() {
		PrinterFactory factory = new PrinterFactory();

		factory.getPrinter("HP").print();

		factory.getPrinter("LG").print();
	}
}
