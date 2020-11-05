package project1b.ver04;

public class NormalAccount extends Account {
	
	int interestRate;

	public NormalAccount(String accNum, String accName, int accBalance, int interestRate) {
		super(accNum, accName, accBalance);
		this.interestRate = interestRate;
	}
	
	public int getInterestRate() {
		return interestRate;
	}

	public void setInterestRate(int interestRate) {
		this.interestRate = interestRate;
	}
	
	@Override
	public void show() {
		super.show();
		System.out.println("기본이자>" + interestRate + "%" );
		System.out.println("=========================");
	}

	@Override
	public int deposit(int input, int balance) {
		double tempBalance = 0;
		tempBalance = balance + (balance * interestRate / 100) + input; 
		return (int)tempBalance;
	}
}
