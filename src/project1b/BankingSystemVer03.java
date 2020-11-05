//package project1b;
//
//import java.util.InputMismatchException;
//import java.util.Scanner;
//
//import project1b.ver03.AccountManager;
//import project1b.ver03.MenuChoice;
//import project1b.ver03.MenuSelectException;
//
//
//public class BankingSystemVer03 implements MenuChoice {
//	
//	public static void main(String[] args) throws Exception {
//		Scanner scanInt = new Scanner(System.in);
//		AccountManager call = new AccountManager();
//		while(true) {
//			System.out.println("==== MENU ====");
//			System.out.println("1. 계좌개설");
//			System.out.println("2. 입     금");
//			System.out.println("3. 출     금");
//			System.out.println("4. 계좌정보 출력");
//			System.out.println("5. 프로그램 종료");
//			
//			try {	
//				int menu = scanInt.nextInt();
//				
//				if (menu < 1 || menu > 5) {
//					MenuSelectException ex = new MenuSelectException();
//					throw ex;
//				}
//				
//				switch (menu) {
//				case MenuChoice.MAKE:
//					call.makeAccount();
//					break;
//					
//				case MenuChoice.DEPOSIT:
//					call.depositMoney();
//					break;
//					
//				case MenuChoice.WITHDRAW:
//					call.withdrawMoney();
//					break;
//					
//				case MenuChoice.SHOW:
//					call.showAccInfo();
//					break;
//					
//				case MenuChoice.QUIT:
//					System.out.println("프로그램을 종료합니다.");
//					System.exit(0);
//				}
//			}
//			catch(MenuSelectException e){
//				System.out.println(e.getMessage());
//			}
//			
//			catch(InputMismatchException e) {
//				System.out.println("숫자만 입력이 가능합니다.");
//				scanInt.next();
//			}
//		}
//	}
//}
