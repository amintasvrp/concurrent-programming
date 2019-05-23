package Resposta;

import java.util.LinkedList;
import java.util.Queue;

public class Channel implements IChannel{
	
	/*
	 * Considere que o construtor do canal recebe um inteiro que indica sua capacidade máxima. 
	 * Mensagens não podem ser descartadas.
	 */
	
	private Queue<String> messages;
	private int capacity;
	
	
	public void channel(int capacity) {
		this.messages = new LinkedList<>();
		this.capacity = capacity;		
	}


	@Override
	public void putMessage(String message) {
		synchronized (this.messages) {
			int size = this.messages.size();
			int capacity = this.capacity;
			while(size == capacity) {
				try {
					this.messages.wait();
				} catch (InterruptedException e) {
					// TODO: handle exception
				}				
			}
			this.messages.add(message);
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
			this.messages.notifyAll();
			return result;
		}
		
	}
	
	

}
