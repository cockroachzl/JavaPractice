
public class TestEnum {
	public static void main(String[] args){
		Weekdays[] w  = Weekdays.values();
		for (int i = 0; i < w.length; ++i)
			System.out.println(w[i]);
		System.out.println(Weekdays.valueOf("Monday"));
		Weekdays w0 = Weekdays.Monday;
		System.out.println(w0.name());
		
		
		TrafficLights tl0 = TrafficLights.Green;
		System.out.println(tl0.getColor().toString());
	}
}

enum Weekdays {Monday, Tuesday, Wednesday, Thursday, Friday, Saturday, Sunday}

class Color {
	private int value;
	private String name;
	Color(int value, String name){
		this.value = value;
		this.name = name;
	}
	public String toString() {
		return "Value: " + value + " Name: " + name;
	}
}
enum TrafficLights {
	Green(0x00FF00, "green"), 
	Yellow(0xFFFF00, "yellow"), 
	Red(0xff0000, "red");
	Color color;
	TrafficLights(int value, String name){
		color = new Color(value, name);
	}
	Color getColor(){
		return color;
	}
}

enum TrafficLights2 {
	Green {
		Color getColor(){
			return new Color(0x00ff00, "green");
		}
	}, 
	Yellow {
		Color getColor(){
			return new Color(0xFFFF00, "yellow");
		}
	}, 
	Red {
		Color getColor(){
			return new Color(0xff0000, "red");
		}
	};

	abstract Color getColor();
}
