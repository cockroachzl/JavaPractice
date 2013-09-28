import java.util.Arrays;
import java.util.Collection;
import java.util.EmptyStackException;


public class Stack<E> {
	private E[] elements;
	private int size = 0;
	private static final int DEFAULT_INITIAL_CAPACITY = 16;
	
	public Stack() {
		elements = (E[])new Object[DEFAULT_INITIAL_CAPACITY];
	}
	
	public void push(E e){
		ensureCapacity();
		elements[size++] = e;
	}
	
	public void pushAll(Iterable<? extends E> src) {
		for (E e : src)
			push(e);
	}

	public E pop(){
		if (size == 0)
			throw new EmptyStackException();
		E result = elements[--size];
		elements[size] = null;
		return result;
	}
	
	public void popAll(Collection<? super E> dst) {
		while (!isEmpty())
		dst.add(pop());
	}
	
	public boolean isEmpty(){
		return size == 0;
	}
	
	private void ensureCapacity() {
		if (elements.length == size)
			elements = Arrays.copyOf(elements, 2 * size + 1);
	}
}
