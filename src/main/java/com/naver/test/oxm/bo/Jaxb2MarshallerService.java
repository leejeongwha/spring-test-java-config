package com.naver.test.oxm.bo;

import java.io.StringReader;
import java.io.StringWriter;

import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;
import org.springframework.stereotype.Service;

import com.naver.test.oxm.model.BoardUser;

@Service
public class Jaxb2MarshallerService {
	private final Logger logger = LoggerFactory.getLogger(Jaxb2MarshallerService.class);

	@Autowired
	private Jaxb2Marshaller jaxb2Marshaller;

	public void marshal() throws Exception {
		BoardUser boardUser = new BoardUser();
		boardUser.setId(1L);
		boardUser.setPasswd("1234");
		boardUser.setRole("ADMIN");
		boardUser.setUserName("Jane");
		boardUser.setAge(20);

		StringWriter string = new StringWriter();
		jaxb2Marshaller.marshal(boardUser, new StreamResult(string));

		logger.info(string.toString());
	}

	public <T> void unmarshal(Class<T> clazz) {
		String body = "<?xml version='1.0' encoding='UTF-8' standalone='yes'?><boardUser><age>20</age><id>1</id><passwd>1234</passwd><role>ADMIN</role><userName>Jane</userName></boardUser>";
		T t = (T) jaxb2Marshaller.unmarshal(new StreamSource(new StringReader(body)));

		logger.info(t.toString());
	}
}
