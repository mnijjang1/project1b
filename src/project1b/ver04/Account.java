package project1b.ver04;

import java.io.Serializable;
import java.text.DecimalFormat;

public abstract class Account implements Serializable {
	private String accNum, accName;
	private int accBalance;
	DecimalFormat printComma = new DecimalFormat("###,###");
	
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
	
	@Override
	public int hashCode() {
		return 0;
	}
 
	@Override
	public boolean equals(Object obj) {
			Account temp = (Account) obj;
			if (temp.accNum.equals(getAccNum())) {
				return true;
			}
			else return false;
	}

	public void show() {
		String printTemp = printComma.format(accBalance);
		System.out.println("=========================");
		System.out.println("계좌번호>" + accNum);
		System.out.println("고객이름>" + accName);
//		System.out.println("잔     고>" + accBalance +"원");
		System.out.printf("잔     고>%s원", printTemp);
		System.out.println();
	}
	
	public int deposit (int input, int balance) {
		 return 0;
	}
}
