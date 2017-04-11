package webmvct.cmd;

import javax.annotation.Resource;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

@Service
@Scope("prototype")
public class Productor {

	@Resource(name = "amqpTemplate")
	private AmqpTemplate amqpTemplate;

	public void sendMessage(String message) {
		// System.out.println(message);
		amqpTemplate.convertAndSend("mq.queue", message);
	}

	public void send_Topic_Message(String message) {

		// 第二个参数为路由key(routingKey)的值，
		// 当路由可以为test321.hello.test123时，两个消费队列都可以收到消息，
		// 当值为test321.hello.aaa时，只有绑定了test321.#的队列才可以收到消息，
		// 当值为ta1.hello.test123，只有绑定了*.*.test123的队列才可收到消息
		amqpTemplate.convertAndSend("mq.topic.exchange", "test321.hello.test123", message);
	}

	public void send_Fanout_Message(String message) {
		amqpTemplate.convertAndSend("mq.fanout.exchange", "", message);
	}
	
	
	
	
	
	//-----------=================================================================
	@Value("${fangcang.hotel.id.queue}")
	protected String rutingKey;

	@Value("${zowoyoo.hotel.id.queue}")
	protected String zowoyoo_rutingKey;

	public void sendMessage_1(Object message) {

		amqpTemplate.convertAndSend("hotal.id.topic.exchange",rutingKey, message);

	}

	public void sendMessage_2(Object message) {
		
		amqpTemplate.convertAndSend("hotal.id.topic.exchange",zowoyoo_rutingKey, message);
	}
}