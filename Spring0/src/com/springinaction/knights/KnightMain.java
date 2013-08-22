package com.springinaction.knights;

import java.io.File;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

public class KnightMain {
	public static void main(String[] args) {
		File file = new File("knights.xml");
		System.out.println("exist : " + file.exists());
		ApplicationContext context = new FileSystemXmlApplicationContext("knights.xml");
		Knight knight = (Knight) context.getBean("knight");
		knight.embarkOnQuest();
	}
}
