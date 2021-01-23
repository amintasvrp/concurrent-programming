package solutionA;

import channel.Channel;

public class ThreadTimeout implements Runnable{
	
	private final Channel canal;
	
	public ThreadTimeout(Channel canal) {
		this.canal = canal;
	}	
	
	
	public void request() {
		try {
			Thread.sleep(8 * 1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
		}
		
		canal.putMessage(-1);		
	}

	@Override
	public void run() {
		request();
	}

}
