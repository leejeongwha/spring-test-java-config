package com.naver.test.circular;

import org.springframework.stereotype.Service;

@Service
public class ClassB {
	private ClassA classA;

	// @Autowired
	// public ClassB(ClassA classA) {
	// this.classA = classA;
	// }
}
