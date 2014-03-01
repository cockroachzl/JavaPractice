package creature;
import lombok.Getter;
import lombok.Setter;

import org.codehaus.jackson.annotate.JsonTypeInfo;


@JsonTypeInfo(use=JsonTypeInfo.Id.CLASS, include=JsonTypeInfo.As.PROPERTY, property="@class")
public interface Animal { // All animals have names, for our demo purposes...
	String getName();
	
}