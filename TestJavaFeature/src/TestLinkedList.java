import static java.lang.System.out;

public class TestLinkedList {
	public static void main(String[] args) {
		Vehicle v0 = new Vehicle(new GasTank(20,2),"Liang");
		Vehicle v1 = new Vehicle(new GasTank(16,16), "Huajuan");
		Vehicle v2 = new Vehicle(new Battery(10,10), "Liuliu");
		LinkedList l = new LinkedList();
//		l.push_front(v0);
//		l.push_front(v1);
//		l.push_front(v2);
//		while(!l.empty()){
//			out.println(l.pop_front());
//		}
		l.push_front(v0,v1,v2);
		out.println(l.toString());
		LinkedList l2 = l.clone();
		out.println(l2.toString());
	}
}

class LinkedList {
	class Cell {
		private Object value;
		private Cell next = null;
	}
	private Cell head = null;
	private int size = 0;
	LinkedList() {
		head = null;
		size = 0;
	}
	void push_front(Object item){
		Cell curr = new Cell();
		curr.value = item;
		curr.next = head;
		head = curr;
		++size;
	}
	void push_front(Object ... items){
		for (int i = items.length-1; i >= 0; --i) {
			push_front(items[i]);
		}
	}
	Object pop_front() throws RuntimeException {
		if (empty())
			throw new RuntimeException("pop_front from an empty linked list");
		else{
			Cell curr = head;
			head = head.next;
			--size;
			return curr.value;
		}
	}
	boolean empty() {
		return size == 0;
	}
	int size() { return size; }
	Object next(Cell curr) {
		if(curr != null)
			return curr.next.value;
		else 
			return null;
	}
	
	public String toString() {
		Cell iter = head;
		String str = "";
		while(iter != null) {
			str += "[" + iter.value.toString() + "]\t";
			iter = iter.next;
		}
		str += '\n';
		return str;
	}
	public LinkedList clone() {
		LinkedList cloned = null;

		cloned = new LinkedList();
		LinkedList temp = new LinkedList();
		Cell curr = head;
		while (curr != null){
			temp.push_front(curr.value);;
			curr = curr.next;
		}
		curr = temp.head;
		while (curr != null){
			cloned.push_front(curr.value);
			curr = curr.next;
		}

//		} catch (CloneNotSupportedException ex) {
//			out.println(ex);
//		}
		return cloned;
	}
}


