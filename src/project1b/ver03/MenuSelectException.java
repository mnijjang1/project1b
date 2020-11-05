package project1b.ver03;

public class MenuSelectException extends Exception {
	public MenuSelectException() throws Exception {
		super("1~5번 사이의 정수를 입력해주세요.");
		
		AccountManager recall = new AccountManager();
	}
}
