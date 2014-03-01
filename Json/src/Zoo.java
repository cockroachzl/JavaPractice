import org.codehaus.jackson.annotate.JsonTypeInfo;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
@JsonTypeInfo(use=JsonTypeInfo.Id.CLASS, include=JsonTypeInfo.As.PROPERTY, property="@class")
public class Zoo {
	 Animal animal;
	 String name = "a zoo name";
	 public Zoo(Animal animal) {
		 this.animal = animal;
	 }
	 public Zoo() {
		 
	 }
}

