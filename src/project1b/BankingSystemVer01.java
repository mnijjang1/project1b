package project1b;

import java.util.Scanner;

import project1b.ver01.Account;
import project1b.ver01.MenuChoice;


public class BankingSystemVer01 implements MenuChoice {
	static Scanner scanInt = new Scanner(System.in);
	static Scanner scanLine = new Scanner(System.in);
	static Account [] accInfo = new Account[50];
	static int cntCustomer = 0;
	
	public static void makeAccount() {
		System.out.println("**** 신규계좌개설 ****");
		System.out.print("계좌번호 : ");
		String accNum = scanLine.nextLine();
		System.out.print("고객이름 : ");
		String accName = scanLine.nextLine();
		System.out.print("잔     고 : ");
		int accBalance = scanInt.nextInt();
		
		accInfo[cntCustomer] = new Account();
		accInfo[cntCustomer].setAccNum(accNum);
		accInfo[cntCustomer].setAccName(accName);
		accInfo[cntCustomer].setAccBalance(accBalance);

		cntCustomer++;
	}
	
	public static void showAccInfo(){
		System.out.println("**** 계좌정보출력 ****");
		for (int i=0; i<cntCustomer; i++) {
			System.out.println("=========================");
			System.out.println("계좌번호 : " + accInfo[i].getAccNum());
			System.out.println("고객이름 : " + accInfo[i].getAccName());
			System.out.println("잔     고 : " + accInfo[i].getAccBalance());
			System.out.println("=========================");
		}
		System.out.println("전체계좌정보 출력이 완료되었습니다.");
	}
	
	public static void depositMoney() {
		
		System.out.println("**** 입    금 ****");
		System.out.println("계좌번호와 입금할 금액을 입력하세요");
		System.out.print("계좌번호 : ");
		String accNumInput = scanLine.nextLine();
		System.out.print("입 금 액 : ");
		int accDeposit = scanInt.nextInt();
		
		for (int i=0; i<cntCustomer; i++) {
			if (accInfo[i].getAccNum().equals(accNumInput)) {
				accInfo[i].setAccBalance(accInfo[i].getAccBalance() + accDeposit);
//				accInfo[i].deposit(accDeposit);
			}
		}
		System.out.println("입금이 완료되었습니다.");
	}
	
	public static void withdrawMoney() {
		System.out.println("**** 출    금 ****");
		System.out.println("계좌번호와 출금할 금액을 입력하세요");
		System.out.print("계좌번호 : ");
		String accNumInput = scanLine.nextLine();
		System.out.print("출 금 액 : ");
		int accWithdraw = scanInt.nextInt();
		
		for (int i=0; i<cntCustomer; i++) {
			
			if (accInfo[i].getAccNum().equals(accNumInput)) {
				accInfo[i].setAccBalance(accInfo[i].getAccBalance() - accWithdraw);
			}
		}
		System.out.println("출금이 완료되었습니다.");
	}	
	
	public static void main(String[] args) {
		while(true) {
			System.out.println("==== MENU ====");
			System.out.println("1. 계좌개설");
			System.out.println("2. 입     금");
			System.out.println("3. 출     금");
			System.out.println("4. 계좌정보 출력");
			System.out.println("5. 프로그램 종료");
			
			
			int menu = scanInt.nextInt();
			
			switch (menu) {
			case MenuChoice.MAKE:
				makeAccount();
				break;
				
			case MenuChoice.DEPOSIT:
				depositMoney();
				break;
				
			case MenuChoice.WITHDRAW:
				withdrawMoney();
				break;
				
			case MenuChoice.SHOW:
				showAccInfo();
				break;
				
			case MenuChoice.QUIT:
				System.out.println("프로그램을 종료합니다.");
				System.exit(0);
				
			default:
				System.out.println("메뉴 선택이 잘못되었습니다.");
			}
		}
	}
}
