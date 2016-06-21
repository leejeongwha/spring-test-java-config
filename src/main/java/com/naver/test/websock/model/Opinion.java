package com.naver.test.websock.model;
import java.io.IOException;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
//import com.fasterxml.jackson.core.JsonFactory;
//import com.fasterxml.jackson.core.JsonGenerator;
//import com.fasterxml.jackson.databind.JsonNode;
//import com.fasterxml.jackson.databind.node.ObjectNode;

public class Opinion {

	private String userId;
	private String userTimeStamp;// javascript var d = new Date(); var n = d.getTime();
	private String message; // 메시지
	private int onair; // 낱글자 전송 on/off
	private String type; // so 같은의견, do 다른 의견, po 개인적 의견, oo 객관적의견
	private String targetUser; //only one

	public String getUserTimeStamp() {
		return userTimeStamp;
	}

	public void setUserTimeStamp(String userTimeStamp) {
		this.userTimeStamp = userTimeStamp;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public int getOnair() {
		return onair;
	}

	public void setOnair(int onair) {
		this.onair = onair;
	}

	public String getType() {
		if(StringUtils.isEmpty(type)) {
			return "all";
		}
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getTargetUser() {
		return targetUser;
	}

	public void setTargetUser(String targetUser) {
		this.targetUser = targetUser;
	}

	/**
	 * @see http://arisu1000.tistory.com/27710
	 * @param source
	 * @return
	 */
	public static Opinion convertMessage(String source) {
		Opinion vo = new Opinion();
		ObjectMapper mapper = new ObjectMapper();
		try {
			vo = mapper.readValue(source, Opinion.class);
		} catch (JsonParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JsonMappingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return vo;
	}
	public String convertJSON(Opinion vo) {
		ObjectMapper mapper = new ObjectMapper();

		String result = "{}";
		try {
			result = mapper.writeValueAsString(vo);
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return result;
	}

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

}
