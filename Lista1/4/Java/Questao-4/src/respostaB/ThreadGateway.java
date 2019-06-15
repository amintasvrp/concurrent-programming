package respostaB;

import java.util.Random;

import channel.Channel;
import respostaB.ThreadTimeout;

public class ThreadGateway {
	
	public int gateway(int num_replicas) {
		
		Channel canal = new Channel(num_replicas);
		Random gerador = new Random();
		Integer allSleep = 0;
		
		System.out.println("Tempo Limite: 16");
		
		for (int i = 0; i < num_replicas; i++) {
			int numberToSleep = gerador.nextInt(30) + 1;
			new Thread(new ThreadRequest(numberToSleep, canal)).start();
		}
		
		new Thread(new ThreadTimeout(canal)).start();
		
		for (int i = 0; i < num_replicas; i++) {
			int sleepTime = canal.takeMessage();
			if (sleepTime == -1) {
				allSleep = sleepTime;
				break;
			} else {
				allSleep += sleepTime;
			}
		}
		
		System.out.println("\nTempo Total: " + allSleep);
		
		return allSleep;
	}


}
