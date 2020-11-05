package project1b.ver03;

import java.util.InputMismatchException;
import java.util.Scanner;

public class AccountManager {
	static Scanner scanInt = new Scanner(System.in);
	static Scanner scanLine = new Scanner(System.in);
	static Account [] accInfo = new Account[50];
	static int cntCustomer = 0;
	
	
	
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
			
		try {
			System.out.print("입 금 액 : ");
			int accDeposit = scanInt.nextInt();
			
			for (int i=0; i<cntCustomer; i++) {
				if (accInfo[i].getAccNum().equals(accNumInput)) {
					if (accDeposit < 0) {
						System.out.println("0원 이하의 금액을 입금할 수 없습니다.");
					}
					else if (accDeposit % 500 != 0) {
						System.out.println("500원 단위로만 입금할 수 있습니다.");
					}
					else{
						int balanceTemp = accInfo[i].getAccBalance();
						int balanceUpdate = accInfo[i].deposit(accDeposit, balanceTemp);
						accInfo[i].setAccBalance(balanceUpdate);
						System.out.println("입금처리가 완료되었습니다.");
					}
				}
			}
		}

		catch(InputMismatchException e) {
			System.out.println("입금액에 문자를 입력할 수 없습니다.");
			scanInt.next();
		}
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
				
				if (accWithdraw < 0) {
					System.out.println("0원 이하의 금액을 출금할 수 없습니다.");
				}
				else if (accWithdraw % 1000 != 0) {
					System.out.println("1000원 단위로만 출금할 수 있습니다.");
				}
				else if (accInfo[i].getAccBalance() < accWithdraw) {
					System.out.println("잔고가 부족합니다. 금액전체를 출금할까요?");
					System.out.println("YES : 금액전체 출금처리");
					System.out.println("NO : 출금요청 취소");
					String minusBalance = scanLine.nextLine();
					
					if (minusBalance.equalsIgnoreCase("YES")) {
						accInfo[i].setAccBalance(0);
						System.out.println("출금처리가 완료되었습니다.");
					}
					else if (minusBalance.equalsIgnoreCase("NO")){
						System.out.println("출금요청이 취소되었습니다.");
					}
				}
				else {
					accInfo[i].setAccBalance(accInfo[i].getAccBalance() - accWithdraw);
					System.out.println("출금처리가 완료되었습니다.");
				}
			}
		}
	}	
	
}
