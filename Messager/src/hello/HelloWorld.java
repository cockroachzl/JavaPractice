package hello;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import persistence.HibernateUtil;

public class HelloWorld {
	public static void main(String[] args) {
		SessionFactory factory = HibernateUtil.getSessionFactory();
		
		Long msgId = saveMessage(factory, "Hello World");
		
		listMessage(factory);
		
		setCurrAndNextMessage(factory, msgId, "Hello World+", "I am liuliu");
		
		listMessage(factory);
		
		HibernateUtil.shutdown();
	}
	
	private static Long saveMessage(SessionFactory factory, String str) {
		Session session = factory.openSession();
		
		Transaction tx = session.beginTransaction();
		
		Message message = new Message(str);
		Long msgId = (Long)session.save(message);
		
		tx.commit();
		session.close();
		return msgId;
	}
	
	private static void listMessage(SessionFactory factory) {
		Session newSession = factory.openSession();
		Transaction newTransaction = newSession.beginTransaction();
		
		Query query = newSession.createQuery("from Message m order by m.text asc");
		@SuppressWarnings("unchecked")
		List<Message> messages = query.list();
		
		System.out.println(messages.size() + " message(s) found:");
		for (Message m : messages) {
			System.out.println(m.getText());
		}
		newTransaction.commit();
		newSession.close();
	}
	
	private static void setCurrAndNextMessage(SessionFactory factory, 
			Long msgId, String currStr, String nextStr) {
		Session session = factory.openSession();
		Transaction tx = session.beginTransaction();
		try {
			Message msg = (Message) session.get(Message.class, msgId);
			msg.setText(currStr);
			msg.setNextMessage(new Message(nextStr));
			tx.commit();
		}
		catch (Exception e) {
			tx.rollback();
			throw e;
		}
		finally {
			session.close();
		}
	}
}
