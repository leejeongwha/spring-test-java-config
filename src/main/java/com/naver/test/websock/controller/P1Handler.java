package com.naver.test.websock.controller;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import com.naver.test.websock.model.Opinion;

@Component
public class P1Handler extends TextWebSocketHandler {
	final static Logger logger = LoggerFactory.getLogger(P1Handler.class);
	// 개별채팅 - connectedUsers
	private List<WebSocketSession> sessionList = new ArrayList<WebSocketSession>();

	/**
	 * 클라이언트 연결 이후에 실행되는 메소드
	 */
	@Override
	public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        // 서버에 접속한 사용자를 제한함
//        if ( sessionList.size() > 40 ) {
//            session.close();
//            return;
//        }
//        session.setTextMessageSizeLimit(140);
//        session.sendMessage(new TextMessage(session.getId() + "님이 접속했습니다."));

        System.out.println(session.getId() + "님이 접속했습니다.");
        System.out.println("연결 IP : " + session.getRemoteAddress().getHostName() );
		sessionList.add(session);
	}

	/**
	 * 클라이언트가 웹소켓 서버로 메시지를 전송했을 때 실행되는 메소드
	 * 1. Send : client -> server로 메시지를 보냄
     * 2. Emit : server -> all client로 메시지를 보냄
	 */
	@Override
	protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
		Opinion vo = Opinion.convertMessage(message.getPayload());
        String hostName = "";

        logger.debug(message.getPayload());
        System.out.println(vo.toString());

		// 연결된 모든 클라이언트에게 메시지 전송
		for (WebSocketSession sess : sessionList) {
			//sess.sendMessage(new TextMessage("websocket echo : " + message.getPayload()));

            if ( vo.getType().equals("all")) {
//                if ( !session.getId().equals(sess.getId()) ) { // 내창에도 같이 보여주자...
                	vo.setUserId(sess.getId());
                	sess.sendMessage( new TextMessage(vo.convertJSON(vo)) );
//                }
            }
            // 특정 user host 귓속말 전송
            else {
                hostName = sess.getRemoteAddress().getHostName();
                if ( vo.getTargetUser().equals(hostName) ) {
                	sess.sendMessage( new TextMessage(
                        "<span style='color:red;'>"
                        + session.getRemoteAddress().getHostName()
                        + " -> " + vo.getMessage()
                        + "</span>") );
                    break;
                }
            }
		}
	}

	/**
	 * 클라이언트 연결을 끊었을 때 실행되는 메소드
	 */
	@Override
	public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
		sessionList.remove(session);
        for (WebSocketSession webSocketSession : sessionList) {
            if ( !session.getId().equals(webSocketSession.getId()) ) {
                webSocketSession.sendMessage(
                    new TextMessage(webSocketSession.getRemoteAddress().getHostName()
                     + "퇴장했습니다.") );
            }
        }
        logger.debug(session.getId() + "님이 퇴장했습니다.");
	}
}
