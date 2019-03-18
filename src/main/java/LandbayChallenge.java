import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class LandbayChallenge{
	
	public static ArrayList<Investment> getInvestmentsFromFile(String fileName){
		ArrayList<Investment> investmentsList = new ArrayList<>();
		try{
			File inputFile = new File(fileName);
			BufferedReader reader = new BufferedReader(new FileReader(inputFile));
			reader.readLine(); //ignore first line

			String s;
	     	while ((s = reader.readLine()) != null) {
	     		String[] investmentFieldsArray = s.split(",");
	     		Investment investment = 
	     		new Investment(investmentFieldsArray[0],Integer.parseInt(investmentFieldsArray[1]),
	     			investmentFieldsArray[2],Integer.parseInt(investmentFieldsArray[3]));
	     		investmentsList.add(investment);
	     	}
	     	reader.close();
		}
		catch(IOException e){
			 e.printStackTrace();
		}
		return investmentsList;
	}

	public static ArrayList<Loan> getLoansFromFile(String fileName){
		ArrayList<Loan> loansList = new ArrayList<>();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		try{
			File inputFile = new File(fileName);
			BufferedReader reader = new BufferedReader(new FileReader(inputFile));
			reader.readLine(); //to skip first line

			String s;
	     	while ((s = reader.readLine()) != null) {
	     		String[] loanFieldsArray = s.split(",");

	     		Loan loan = 
	     		new Loan(Integer.parseInt(loanFieldsArray[0]),Integer.parseInt(loanFieldsArray[1]),loanFieldsArray[2],
	     			Integer.parseInt(loanFieldsArray[3]),LocalDate.parse(loanFieldsArray[4], formatter));
	     		loansList.add(loan);
	     	}
	     	reader.close();
		}
		catch(IOException e){
			 e.printStackTrace();
		}
		return loansList;
	}
	
	public static void main(String[] args) {
		ArrayList<Investment> investments =  getInvestmentsFromFile("./resource/investmentRequests-data.csv");
		ArrayList<Loan> loans = getLoansFromFile("./resource/loans-data.csv");
		
		LoanMatcher loanMatcher = new LoanMatcher();
		List<MatchedLoan> matchedLoans = loanMatcher.matchLoans(loans, investments);
				
		Gson prettyGson = new GsonBuilder().setPrettyPrinting().create();
		System.out.println(prettyGson.toJson(matchedLoans));
		
	}
}
