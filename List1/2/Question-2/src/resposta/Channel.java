package resposta;

import java.util.LinkedList;
import java.util.Queue;

public class Channel implements IChannel{
	
	/*
	 * Considere que o construtor do canal recebe um inteiro que indica sua capacidade máxima. 
	 * Mensagens não podem ser descartadas.
	 */
	
	/*
	 * Os prints para teste foram colocados 
	 */
	
	private Queue<String> messages;
	private int capacity;
	
	
	public Channel(int capacity) {
		this.messages = new LinkedList<>();
		this.capacity = capacity;		
	}


	@Override
	public void putMessage(String message) {
		synchronized (this.messages) {
			while(this.messages.size() >= this.capacity) {
				try {
					this.messages.wait();
				} catch (InterruptedException e) {
					// TODO: handle exception
				}				
			}
			this.messages.add(message);
			System.out.print("TAMANHO " + this.messages.size() + " : ");
			this.messages.notifyAll();
		}
		
	}


	@Override
	public String takeMessage() {
		synchronized (this.messages) {
			while(this.messages.isEmpty()) {
				try {
					this.messages.wait();
				} catch (InterruptedException e) {
					// TODO: handle exception
				}				
			}
			String result = this.messages.remove();
			
			System.out.print("TAMANHO " + this.messages.size() + " : ");
			
			this.messages.notifyAll();
			return result;
		}
		
	}
	
	

}
