
import java.util.concurrent.CountDownLatch;
import java.util.List;

public class DoSLOperations implements Runnable {
    List slist;
    CountDownLatch countDownLatch;

    int threadId;
    int totalInsertOperations;
    int totalDeleteOperations;
    int totalReadOperations;
    
    Integer insertValues[];
    Integer deleteValues[];
    Integer getIndex[];


    public DoSLOperations(List slistInstance,
                         CountDownLatch countDownLatch,
                         int threadId,
                         int totalInsertOperations,
                         int totalDeleteOperations,
                         int totalReadOperations,
                         Integer insertValues[],
                         Integer deleteValues[],
                         Integer getIndex[]) {
        this.slist = slistInstance;
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
                this.slist.add(this.insertValues[i]);
                inserCount++;
            }
            
            if (deleteCount < this.totalDeleteOperations) {
                this.slist.remove(this.deleteValues[i]);
                deleteCount++;
            }

            if (readCount < this.totalReadOperations) {
                this.slist.get(getIndex[i]);
                readCount++;
            }
        }
        System.out.println("FINISH SL Thread " + this.threadId);
        countDownLatch.countDown();
    }
}