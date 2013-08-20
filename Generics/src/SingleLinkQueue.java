
class SingleLinkQueue<E> {
	protected Cell<E> head;
	protected Cell<E> tail;
	protected int size = 0;
	
	public void push(E item){
		Cell<E> cell = new Cell<E>(item);
		if (tail == null)
			head = tail = cell;
		else {
			tail.setNext(cell);
			tail = cell;
		}
		++size;
	}
	
	public E pop() {
		if (head == null)
			return null;
		Cell<E> cell = head;
		head = head.getNext();
		if (head == null)
			tail = null;
		--size;
		return cell.getElement();
	}
	
	public E front() {
		return head.getElement();
	}
	
	public E back() {
		return tail.getElement();
	}
	
	public boolean empty() {
		return tail == null;
	}
}

class TestSingleLinkQueue {
	static void test(){
		SingleLinkQueue<String> queue = new SingleLinkQueue<String>();
		queue.push("Hello");
		queue.push("Liang");
		queue.push("Zhang");
		while(!queue.empty()) {
			System.out.println(queue.front());
			queue.pop();
		}
	}
}
