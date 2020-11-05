package project1b.ver04;

public class MenuSelectException extends Exception {
	public MenuSelectException() throws Exception {
		super("1~5번 사이의 정수를 입력해주세요.");
		
	}
}
