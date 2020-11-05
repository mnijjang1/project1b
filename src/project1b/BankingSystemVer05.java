package project1b;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

import project1b.ver05.MenuChoice;

public class BankingSystemVer05 implements MenuChoice {
	static Scanner scanInt = new Scanner(System.in);
	static Scanner scanLine = new Scanner(System.in);
	static Connection con;
	static PreparedStatement psmt; 
	static Statement stmt;
	static ResultSet rs;
	
	public static void connectDB() {
		try {
			Class.forName("oracle.jdbc.OracleDriver");
			String url = "jdbc:oracle:thin://@localhost:1521:orcl";
			String userid = "kosmo";
			String userpw = "1234";
			con = DriverManager.getConnection(url, userid, userpw);
			if (con!=null) {
				System.out.println("Oracle DB 연결 성공");
			}
			else {
				System.out.println("Oracle DB 연결 실패");
			}
		}
		
		catch(SQLException e) {
			System.out.println("SQLException 예외발생");
			e.printStackTrace();
		}
		
		catch(ClassNotFoundException e) {
			System.out.println("ClassNotFoundException 예외발생");
			e.printStackTrace();
		}
		
		catch(Exception e) {
			System.out.println("예상치 못한 예외발생");
			e.printStackTrace();
		}
	}

	public static void close() {
		try {
			if(con!=null) con.close();
			if(psmt!=null) psmt.close();
			if(rs!=null) rs.close();
			System.out.println("자원 반납 완료");
		}
		catch(Exception e) {
			System.out.println("자원 반납 에러 발생");
			e.printStackTrace();
		}
	}
	
	public static void makeAccount() {
		System.out.println("**** 신규계좌개설 ****");
		System.out.print("계좌번호 : ");
		String accNum = scanLine.nextLine();
		
		System.out.print("고객이름 : ");
		String accName = scanLine.nextLine();
		
		System.out.print("잔     고 : ");
		int accBalance = scanInt.nextInt();
		
		try {
			String query = "INSERT INTO banking_tb VALUES (?, ?, ?)";
			psmt = con.prepareStatement(query);

			psmt.setNString(1, accNum);
			psmt.setNString(2, accName);
			psmt.setInt(3, accBalance);
			
			int affected = psmt.executeUpdate();
			System.out.println("신규계좌가 개설되었습니다.");
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}	
	
	public static void showAccInfo(){
		try {
			stmt = con.createStatement();
			String query = 
					"SELECT accNum, accName, accBalance from banking_tb";
			rs = stmt.executeQuery(query);
			
			while(rs.next()) {
				String accNum = rs.getString(1);
				String accName = rs.getString(2);
				int accBalance = rs.getInt(3);
				
				System.out.println("=========================");
				System.out.println("계좌번호 : " + accNum);
				System.out.println("고객이름 : " + accName);
				System.out.println("잔     고 : " + accBalance);
				System.out.println("=========================");
			}
			
			System.out.println("전체계좌정보 출력이 완료되었습니다.");
		}
		
		catch(SQLException e) {
			e.printStackTrace();
		}
	}
	
	public static void depositMoney() {
		System.out.println("**** 입    금 ****");
		System.out.println("계좌번호와 입금할 금액을 입력하세요");
		
		System.out.print("계좌번호 : ");
		String accNumInput = scanLine.nextLine();
		
		System.out.print("입 금 액 : ");
		int accDeposit = scanInt.nextInt();
		
		try {
			String query = 
					"UPDATE banking_tb SET accBalance=accBalance+? "
					+ " WHERE accNum=?";
			psmt = con.prepareStatement(query);
			
			psmt.setNString(2, accNumInput);
			psmt.setInt(1, accDeposit);
			
			int affected = psmt.executeUpdate();
			System.out.println("입금처리가 완료되었습니다.");
		}
		
		catch(SQLException e) {
			e.printStackTrace();
		}
	}
	
	public static void withdrawMoney() {
		System.out.println("**** 출    금 ****");
		System.out.println("계좌번호와 출금할 금액을 입력하세요");
		
		System.out.print("계좌번호 : ");
		String accNumInput = scanLine.nextLine();
		
		System.out.print("출 금 액 : ");
		int accWithdraw = scanInt.nextInt();
		
		try {
			String query = 
					"UPDATE banking_tb SET accBalance=accBalance-? "
					+ " WHERE accNum=?";
			psmt = con.prepareStatement(query);
			
			psmt.setNString(2, accNumInput);
			psmt.setInt(1, accWithdraw);

			int affected = psmt.executeUpdate();
			System.out.println("출금처리가 완료되었습니다.");
		}
		
		catch(SQLException e) {
			e.printStackTrace();
		}
	}	
	

	
	public static void main(String[] args) {
		
		connectDB();
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
				close();
				System.out.println("프로그램을 종료합니다.");
				System.exit(0);
				
			default:
				System.out.println("메뉴 선택이 잘못되었습니다.");
			}
		}
	}
}
