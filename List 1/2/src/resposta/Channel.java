package resposta;

import java.util.LinkedList;
import java.util.Queue;

public class Channel implements IChannel{
	
	/*
	 * Consider that the channel builder receives an integer that indicates its maximum capacity.
	 * Messages cannot be discarded.
	 */
	
	/*
	 * The prints were put to test
	 */
	
	private final Queue<String> messages;
	private final int capacity;
	
	
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
			System.out.print("SIZE " + this.messages.size() + " : ");
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
			
			System.out.print("SIZE " + this.messages.size() + " : ");
			
			this.messages.notifyAll();
			return result;
		}
		
	}
	
	

}
