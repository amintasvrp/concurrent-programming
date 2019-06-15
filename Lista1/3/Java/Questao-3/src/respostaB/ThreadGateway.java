package respostaB;

import java.util.Random;

import channel.Channel;

public class ThreadGateway {
	
	public int gateway(int num_replicas) {
		
		Channel canal = new Channel(num_replicas);
		Random gerador = new Random();
		Integer allSleep = 0;
		
		for (int i = 0; i < num_replicas; i++) {
			int numberToSleep = gerador.nextInt(30) + 1;
			new Thread(new ThreadRequest(numberToSleep, canal)).start();
		}
		
		for (int i = 0; i < num_replicas; i++) {
			allSleep += canal.takeMessage();
		}
		
		System.out.println("\nTempo Total: " + allSleep);
		
		return allSleep;
	}


}
