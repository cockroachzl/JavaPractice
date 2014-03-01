import lombok.Getter;
import lombok.Setter;

import org.codehaus.jackson.annotate.JsonTypeInfo;


@Getter @Setter
@JsonTypeInfo(use=JsonTypeInfo.Id.CLASS, include=JsonTypeInfo.As.PROPERTY, property="@class")
public class Animal { // All animals have names, for our demo purposes...
	String name;

	protected Animal() {
	}
}

@Getter @Setter
class Dog extends Animal {
	public double barkVolume; // in decibels

	public Dog(double barkVolume) {
		this.barkVolume = barkVolume;
	}
}

@Getter @Setter
class Cat extends Animal {
	boolean likesCream;
	public int lives;

	public Cat() {
	}
}