package creature;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter @NoArgsConstructor
public class PregnantDog extends Dog {
	Animal baby;
	public PregnantDog(String name, double barkVolume, Animal baby){
		super(name, barkVolume);
		this.baby = baby;
	}
}
