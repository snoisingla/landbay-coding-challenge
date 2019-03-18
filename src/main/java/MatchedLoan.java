import java.util.ArrayList;

public class MatchedLoan {
	Loan loan;
	ArrayList<Investment> investments;
	
	public MatchedLoan(Loan loan, ArrayList<Investment> investments) {
		super();
		this.loan = loan;
		this.investments = investments;
	}

	public Loan getLoan() {
		return loan;
	}

	public ArrayList<Investment> getInvestments() {
		return investments;
	}
}
