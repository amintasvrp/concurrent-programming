package solutionB;

import channel.Channel;

public class ThreadTimeout implements Runnable{
	
	private final Channel channel;
	
	public ThreadTimeout(Channel channel) {
		this.channel = channel;
	}	
	
	
	public void request() {
		try {
			Thread.sleep(16 * 1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
		}
		
		channel.putMessage(-1);
	}

	@Override
	public void run() {
		request();
	}

}
