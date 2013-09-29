
public class TestSort {
	static double[] data = {7,1,2,3,4,5,6};
	public static void main(String[] args) {
		Sort s = new SelectionSort();
		SortMetrics sm = s.sort(data.clone());
		System.out.println(sm);
		System.out.println(s);
		
		s = new InsertionSort();
		sm = s.sort(data.clone());
		System.out.println(sm);
		System.out.println(s);
		
		s = new BubbleSort();
		sm = s.sort(data.clone());
		System.out.println(sm);
		System.out.println(s);
		
		s = new QuickSort();
		sm = s.sort(data.clone());
		System.out.println(sm);
		System.out.println(s);
	}

}
//template <typename T>
//class {};
abstract class Sort{
	private double[] data = null;
	private final SortMetrics metrics = new SortMetrics();
	
	public final SortMetrics sort(double[] data) {
		this.data = data;
		metrics.init();
		doSort();
		return getMetrics();
	}
	
	protected final SortMetrics getMetrics() {
		return metrics.clone();
	}
	
	protected abstract void doSort();
	
	protected final int getLength() {
		return data.length;
	}
	
	protected final double probe(int i){
		++metrics.probe;
		return data[i];
	}
	
	protected final int compare(int i, int j){
		++metrics.compare;
		if (data[i] == data[j])
			return 0;
		else if (data[i] < data[j])
			return -1;
		else
			return 1;
	}
	
	protected final void swap(int i, int j){
		++metrics.swap;
		double temp = data[i];
		data[i] = data[j];
		data[j] = temp;
	}
	
	public String toString() {
		String str = "";
		for (int i = 0; i < data.length; ++i)
			str += (data[i]+ " ");
		str += "\n";
		return str;
	}
}

class SortMetrics implements Cloneable {
	int probe;
	int swap;
	int compare;
	void init() {
		probe = swap = compare = 0;
	}
	public SortMetrics clone() {
		try {
			return (SortMetrics) super.clone(); 
		} catch (CloneNotSupportedException e) {
			throw new InternalError(e.toString());
		}
	}
	public String toString() {
		return "Probe: " + probe + " Swap: " + swap + " Compare: " + compare;
	}
}

class SelectionSort extends Sort{
	protected void doSort() {
		for (int i = 0; i < getLength(); ++i)
			for (int j = i+1; j < getLength() ; ++ j) 
				if (compare(i,j) > 0)
					swap(i,j);
	}
}
class InsertionSort extends Sort{
	protected void doSort() {
		for (int i = 1; i < getLength(); ++i) 
			for (int j = i-1; j >=0; --j) 
				if(compare(j,j+1) > 0)
					swap(j,j+1);
	}
}
class BubbleSort extends Sort{
	protected void doSort() {
		for (int i = 0; i < getLength(); ++i) 
			for (int j = getLength()-1; j > i; --j)
				if (compare(j-1,j) > 0)
					swap(j-1,j);
	}
}

class QuickSort extends Sort{
	protected void doSort() {
		quicksort(0, getLength()-1);
	}
	private void quicksort(int left, int right){
		if(left >= right)
			return;
		int last = left+1;
		for(int i = left+1; i <= right; ++i)
			if(compare(left,i)>0)
				swap(last++, i);
		swap(left,--last);
		quicksort(left,last-1);
		quicksort(last+1,right);
	}
}
