package project1b.ver03;

public class HighCreditAccount extends Account implements CustomSpecialRate {

	int interestRate;
	String creditGrade;
	
	public HighCreditAccount(String accNum, String accName, int accBalance, int interestRate, String creditGrade) {
		super(accNum, accName, accBalance);
		this.interestRate = interestRate;
		this.creditGrade = creditGrade;
	}

	public int getInterestRate() {
		return interestRate;
	}

	public void setInterestRate(int interestRate) {
		this.interestRate = interestRate;
	}

	public String getCreditGrade() {
		return creditGrade;
	}

	public void setCreditGrade(String creditGrade) {
		this.creditGrade = creditGrade;
	}
	
	@Override
	public void show() {
		super.show();
		System.out.println("기본이자>" + interestRate );
		System.out.println("신용등급>" + creditGrade);
		System.out.println("=========================");
	}

	@Override
	public int deposit(int input, int balance) {
		double returnBalance = 0;
		if (creditGrade.equalsIgnoreCase("A")) {
			returnBalance = balance + ((balance * (interestRate + CustomSpecialRate.AGRADE)) / 100) + input;
		}
		else if (creditGrade.equalsIgnoreCase("B")){
			returnBalance = balance + ((balance * (interestRate + CustomSpecialRate.BGRADE)) / 100) + input;
		}			
		else if (creditGrade.equalsIgnoreCase("C")){
			returnBalance = balance + ((balance * (interestRate + CustomSpecialRate.CGRADE)) / 100) + input;
		}
		return (int)returnBalance;
	}
}
