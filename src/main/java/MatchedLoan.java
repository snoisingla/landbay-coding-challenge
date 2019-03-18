import java.util.ArrayList;

public class MatchedLoan {
	Loan loan;
	ArrayList<Investment> investors;
	
	public MatchedLoan(Loan loan, ArrayList<Investment> investors) {
		super();
		this.loan = loan;
		this.investors = investors;
	}

	public Loan getLoan() {
		return loan;
	}

	public ArrayList<Investment> getInvestors() {
		return investors;
	}
}
