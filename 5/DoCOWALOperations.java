import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.CountDownLatch;

public class DoCOWALOperations implements Runnable {
    CopyOnWriteArrayList clist;
    CountDownLatch countDownLatch;

    int threadId;
    int totalInsertOperations;
    int totalDeleteOperations;
    int totalReadOperations;
    
    Integer insertValues[];
    Integer deleteValues[];
    Integer getIndex[];


    public DoCOWALOperations(CopyOnWriteArrayList clistInstance,
                         CountDownLatch countDownLatch,
                         int threadId,
                         int totalInsertOperations,
                         int totalDeleteOperations,
                         int totalReadOperations,
                         Integer insertValues[],
                         Integer deleteValues[],
                         Integer getIndex[]) {
        this.clist = clistInstance;
        this.countDownLatch = countDownLatch;
        this.threadId = threadId;
        this.totalInsertOperations = totalInsertOperations;
        this.totalDeleteOperations = totalDeleteOperations;
        this.totalReadOperations = totalReadOperations;
        this.deleteValues = deleteValues;
        this.getIndex = getIndex;
        this.insertValues = insertValues;
    }

    public void run() {
        int maxOp = Math.max(this.totalInsertOperations,
                             Math.max(this.totalDeleteOperations, this.totalReadOperations));
        
        int inserCount = 0;
        int deleteCount = 0;
        int readCount = 0;

        for (int i = 0; i < maxOp; i++) {
            if (inserCount < this.totalInsertOperations) {
                this.clist.add(this.insertValues[i]);
                inserCount++;
            }
            
            if (deleteCount < this.totalDeleteOperations) {
                this.clist.remove(this.deleteValues[i]);
                deleteCount++;
            }

            if (readCount < this.totalReadOperations) {
                this.clist.get(getIndex[i]);
                readCount++;
            }
        }
        System.out.println("FINISH COWAL Thread " + this.threadId);
        countDownLatch.countDown();
    }
}