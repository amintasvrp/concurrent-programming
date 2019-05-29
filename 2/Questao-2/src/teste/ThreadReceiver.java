package teste;

import resposta.Channel;

public class ThreadReceiver implements Runnable{

	private Channel canal;
	
	public ThreadReceiver(Channel canal) {
		this.canal = canal;
	}
	
	@Override
	public void run() {		
		
		for (int i = 0; i < 20; i++) {
			String result = this.canal.takeMessage();
			System.out.println("RECEIVING " + result);
			
			// Estabelecer velocidade entre threads
			// Tornar os prints mais intuitivos -> sleep > 0
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
			}
						
		}		
		
	}

}
