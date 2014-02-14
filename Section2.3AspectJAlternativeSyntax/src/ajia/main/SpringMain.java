package ajia.main;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import ajia.messaging.MessageCommunicator;

public class SpringMain {
	public static void main(String[] args) {
		ApplicationContext context = new ClassPathXmlApplicationContext(
				"applicationContext.xml");
		MessageCommunicator messageCommunicator = (MessageCommunicator) context
				.getBean("messageCommunicator");
		messageCommunicator.deliver("Wanna learn AspectJ?");
		messageCommunicator.deliver("Harry", "having fun?");
	}
}