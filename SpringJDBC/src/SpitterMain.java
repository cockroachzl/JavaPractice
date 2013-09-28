import java.io.File;
import java.util.Iterator;
import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

public class SpitterMain {

	public static void main(String[] args) {
		File file = new File("resources/dataSource-context.xml");
		System.out.println("exist : " + file.exists());
		ApplicationContext context = new FileSystemXmlApplicationContext("resources/dataSource-context.xml");
		SpitterDao spitterDao = (SpitterDao) context.getBean("s0");;
		if(!spitterDao.isSpitterCreated())
			spitterDao.createSpitterTable();
//		Spitter s1 = new Spitter();
//		s1.setUsername("cockroachzl");
//		s1.setPassword("123456");
//		s1.setFullName("Liang Zhang");
//		spitterDao.addSpitter(s1);
//		
//		Spitter s0 = spitterDao.getSpitterById(0);
//		System.out.println(s0);
		List<Spitter> spitters = spitterDao.findAllSpitters();
		for (Iterator<Spitter> iter = spitters.iterator(); iter.hasNext(); ){
			System.out.println(iter.next());
		}
	}

}
