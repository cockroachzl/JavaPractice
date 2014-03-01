package knowledge;

import lombok.Getter;

@Getter 
public class PaperBook extends Book{
	int weight;
	public PaperBook(String title, int weight) {
		super(title);
		this.weight = weight;
	}
}
