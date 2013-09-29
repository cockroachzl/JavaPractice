import static java.lang.System.out;
public class TestVehicle {
	public static void main(String[] args) {
		
		Vehicle v0 = new Vehicle(new GasTank(20,2),"Liang");
		Vehicle v1 = new Vehicle(new GasTank(16,16), "Huajuan");
		Vehicle v2 = new Vehicle(new Battery(10,10), "Liuliu");
		v0.turn(Vehicle.Direction.TURN_LEFT);
		out.println(v0.toString());
		out.println(v1.toString());
		out.println(v2.toString());
		Vehicle[] vehicles = new Vehicle[args.length];
		for(int i = 0; i < args.length; ++i){
			vehicles[i] = new Vehicle(new GasTank(18,0),args[i]);
			out.println(vehicles[i].toString());
		}
		
		Vehicle v3 = new PassengerVehicle("Liang");
		out.println(v3);
		Vehicle v4 = new Vehicle(new GasTank(20,2), "Liang");
		out.println(v0.equals(v4));
		Vehicle v5 = (Vehicle) v0.clone();
		out.println(v5);
	}
}
abstract class EnergySource implements Cloneable {
	double capacity;
	double current;
	EnergySource(double capacity, double current){
		this.capacity = capacity;
		this.current = current;
	}
	abstract boolean empty();
	public EnergySource clone() throws CloneNotSupportedException{
		return (EnergySource) super.clone();
	}
}
class GasTank extends EnergySource {
	GasTank(double capacity, double current) {
		super(capacity, current);
	}
	boolean empty() { return Math.abs(current-0) < 0.1; }
	void refill() { current = capacity; }
	public boolean equals(Object rhs){
		return (rhs instanceof GasTank) && capacity == ((GasTank)rhs).capacity && current == ((GasTank)rhs).current;
	}
}
class Battery extends EnergySource {
	Battery(double capacity, double current) {
		super(capacity, current);
	}
	boolean empty() { return current > 1; }
}
class Vehicle implements Cloneable {
	enum Direction { TURN_LEFT, TURN_RIGHT };
	private double speed;
	private double degree;
	private String owner;
	private static int next_id = 0;
	private final int id;
	private EnergySource energy_source;
	static int getHigestID() { return next_id - 1; }
	Vehicle(EnergySource es) {
		id = next_id++;
		energy_source = es;
	}
	Vehicle(EnergySource es, String owner) { 
		this(es);
		this.owner = owner; 
	}
	public boolean equals(Vehicle rhs) {
		return owner == rhs.owner && energy_source.equals(rhs.energy_source);
	}
	double getSpeed() { return speed; }
	void changeSpeed(double speed) { this.speed = speed; } 
	boolean start() { 
		if (energy_source.empty()) {
			return false;
		} else {
			speed = 5;
			return true;
		}
	}
	void stop() { speed = 0; }
	double getDegree() { return degree; }
	void setDegree(double degree) { this.degree = degree; }
	void turn (double degree) {
		this.degree += degree;
	}
	void turn (Direction d) {
		switch (d){
		case TURN_LEFT:
			degree += 30;
			break;
		case TURN_RIGHT:
			degree -= 30;
			break;
		}
	}
	public String toString() {
		return owner + " VIN: " + id + " Speed: " + speed + " Degree: " + degree + " HashCode: " + hashCode();
	}
	public Vehicle clone()  {
		Vehicle v = null;
		try {
			v = (Vehicle) super.clone();
			v.energy_source = (EnergySource) energy_source.clone();
		} catch(CloneNotSupportedException ex) {
			out.println("haha");
			out.println(ex);
		}
		return v;
	}
}

class PassengerVehicle extends Vehicle {
	private int capacity;
	private int occupied;
	PassengerVehicle(String owner) {
		super(new GasTank(25, 10), owner);
		capacity = 20;
		occupied = 0;
	}
	int capacity() {
		return capacity;
	}
	int occupied() {
		return occupied;
	}
	public String toString() {
		return super.toString() + "\tCapacity: " + capacity + "\tOccupied" + occupied;
	}
}


