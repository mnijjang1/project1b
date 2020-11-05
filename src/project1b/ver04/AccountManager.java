package project1b.ver04;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.util.HashSet;
import java.util.InputMismatchException;
import java.util.Iterator;
import java.util.Scanner;

public class AccountManager {
	static Scanner scanInt = new Scanner(System.in);
	static Scanner scanLine = new Scanner(System.in);
	HashSet<Account> accInfo = new HashSet<Account>();

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
		Account accInfoTemp = null;
		
		if (accSelect == 2) { 
			System.out.print("신용등급(A,B,C등급) : ");
			String creditGrade = scanLine.nextLine();
			accInfoTemp = new HighCreditAccount
					(accNum, accName, accBalance, interestRate, creditGrade);
		}
		
		else {
			accInfoTemp = new NormalAccount
					(accNum, accName, accBalance, interestRate);
		}
		
		if (!accInfo.add(accInfoTemp)) {
			
			System.out.println("중복계좌발견됨. 덮어쓸까요?(Y or N)");
			String alreadyAccNum = scanLine.nextLine();
			
			if (alreadyAccNum.equalsIgnoreCase("y")) {
				accInfo.remove(accInfoTemp);
				accInfo.add(accInfoTemp);
				
				System.out.println("계좌를 새롭게 개설하였습니다.");
			}
			
			else System.out.println("계좌개설을 취소하였습니다.");
		}
		
		else System.out.println("계좌개설이 완료되었습니다.");
	} 
	
	public void showAccInfo(){
		System.out.println("**** 계좌정보출력 ****");
		Iterator iter = accInfo.iterator();
		
		while(iter.hasNext()) {
			Account callIter = (Account) iter.next();
			callIter.show();
		}
		System.out.println("전체계좌정보 출력이 완료되었습니다.");
	}
	
	public void depositMoney() {
			System.out.println("**** 입    금 ****");
			System.out.println("계좌번호와 입금할 금액을 입력하세요");
			System.out.print("계좌번호 : ");
			String accNumInput = scanLine.nextLine();
			boolean checkDeposit = true;
		
			try {
			System.out.print("입 금 액 : ");
			int accDeposit = scanInt.nextInt();
			Iterator iter = accInfo.iterator();
			
			while(iter.hasNext()) {
				Account getIter = (Account) iter.next();
				
				if (getIter.getAccNum().equals(accNumInput)) {
					checkDeposit = false;
					
					if (accDeposit < 0) {
						System.out.println("0원 이하의 금액을 입금할 수 없습니다.");
						break;
					}
					
					else if (accDeposit % 500 != 0) {
						System.out.println("500원 단위로만 입금할 수 있습니다.");
						break;
					}
					
					else{
						int balanceTemp = getIter.getAccBalance();
						int balanceUpdate = getIter.deposit(accDeposit, balanceTemp);
						getIter.setAccBalance(balanceUpdate);
						System.out.println("입금처리가 완료되었습니다.");
						break;
					}
				}
			}
			if (checkDeposit) System.out.println("일치하는 계좌번호를 찾을 수 없습니다.");
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
		Iterator iter = accInfo.iterator();
		boolean checkWithdraw = true;
		
		while (iter.hasNext()) {
			Account getIter = (Account) iter.next();
			
			if (getIter.getAccNum().equals(accNumInput)) {
				checkWithdraw = false;
				
				if (accWithdraw < 0) {
					System.out.println("0원 이하의 금액을 출금할 수 없습니다."); 
					break;
				} 
				
				else if (accWithdraw % 1000 != 0) {
					System.out.println("1000원 단위로만 출금할 수 있습니다."); 
					break;
				}
				
				else if (getIter.getAccBalance() < accWithdraw) {
					System.out.println("잔고가 부족합니다. 금액전체를 출금할까요?");
					System.out.println("YES : 금액전체 출금처리");
					System.out.println("NO : 출금요청 취소");
					String minusBalance = scanLine.nextLine();
					
					if (minusBalance.equalsIgnoreCase("YES")) {
						getIter.setAccBalance(0);
						System.out.println("출금처리가 완료되었습니다."); 
					} 
					
					else if (minusBalance.equalsIgnoreCase("NO")){
						System.out.println("출금처리가 취소되었습니다."); 
					} 
					break;
				}
				
				else {
					getIter.setAccBalance(getIter.getAccBalance()-accWithdraw);
					System.out.println("출금처리가 완료되었습니다."); 
					break;
				}
			}	
		}
		if (checkWithdraw) System.out.println("일치하는 계좌번호를 찾을 수 없습니다.");
	}
	
	public void IOsave() {
		try {
		ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream("src/project1b/ver04/AccountInfo.obj"));
		Iterator iter = accInfo.iterator();
			
		while (iter.hasNext()) {
				Account getIter = (Account) iter.next();
				out.writeObject(getIter);
			}
			out.close();
		} 
		
		catch(Exception e) {
		}
	}
	
	public void IOload() {
		try {
			ObjectInputStream in = new ObjectInputStream(new FileInputStream("src/project1b/ver04/AccountInfo.obj"));
			
			while(true) {
				Account loadAccInfo = (Account)in.readObject();
				if (loadAccInfo == null ) {
					break;
				}
				
				else {
					accInfo.add(loadAccInfo);
				}
			}
			in.close();
		}
		
		catch(Exception e) {
		}
	}

	public void saveAuto(AutoSaverT saver) {
		System.out.println("자동저장 기능을 사용하시겠습니까?");
		System.out.println("(1.On 2.Off)");
		int option = scanInt.nextInt();
		Thread.State optionState = saver.getState();
		
		if (option == 1){
			
			if (optionState == Thread.State.TIMED_WAITING || optionState == Thread.State.RUNNABLE) {
				System.out.println("이미 자동저장이 실행중입니다.");
			}
			
			else {
				System.out.println("자동저장 기능을 활성화합니다.");
				saver.start();
			}
		}
		
		else if (option == 2) {
			System.out.println("자동저장 기능을 비활성화합니다.");
			saver.interrupt();
		}
	}
	
	public void saveTxt() {
		try {
			PrintWriter out = new PrintWriter(new FileWriter("src/project1b/ver04/AutoSaveAccount.txt"));
			Iterator<Account> iter = accInfo.iterator();
			
			while (iter.hasNext()) {
				Account getIter = (Account) iter.next();
				boolean checkClass = getIter instanceof NormalAccount;
				
				if (checkClass) {
					out.println("=============");
					out.printf("계좌번호 : %s\n고객이름 : %s\n잔     고 : %d\n기본이자 : %d\n", getIter.getAccNum(), getIter.getAccName(), getIter.getAccBalance(), ((NormalAccount)getIter).interestRate);
					out.println("=============");
				}
				
				else if (!checkClass) {
					out.println("=============");
					out.printf("계좌번호 : %s\n고객이름 : %s\n잔     고 : %d\n기본이자 : %d\n신용등급 : %s\n", 
							getIter.getAccNum(), getIter.getAccName(), getIter.getAccBalance(), ((HighCreditAccount)getIter).interestRate, ((HighCreditAccount)getIter).creditGrade);
					out.println("=============");
				}
			}
			out.close();
		}
		
		catch(IOException e) {
		}
	}
}
