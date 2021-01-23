package solutionA;

import channel.Channel;

public class ThreadRequest implements Runnable {
	
	private final int numberToSleep;
	private final Channel channel;
	
	public ThreadRequest(int numberToSleep, Channel channel) {
		this.numberToSleep = numberToSleep;
		this.channel = channel;
	}	
	
	
	public void request() {
		System.out.println("Tempo: " + numberToSleep);
		
		try {
			Thread.sleep(numberToSleep * 1000L);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
		}
		
		channel.putMessage(numberToSleep);
	}

	@Override
	public void run() {
		request();
	}
	

}
