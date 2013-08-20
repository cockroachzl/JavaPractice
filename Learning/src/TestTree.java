import java.util.*;

public class TestTree {
	public static void main(String[] args){
		Book b0 = new Book("Calculus");
		Book b1 = new Book("Analysis");
		Book b2 = new Book("Topology");
		TreeSet<Book> books = new TreeSet<Book>();
		books.add(b0);
		books.add(b1);
		books.add(b2);
		System.out.println(books);
		
		TreeSet<Book> books2 = new TreeSet<Book>(new BookComp());
		books2.addAll(books);
		System.out.println(books2);
	}
}

class Book implements Comparable<Book>{
	static int count = 0;
	String title;
	int id;
	public Book(String t) {
		title = t;
		id = count++;
	}
	public int compareTo(Book rhs){
		return title.compareTo(rhs.title);
	}
	public String toString() {
		return title + " " + Integer.toString(id);
	}
}

class BookComp implements Comparator<Book> {
	public int compare(Book lhs, Book rhs){
		return lhs.id - rhs.id;
	}
}