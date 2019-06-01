package respostaA;

import java.util.Random;

public class ThreadRequest implements Runnable {
	
	private Random gerador;
	private IntegerPointer first;
	
	public ThreadRequest(Random gerador, IntegerPointer first) {
		this.gerador = gerador;
		this.first = first;
	}	
	
	
	public void request() {
		int numberToSleep;
		
		numberToSleep = this.gerador.nextInt(30) + 1;
		
		System.out.println("Tempo: " + numberToSleep);
		
		try {
			Thread.sleep(numberToSleep * 1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
		}
		
		synchronized (gerador) {
			this.first.setValue(numberToSleep);
			this.gerador.notifyAll();
			System.out.println("\nTempo do Primeiro: " + numberToSleep + "\n");
		}		
	}

	@Override
	public void run() {
		request();
	}
	

}
