package teste;

import resposta.Channel;

public class Test {
	
	public static void main(String[] args) throws InterruptedException {
		
		// Modificar capacidade
		int capacidade = 5;
		
		Channel canal = new Channel(capacidade);
		ThreadSender sender = new ThreadSender(canal);
		ThreadReceiver receiver = new ThreadReceiver(canal);
		
		System.out.println("----INICIANDO----");
		
		Thread sending = new Thread(sender);
		Thread receiving = new Thread(receiver);
		
		sending.start();
		receiving.start();
		
		sending.join();
		receiving.join();
			
		
		System.out.println("----FINALIZANDO----");
		
	}

}
