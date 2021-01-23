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
        for (int k = 0; k < 100; k++) {
            try {
                Thread.currentThread().sleep(100);
            } catch(InterruptedException e) {
                System.out.println("Erro ao executar teste: " + e.getMessage());
            }
        }
        
        countDownLatch.countDown();
    }
}