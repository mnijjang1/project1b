package project1b.ver03;

public abstract class Account {
	private String accNum, accName;
	private int accBalance;
	
	public Account (String accNum, String accName, int accBalance) {
		this.accNum = accNum;
		this.accName = accName;
		this.accBalance = accBalance;
	}
	
	public String getAccNum() {
		return accNum;
	}
	public void setAccNum(String accNum) {
		this.accNum = accNum;
	}
	public String getAccName() {
		return accName;
	}
	public void setAccName(String accName) {
		this.accName = accName;
	}
	public int getAccBalance() {
		return accBalance;
	}
	public void setAccBalance(int accBalance) {
		this.accBalance = accBalance;
	}
	
	public void show() {
		System.out.println("=========================");
		System.out.println("계좌번호>" + accNum);
		System.out.println("고객이름>" + accName);
		System.out.println("잔     고>" + accBalance);
	}
	
	
	public int deposit (int input, int balance) {
		 return 0;
	}
}
