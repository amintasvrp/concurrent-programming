package solutionB;

import java.util.Random;

import channel.Channel;

public class ThreadGateway {
	
	public void gateway(int num_replicas) {
		
		Channel canal = new Channel(num_replicas);
		Random random = new Random();
		Integer allSleep = 0;
		
		for (int i = 0; i < num_replicas; i++) {
			int numberToSleep = random.nextInt(30) + 1;
			new Thread(new ThreadRequest(numberToSleep, canal)).start();
		}
		
		for (int i = 0; i < num_replicas; i++) {
			allSleep += canal.takeMessage();
		}
		
		System.out.println("\nTotal Time: " + allSleep);

	}


}
