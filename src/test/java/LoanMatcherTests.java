import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import java.time.LocalDateTime;
import java.util.ArrayList;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;


public class LoanMatcherTests {
	
	LoanMatcher loanMatcher;
	ArrayList<Loan> loans;
	ArrayList<Investment> investments;
	
	@Before
	public void setUp() {
		loanMatcher = new LoanMatcher();
		Loan loan1 = new Loan(1,500,"FIXED",12,LocalDateTime.now().toLocalDate());
		Loan loan2 = new Loan(1,1000,"TRACKER",12,LocalDateTime.now().toLocalDate().plusDays(1));
		loans = new ArrayList<>();
		loans.add(loan1);
		loans.add(loan2);
		
		
		Investment investment1 = new Investment("Test1",500,"FIXED",10);
		Investment investment2 = new Investment("Test2",1000,"TRACKER",18);
		investments = new ArrayList<>();
		investments.add(investment1);
		investments.add(investment2);
	}
	
	@Test
	public void test_matchLoans_loanAmountEqualstotalInvestedAmount() {
		
		ArrayList<MatchedLoan> matchedLoans = loanMatcher.matchLoans(loans, investments);
		for(MatchedLoan ml : matchedLoans) {
			int loanAmount = ml.getLoan().getAmount();
			int totalInvestedAmount = 0;
			ArrayList<Investment> investors = ml.getInvestors();
			for(Investment i : investors) {
				totalInvestedAmount += i.getAmount();
			}
			assertEquals(loanAmount, totalInvestedAmount);
		}
	}
	
	@Test
	public void test_matchLoans_matchProductType() {
		
		ArrayList<MatchedLoan> matchedLoans = loanMatcher.matchLoans(loans, investments);
		for(MatchedLoan ml : matchedLoans) {
			String loanProductType = ml.getLoan().getProduct();
			String investmentProductType = "";
			ArrayList<Investment> investors = ml.getInvestors();
			for(Investment i : investors) {
				investmentProductType = i.getProductType();
			}
			assertEquals(loanProductType, investmentProductType);
		}	
	}
	
	@Test
	public void test_matchLoans_checkProductTermEligbility() {
		
		ArrayList<MatchedLoan> matchedLoans = loanMatcher.matchLoans(loans, investments);
		for(MatchedLoan ml : matchedLoans) {
			int loanTerm = ml.getLoan().getTerm();
			int investmentTerm = 0;
			ArrayList<Investment> investors = ml.getInvestors();
			for(Investment i : investors) {
				investmentTerm = i.getTerm();
			}
			assertTrue(investmentTerm >= loanTerm);
		}
	}
	
	@Test
	public void test_matchLoans_sortByTerm() {
		Loan loan1 = new Loan(1,1000,"FIXED",12,LocalDateTime.now().toLocalDate());
		Loan loan2 = new Loan(1,1000,"FIXED",18,LocalDateTime.now().toLocalDate().plusDays(1));
		loans = new ArrayList<>();
		loans.add(loan1);
		loans.add(loan2);
		
		
		Investment investment1 = new Investment("Test1",500,"FIXED",18);
		Investment investment2 = new Investment("Test2",1000,"TRACKER",12);
		Investment investment3 = new Investment("Test3",500,"FIXED",18);

		investments = new ArrayList<>();
		investments.add(investment1);
		investments.add(investment2);
		investments.add(investment3);
		
		ArrayList<MatchedLoan> matchedLoans = loanMatcher.matchLoans(loans, investments);
		
		for(MatchedLoan ml : matchedLoans) {
			ArrayList<Investment> investors = ml.getInvestors();
			assertTrue(investors.size()==2);
		}	
	}
	
	@After
	public void tearDown() {
		loanMatcher = null;
		loans = null;
		investments = null;
	}
}