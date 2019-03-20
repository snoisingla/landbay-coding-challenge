import java.util.*;
import java.util.stream.Collectors;

public class LoanMatcher {
	
	public ArrayList<MatchedLoan> matchLoans(ArrayList<Loan> loans, ArrayList<Investment> investments) {
		Collections.sort(loans, (Loan a, Loan b) -> (a.getCompletedDate().compareTo(b.getCompletedDate())));
		Collections.sort(investments, (Investment a, Investment b) -> a.getTerm() - b.getTerm());
		ArrayList<MatchedLoan> matchedLoans = new ArrayList<>();
		for(Loan loan : loans) {
			MatchedLoan r = matchLoan(loan, investments);
			if (r != null) matchedLoans.add(r);
		}
		return matchedLoans;
	}
	
	private MatchedLoan matchLoan(Loan loan, ArrayList<Investment> investmentsList) {
		ArrayList<Investment> eligibleSortedInvestments = investmentsList.stream()
				.filter(investment -> investment.isFulfillingLoanRequirements(loan))
				.collect(Collectors.toCollection(ArrayList::new));
		
		ArrayList<Investment> matchedInvestments = new ArrayList<>();
		int loanAmount = loan.getAmount();
		for(Investment investment : eligibleSortedInvestments) {

			int fulfilledAmount = Math.min(loanAmount, investment.getAmount());
			Investment investorFundingTheLoan = new Investment(investment.getInvestor(), fulfilledAmount, investment.getProductType(), investment.getTerm());
			matchedInvestments.add(investorFundingTheLoan);
			
			if (fulfilledAmount == loanAmount) {
				//loan has been fulfilled	
				MatchedLoan result = new MatchedLoan(loan, matchedInvestments);				
				investment.setAmount(investment.getAmount() - fulfilledAmount); //eg ->loan 50, investment 70 or both equal
				return result;
			}
			//loan is not fulfilled, move to next investor	
			loanAmount = loanAmount - fulfilledAmount; //eg -> loan 50, investment 20
			investmentsList.remove(investment); //fulfilled investment
		}
		return null;
	}
}