package solutionA;

import java.util.Random;

import channel.Channel;

public class ThreadGateway {
	
	public void gateway(int num_replicas) {
		
		Channel canal = new Channel(1);
		Random random = new Random();
		
		for (int i = 0; i < num_replicas; i++) {
			int numberToSleep = random.nextInt(30) + 1;
			new Thread(new ThreadRequest(numberToSleep, canal)).start();
		}
		
		Integer first = canal.takeMessage();
		
		System.out.println("\nFirst's Time: " + first);

	}


}
