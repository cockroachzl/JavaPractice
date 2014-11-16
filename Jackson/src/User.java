import lombok.Data;
import lombok.ToString;

@Data
public class User {
    public enum Gender { MALE, FEMALE };
    @Data
    public static class Name {
      private String first, last;
    }

    private Gender gender;
    private Name name;
    private boolean isVerified;
    private byte[] userImage;
}