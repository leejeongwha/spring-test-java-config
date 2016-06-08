package com.naver.test.circular;

import org.springframework.stereotype.Service;

@Service
public class ClassA {
	private ClassB classB;

	// @Autowired
	// public ClassA(ClassB classB) {
	// this.classB = classB;
	// }
}
