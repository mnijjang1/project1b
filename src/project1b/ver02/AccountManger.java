package project1b.ver02;

import java.util.Scanner;

public class AccountManger {
	static Scanner scanInt = new Scanner(System.in);
	static Scanner scanLine = new Scanner(System.in);
	static Account [] accInfo = new Account[50];
	static int cntCustomer = 0;
	
	public void selectMenu() {
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
	
	public void makeAccount() {
		System.out.println("**** 신규계좌개설 ****");
		System.out.println("===== 계좌선택 =====");
		System.out.println("1.보통계좌");
		System.out.println("2.신용신뢰계좌");
		System.out.print("선택 : ");
		int accSelect = scanInt.nextInt();
		System.out.print("계좌번호 : ");
		String accNum = scanLine.nextLine();
		System.out.print("고객이름 : ");
		String accName = scanLine.nextLine();
		System.out.print("잔     고 : ");
		int accBalance = scanInt.nextInt();
		System.out.print("기본이자%(정수형태로입력) : ");
		int interestRate = scanInt.nextInt();
		
		if (accSelect == 2) {
			System.out.print("신용등급(A,B,C등급) : ");
			String creditGrade = scanLine.nextLine();
			HighCreditAccount accInfoHigh = new HighCreditAccount(accNum, accName, accBalance, interestRate, creditGrade);
			accInfo[cntCustomer] = accInfoHigh;
		}
		else {
			NormalAccount accInfoNormal = new NormalAccount(accNum, accName, accBalance, interestRate);
			accInfo[cntCustomer] = accInfoNormal;
		}
		cntCustomer ++;
		
	}
	
	public void showAccInfo(){
		System.out.println("**** 계좌정보출력 ****");
		for (int i=0; i<cntCustomer; i++) {
			accInfo[i].show();
		}
		System.out.println("전체계좌정보 출력이 완료되었습니다.");
	}
	
	public void depositMoney() {
		
		System.out.println("**** 입    금 ****");
		System.out.println("계좌번호와 입금할 금액을 입력하세요");
		System.out.print("계좌번호 : ");
		String accNumInput = scanLine.nextLine();
		System.out.print("입 금 액 : ");
		int accDeposit = scanInt.nextInt();
		
		for (int i=0; i<cntCustomer; i++) {
			if (accInfo[i].getAccNum().equals(accNumInput)) {
				int balanceTemp = accInfo[i].getAccBalance();
				int balanceUpdate = accInfo[i].deposit(accDeposit, balanceTemp);
				accInfo[i].setAccBalance(balanceUpdate);
			}
		}
		System.out.println("입금이 완료되었습니다.");
	}
	
	public void withdrawMoney() {
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
	
}
