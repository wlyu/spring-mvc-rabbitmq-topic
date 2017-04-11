package com.test.queue;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.core.ChannelAwareMessageListener;
import org.springframework.stereotype.Service;

import com.rabbitmq.client.Channel;

@Service
public class Consumor implements ChannelAwareMessageListener {
	public Consumor(){
		
	}
	private String str;
	public Consumor(String str){
		this.str =str;
	}
	public void onMessage(Message message, Channel channel) {
		try {
			System.out.println(str+"=======consumor:" + new String(message.getBody(), "utf-8"));

		} catch (UnsupportedEncodingException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} finally {
			try {
				channel.basicAck(message.getMessageProperties().getDeliveryTag(), false);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	}
}
