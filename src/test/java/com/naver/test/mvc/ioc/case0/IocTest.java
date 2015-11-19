package com.naver.test.mvc.ioc.case0;

import org.junit.Before;
import org.junit.Test;

public class IocTest {

	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void test() {
		Printer printer = new Printer();

		printer.printHP();

		printer.printLG();
	}

}
