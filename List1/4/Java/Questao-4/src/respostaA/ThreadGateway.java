package respostaA;

import java.util.Random;

import channel.Channel;

public class ThreadGateway {
	
	public int gateway(int num_replicas) {
		
		Channel canal = new Channel(1);
		Random gerador = new Random();
		
		System.out.println("Tempo Limite: 8");
		
		new Thread(new ThreadTimeout(canal)).start();
		
		for (int i = 0; i < num_replicas; i++) {
			int numberToSleep = gerador.nextInt(30) + 1;
			new Thread(new ThreadRequest(numberToSleep, canal)).start();
		}
		
		Integer first = canal.takeMessage();
		
		System.out.println("\nTempo do Primeiro: " + first);
		
		return first;
	}


}
