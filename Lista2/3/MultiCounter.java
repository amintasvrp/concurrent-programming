import java.util.concurrent.CountDownLatch;

public class MultiCounter implements Runnable {

    CountDownLatch countDownLatch;
    int threadId;

    public MultiCounter(CountDownLatch countDownLatch,
                        int threadId) {
        this.countDownLatch = countDownLatch;
        this.threadId = threadId;

    }

    public void run() {
        for (int i = 0; i < 1000000; i++) {
            if (i % 100000 == 0) {
                System.out.println("thread " +  this.threadId + " contou até " + i);
            }
        }
        
        countDownLatch.countDown();
    }
}