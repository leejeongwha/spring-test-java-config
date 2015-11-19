package com.naver.test.mvc.ioc.case3;

import javax.annotation.Resource;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = PrinterConfiguration.class, loader = AnnotationConfigContextLoader.class)
public class IocTest {

	@Resource(name = "getHP")
	PrinterIF hpPrinter;

	@Resource(name = "getLG")
	PrinterIF lgPrinter;

	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void test() {
		hpPrinter.print();

		lgPrinter.print();
	}

}
