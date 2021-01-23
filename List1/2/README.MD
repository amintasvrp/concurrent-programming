

### Problem Description

A widely used abstraction in concurrent programming is channels. A channel receives messages sent by sender threads. Recipient processes read messages sent on the channel. Messages should be read in the order they entered the channel. Once read, the message cannot be read again. The channel must have a maximum capacity, that is, when it reaches the limit, new messages cannot be sent to the channel immediately. Assume that the channel builder receives an integer that indicates its maximum capacity. Messages cannot be discarded. Implement the interface below for the channel using any Java language concurrency and coordination mechanisms except the Concurrent Collections data structures. Consider both correctness and efficiency criteria (eg avoid spin locks when possible).


#### Interface

```
public interface Channel {
    public void putMessage(String message);
    public String takeMessage();
}
```

#### Solution

The code for the solution of the question is [here](./src).




