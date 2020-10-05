import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CountDownLatch;

public class DoCHMOperations implements Runnable {
    ConcurrentHashMap cmap;
    CountDownLatch countDownLatch;

    int threadId;
    int totalInsertOperations;
    int totalDeleteOperations;
    int totalReadOperations;
    
    Integer insertKeys[];
    Integer insertValues[];
    Integer deleteKeys[];
    Integer getKeys[];


    public DoCHMOperations(ConcurrentHashMap cmapInstance,
                         CountDownLatch countDownLatch,
                         int threadId,
                         int totalInsertOperations,
                         int totalDeleteOperations,
                         int totalReadOperations,
                         Integer insertKeys[],
                         Integer insertValues[],
                         Integer deleteKeys[],
                         Integer getKeys[]) {
        this.cmap = cmapInstance;
        this.countDownLatch = countDownLatch;
        this.threadId = threadId;
        this.totalInsertOperations = totalInsertOperations;
        this.totalDeleteOperations = totalDeleteOperations;
        this.totalReadOperations = totalReadOperations;
        this.insertKeys = insertKeys;
        this.deleteKeys = deleteKeys;
        this.getKeys = getKeys;
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
                this.cmap.put(this.insertKeys[i], this.insertValues[i]);
                inserCount++;
            }
            
            if (deleteCount < this.totalDeleteOperations) {
                this.cmap.remove(this.deleteKeys[i]);
                deleteCount++;
            }

            if (readCount < this.totalReadOperations) {
                this.cmap.get(getKeys[i]);
                readCount++;
            }
        }
        countDownLatch.countDown();
    }
}