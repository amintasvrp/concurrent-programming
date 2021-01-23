package solutionB;

import java.util.Random;

import channel.Channel;

public class ThreadGateway {
	
	public void gateway(int num_replicas) {
		
		Channel canal = new Channel(num_replicas);
		Random random = new Random();
		int allSleep = 0;
		
		System.out.println("Time Limit: 16");
		
		for (int i = 0; i < num_replicas; i++) {
			int numberToSleep = random.nextInt(30) + 1;
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

	}


}
