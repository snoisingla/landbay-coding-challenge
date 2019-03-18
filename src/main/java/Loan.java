import java.time.LocalDate;

public class Loan {
	int id;
	int amount;
	String product;
	int term;
	LocalDate completedDate; //TODO : change to date
	
	public Loan(int id, int amount, String product, int term, LocalDate completedDate) {
		super();
		this.id = id;
		this.amount = amount;
		this.product = product;
		this.term = term;
		this.completedDate = completedDate;
	}

	public int getId() {
		return id;
	}

	public int getAmount() {
		return amount;
	}

	public void setAmount(Integer amount) {
		this.amount = amount;
	}

	public String getProduct() {
		return product;
	}

	public int getTerm() {
		return term;
	}

	public LocalDate getCompletedDate() {
		return completedDate;
	}	
	
}
