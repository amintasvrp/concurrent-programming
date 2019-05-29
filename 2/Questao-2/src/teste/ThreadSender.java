package teste;

import resposta.Channel;

public class ThreadSender implements Runnable{
	
	public Channel canal;
	
	public ThreadSender(Channel canal) {
		this.canal = canal;
	}
	
	@Override
	public void run() {
		
		String message = "";
		for (int i = 0; i < 20; i++) {
			message += i; 
			this.canal.putMessage(message);
			System.out.println("SENDING " + message);
			
			// Estabelecer velocidade entre threads
			// Tornar os prints mais intuitivos -> sleep > 0
			try {
				Thread.sleep(0001);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
			}
			
			message = "";
		}
		
		
	}

	
}
