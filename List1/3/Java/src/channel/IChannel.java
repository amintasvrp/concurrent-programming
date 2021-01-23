package channel;

public interface IChannel {
	
	/*
	 * A channel receives messages sent by sending processes (threads).
	 * The channel must have a maximum capacity, that is, when reaching
	 * the limit, new messages cannot be sent to the channel immediately.
	 */
	
	void putMessage(Integer message);
	
	/*
	 * Recipient processes read messages sent on the channel. Messages must
	 * be read in the order they entered the channel. Once read, the message
	 * cannot be read again.
	 */
	
	Integer takeMessage();

}
