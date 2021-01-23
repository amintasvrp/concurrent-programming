package teste;

import resposta.Channel;

public class ThreadSender implements Runnable{
	
	private final Channel channel;
	
	public ThreadSender(Channel channel) {
		this.channel = channel;
	}
	
	@Override
	public void run() {
		
		String message = "";
		for (int i = 0; i < 20; i++) {
			message += i; 
			this.channel.putMessage(message);
			System.out.println("SENDING " + message);

			// Establish speed between threads
			// Making prints more intuitive -> sleep > 0
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
			}
			
			message = "";
		}
		
		
	}

	
}
