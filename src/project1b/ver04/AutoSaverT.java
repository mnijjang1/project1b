package project1b.ver04;

public class AutoSaverT extends Thread{
	AccountManager call;
	
	public AutoSaverT(AccountManager call) {
		this.call = call;
	}

	@Override
	public void run() {
		while(true) {
			try {
				call.saveTxt();
				System.out.println("계좌정보가 자동저장되었습니다.");
				sleep(1000);
			}
			
			catch(InterruptedException e) {
				break;
			}
		}
	}
}
 