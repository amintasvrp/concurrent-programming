package solutionA;

import java.util.Scanner;

public class FirstThread {	

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		int num_replicas = in.nextInt();
		(new ThreadGateway()).gateway(num_replicas);
		in.close();
	}

}
