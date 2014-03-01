import lombok.EqualsAndHashCode;
import lombok.Getter;


@Getter
@EqualsAndHashCode(callSuper=false)
public class Derived extends Base{
	String derivedName;
}