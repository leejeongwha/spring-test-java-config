package com.naver.test.orm.bo;

public interface BoardUserBO {
	void doTransaction();

	void doNotTransaction();

	void callTransactionMethod();
}
