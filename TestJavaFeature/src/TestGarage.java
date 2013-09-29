
public class TestGarage {
	public static void main(String[] args){
		Vehicle v0 = new Vehicle(new GasTank(20,2),"Liang");
		Vehicle v1 = new Vehicle(new GasTank(16,16), "Huajuan");
		Vehicle v2 = new Vehicle(new Battery(10,10), "Liuliu");
		
		Garage g0 = new Garage();
		g0.park(0,v0);
		g0.park(1, v1);
		g0.park(2, v2);
		System.out.println(g0);
		
		Garage g1 = g0.clone();
		System.out.println(g1);
	}
}

class Garage implements Cloneable {
	private int capacity = 10; //in C++, member with a in-class initializer must be const
	Vehicle[] vehicles = new Vehicle[capacity];
	boolean park(int spot, Vehicle v){
		assert(spot>=0 && spot<capacity);
		if (vehicles[spot] == null) {
			vehicles[spot] = v;
			return true;
		}
		else {
			return false;
		}
	}
	boolean exit(int spot){
		assert(spot>=0 && spot<capacity);
		if (vehicles[spot] == null){
			return false;
		}
		else {
			vehicles[spot] = null;
			return true;
		}
	}
	public Garage clone() {
		Garage cloned = null; 
		try {
			cloned = (Garage)super.clone();
			cloned.vehicles = vehicles.clone();
			for(int i = 0; i < capacity; ++i){
				if (vehicles[i] != null)
					cloned.vehicles[i] = vehicles[i].clone();
			}
		} catch (CloneNotSupportedException ex) {
			ex.printStackTrace();
		}
		return cloned;
	}
	public String toString(){
		String str = "";
		for (int i = 0; i < capacity; ++i){
			str += "["+vehicles[i]+"]";
		}
		return str;
	}
}