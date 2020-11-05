package project1b;

import project1b.ver02.AccountManger;
import project1b.ver02.MenuChoice;


public class BankingSystemVer02 implements MenuChoice {
	
	public static void main(String[] args) {
		AccountManger start = new AccountManger();
		start.selectMenu();
	}
}
