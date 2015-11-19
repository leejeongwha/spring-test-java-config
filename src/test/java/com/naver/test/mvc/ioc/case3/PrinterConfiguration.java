package com.naver.test.mvc.ioc.case3;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class PrinterConfiguration {
	@Bean
	public PrinterIF getHP() {
		HPPrinter printer = new HPPrinter();
		return printer;
	}

	@Bean
	public PrinterIF getLG() {
		LGPrinter printer = new LGPrinter();
		return printer;
	}
}
