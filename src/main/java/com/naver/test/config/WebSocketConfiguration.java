package com.naver.test.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;

import com.naver.test.websock.controller.MyHandler;
import com.naver.test.websock.controller.P1Handler;

@Configuration
@EnableWebSocket
public class WebSocketConfiguration implements WebSocketConfigurer {
	@Override
	public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
		registry.addHandler(myHandler(), "/echo").withSockJS();
		registry.addHandler(myHandler(), new String[]{"/echo1","/echo2"}).withSockJS();

		registry.addHandler(p1Handler(), "/chat1").withSockJS();
		registry.addHandler(p2Handler(), "/chat2").withSockJS();
		registry.addHandler(p3Handler(), "/chat3").withSockJS();
		registry.addHandler(p4Handler(), "/chat4").withSockJS();
		registry.addHandler(p5Handler("{chatNo}"), "/chat/{chatNo}").withSockJS();
	}

	@Bean
	public WebSocketHandler myHandler() {
		return new MyHandler();
	}

	@Bean
	public WebSocketHandler p1Handler() {
		return new P1Handler();
	}
	@Bean
	public WebSocketHandler p2Handler() {
		return new P1Handler();
	}
	@Bean
	public WebSocketHandler p3Handler() {
		return new P1Handler();
	}
	@Bean
	public WebSocketHandler p4Handler() {
		return new P1Handler();
	}
	@Bean
	public WebSocketHandler p5Handler(String key) {
		return new P1Handler();
	}
}
