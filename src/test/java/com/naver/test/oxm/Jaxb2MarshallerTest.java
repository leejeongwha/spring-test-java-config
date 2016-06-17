package com.naver.test.oxm;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import com.naver.test.config.AppConfiguration;
import com.naver.test.oxm.bo.Jaxb2MarshallerService;
import com.naver.test.oxm.model.BoardUser;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(classes = AppConfiguration.class)
public class Jaxb2MarshallerTest {
	@Autowired
	private Jaxb2MarshallerService jaxb2MarshallerService;

	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void testMarshall() throws Exception {
		jaxb2MarshallerService.marshal();
	}

	@Test
	public void testUnmarshall() throws Exception {
		jaxb2MarshallerService.unmarshal(BoardUser.class);
	}
}
