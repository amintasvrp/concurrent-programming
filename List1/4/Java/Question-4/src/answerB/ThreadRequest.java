package respostaB;

import channel.Channel;

public class ThreadRequest implements Runnable {
	
	private int numberToSleep;
	private Channel canal;
	
	public ThreadRequest(int numberToSleep, Channel canal) {
		this.numberToSleep = numberToSleep;
		this.canal = canal;
	}	
	
	
	public void request() {
		
		System.out.println("Tempo: " + numberToSleep);
		
		try {
			Thread.sleep(numberToSleep * 1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
		}
		
		canal.putMessage(numberToSleep);		
	}

	@Override
	public void run() {
		request();
	}
	

}
