package hello;

import java.util.List;

import javax.persistence.*;

public class HelloWorld {
	public static void main(String[] args) {
//		SessionFactory factory = HibernateUtil.getSessionFactory();	
		EntityManagerFactory factory = Persistence.createEntityManagerFactory("helloworld");
		
		Long msgId = saveMessage(factory, "Hello World");
		
		listMessage(factory);
		
		setCurrAndNextMessage(factory, msgId, "Hello World+", "I am liuliu");
		
		listMessage(factory);
		
//		HibernateUtil.shutdown();
		factory.close();
	}
	
	private static Long saveMessage(EntityManagerFactory factory, String str) {
//		Session session = factory.openSession();
//		Transaction tx = session.beginTransaction();
		EntityManager entityManager = factory.createEntityManager();
		EntityTransaction tx = entityManager.getTransaction();
		tx.begin();
		
		Message message = new Message(str);
		entityManager.persist(message);
		entityManager.flush();

		tx.commit();
		entityManager.close();
		return message.getId();
	}
	
	private static void listMessage(EntityManagerFactory factory) {
		EntityManager entityManager = factory.createEntityManager();
		EntityTransaction tx = entityManager.getTransaction();
		tx.begin();
		
		Query query = entityManager.createQuery("select m from Message m order by m.text asc");
		@SuppressWarnings("unchecked")
		List<Message> messages = query.getResultList();
		
		System.out.println(messages.size() + " message(s) found:");
		for (Message m : messages) {
			System.out.println(m.getText());
		}
		
		tx.commit();
		entityManager.close();
	}
	
	private static void setCurrAndNextMessage(EntityManagerFactory factory, 
			Long msgId, String currStr, String nextStr) {
		EntityManager entityManager = factory.createEntityManager();
		EntityTransaction tx = entityManager.getTransaction();
		tx.begin();
		
		try {
			Message msg = entityManager.find(Message.class, msgId);
			msg.setText(currStr);
			msg.setNextMessage(new Message(nextStr));
			tx.commit();
		}
		catch (Exception e) {
			tx.rollback();
			throw e;
		}
		finally {
			entityManager.close();
		}
	}
}
