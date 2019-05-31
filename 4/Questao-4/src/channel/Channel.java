package channel;

import java.util.LinkedList;
import java.util.Queue;

public class Channel implements IChannel{
	
	/*
	 * Considere que o construtor do canal recebe um inteiro que indica sua capacidade máxima. 
	 * Mensagens não podem ser descartadas.
	 */
	
	private Queue<Integer> messages;
	private int capacity;
	
	
	public Channel(int capacity) {
		this.messages = new LinkedList<>();
		this.capacity = capacity;		
	}


	@Override
	public void putMessage(Integer message) {
		synchronized (this.messages) {
			while(this.messages.size() >= this.capacity) {
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
	public Integer takeMessage() {
		synchronized (this.messages) {
			while(this.messages.isEmpty()) {
				try {
					this.messages.wait();
				} catch (InterruptedException e) {
					// TODO: handle exception
				}				
			}
			Integer result = this.messages.remove();
			this.messages.notifyAll();
			return result;
		}
		
	}
	
	

}
