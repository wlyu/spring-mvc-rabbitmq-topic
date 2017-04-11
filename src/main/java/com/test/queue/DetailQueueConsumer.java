package com.test.queue;

import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageListener;

public class DetailQueueConsumer implements MessageListener {
	public DetailQueueConsumer(){
		
	}
	public DetailQueueConsumer(String tagName){
		this.tagName = tagName;
	}
	private String tagName;
	
    public void onMessage(Message message) {
        System.out.println(tagName+"====="+this.toString()+"====="+Thread.currentThread().getId()+"====DetailQueueConsumer: ====" + new String(message.getBody()));
    }
}