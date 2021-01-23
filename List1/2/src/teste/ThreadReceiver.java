package teste;

import resposta.Channel;

public class ThreadReceiver implements Runnable{

	private final Channel channel;
	
	public ThreadReceiver(Channel channel) {
		this.channel = channel;
	}
	
	@Override
	public void run() {		
		
		for (int i = 0; i < 20; i++) {
			String result = this.channel.takeMessage();
			System.out.println("RECEIVING " + result);
			
			// Establish speed between threads
			// Making prints more intuitive -> sleep > 0
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
			}
						
		}		
		
	}

}
