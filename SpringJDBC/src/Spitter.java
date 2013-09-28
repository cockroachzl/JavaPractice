import java.util.List;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class Spitter {
	private Long id;
	private String username;
	private String password;
	private String fullName;
	private List<Spittle> spittles;
	private String email;  
	private boolean updateByEmail;

	@Override
	public boolean equals(Object obj) {
		Spitter other = (Spitter) obj;
		return other.fullName.equals(fullName) && other.username.equals(username) && other.password.equals(password);
	}

	@Override
	public int hashCode() {
		// TODO Auto-generated method stub
		return super.hashCode();
	}
}
