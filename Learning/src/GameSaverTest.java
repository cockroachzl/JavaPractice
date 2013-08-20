import java.io.*;

public class GameSaverTest {
	public static void main(String[] args){
		GameCharacter one = new GameCharacter(50, "Elf", new String[] {"bow", "sword", "dust"});
		GameCharacter two = new GameCharacter(200, "Troll", new String[] {"bare hands", "big axe"});
		GameCharacter three = new GameCharacter(120, "Magician", new String[] {"spell", "invisibility"});
		
		try {
			ObjectOutputStream os = new ObjectOutputStream(new FileOutputStream("Game.ser"));
			os.writeObject(one);
			os.writeObject(two);
			os.writeObject(three);
			os.close();
		} catch (IOException ex) {
			ex.printStackTrace();
		}
		one = two = three = null;
		
		try {
			ObjectInputStream is = new ObjectInputStream(new FileInputStream("Game.ser"));
			one = (GameCharacter) is.readObject();
			two = (GameCharacter) is.readObject();
			three = (GameCharacter) is.readObject();
			is.close();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		
		System.out.println("One's type: " + one.getType());
		System.out.println("Two's type: " + two.getType());
		System.out.println("Three's type: " + three.getType());
	}
}

class GameCharacter implements Serializable {
	int power;
	String type;
	String[] weapons;
	
	public GameCharacter(int p, String t, String[] w){
		power = p;
		type = t;
		weapons = w;
	}
	
	public int getPower() {
		return power;	
	}
	
	public String getType() {
		return type;
	}
	
	public String getWeapons() {
		String weaponList = "";
		for (int i = 0; i < weapons.length; ++i) {
			weaponList += weapons[i] + " ";
		}
		return weaponList;
	}
}
