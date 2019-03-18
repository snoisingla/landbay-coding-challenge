public class Investment {
	String investor;
	int amount;
	String productType;
	int term;
	
	public Investment(String investor, int amount, String productType, int term) {
		super();
		this.investor = investor;
		this.amount = amount;
		this.productType = productType;
		this.term = term;
	}

	public String getInvestor() {
		return investor;
	}

	public int getAmount() {
		return amount;
	}

	public void setAmount(Integer amount) {
		this.amount = amount;
	}

	public String getProductType() {
		return productType;
	}

	public int getTerm() {
		return term;
	}
	
	public boolean isFulfillingLoanRequirements(Loan loan) {
		return loan.getProduct().equals(this.getProductType()) &&
				loan.getTerm() <= this.getTerm();
	}
	
}
