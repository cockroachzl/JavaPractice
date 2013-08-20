package com.leonzhang;
import java.util.*;

public class TestGenerics2 {
	public void go(){
		ArrayList<Animal> animals = new ArrayList<Animal>();
		animals.add(new Dog());
		animals.add(new Cat());
		animals.add(new Dog());
		takeAnimals(animals);
	}
	
	public void takeAnimals(ArrayList<? extends Animal> animals) {
		for (Animal a : animals)
			a.eat();
	}
	
	public static void main(String[] args){
		new TestGenerics2().go();
	}
}

abstract class Animal {
	void eat() {
		System.out.println("animal eating");
	}
}

class Dog extends Animal {
	void bark() { }
}

class Cat extends Animal {
	void meow() { }
}