package com.immoc.sell.service;

import java.io.IOException;
import java.util.concurrent.CopyOnWriteArraySet;

import javax.websocket.OnClose;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;

import org.springframework.stereotype.Component;

@Component
@ServerEndpoint("/websocket")
public class WebSocket {
	private Session session;
	private static CopyOnWriteArraySet<WebSocket> webSocketSet = new CopyOnWriteArraySet<>();

	@OnOpen
	public void onOpen(Session session) {
		this.session = session;
		webSocketSet.add(this);
		System.out.println("有新的连接 总数：" + webSocketSet.size());
		// 发送消息测试
		sendMessage("发送一条后端消息");
	}

	@OnClose
	public void onClose() {

		webSocketSet.remove(this);
		System.out.println("连接断开了 总数：" + webSocketSet.size());
	}

	@OnMessage
	public void onMessage(String message) {
		System.out.println("收到客户消息：" + message);
	}

	// 发送消息 外部调用
	public void sendMessage(String message) {
		// 广播消息
		for (WebSocket webSocket : webSocketSet) {
			try {
				System.out.println("发送消息：" + message);
				webSocket.session.getBasicRemote().sendText(message, true);
			} catch (IOException e) { // TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		// 单点发送消息		
		/*
		try {
			this.session.getBasicRemote().sendText(message, true);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		*/
		 
	}

}
