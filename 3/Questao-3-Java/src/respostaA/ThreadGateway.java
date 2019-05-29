package respostaA;

import java.util.Random;

public class ThreadGateway {
	
	public int gateway(int num_replicas) {
		
		IntegerPointer first = new IntegerPointer(0);
		Random gerador = new Random();
		
		for (int i = 0; i < num_replicas; i++) {
			Thread thread = new Thread(new ThreadRequest(gerador, first));
			thread.start();
		}
		
		synchronized (gerador) {
			while (first.getValue() == 0) {
				try {
					gerador.wait();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
				}
			}
		}
		
		return first.getValue();
	}


}
