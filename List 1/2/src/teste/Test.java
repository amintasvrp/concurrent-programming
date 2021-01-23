package teste;

import resposta.Channel;

public class Test {
	
	public static void main(String[] args) throws InterruptedException {
		
		// Setting capacity
		int capacity = 5;
		
		Channel channel = new Channel(capacity);
		ThreadSender sender = new ThreadSender(channel);
		ThreadReceiver receiver = new ThreadReceiver(channel);
		
		System.out.println("----STARTING----");
		
		Thread sending = new Thread(sender);
		Thread receiving = new Thread(receiver);
		
		sending.start();
		receiving.start();
		
		sending.join();
		receiving.join();
			
		
		System.out.println("----ENDING----");
		
	}

}
