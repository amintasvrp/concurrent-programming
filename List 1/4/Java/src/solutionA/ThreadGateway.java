package solutionA;

import java.util.Random;

import channel.Channel;

public class ThreadGateway {
	
	public void gateway(int num_replicas) {
		
		Channel channel = new Channel(1);
		Random random = new Random();
		
		System.out.println("Time Limit: 8");
		
		new Thread(new ThreadTimeout(channel)).start();
		
		for (int i = 0; i < num_replicas; i++) {
			int numberToSleep = random.nextInt(30) + 1;
			new Thread(new ThreadRequest(numberToSleep, channel)).start();
		}
		
		Integer first = channel.takeMessage();
		
		System.out.println("\nFirst's Time: " + first);

	}


}
