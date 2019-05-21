import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.TimeUnit;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.CountDownLatch;

public class DoSMOperations implements Runnable {
    Map smap;
    CountDownLatch countDownLatch;

    int threadId;
    int totalInsertOperations;
    int totalDeleteOperations;
    int totalReadOperations;
    
    Integer insertKeys[];
    Integer insertValues[];
    Integer deleteKeys[];
    Integer getKeys[];


    public DoSMOperations(Map smapInstance,
                         CountDownLatch countDownLatch,
                         int threadId,
                         int totalInsertOperations,
                         int totalDeleteOperations,
                         int totalReadOperations,
                         Integer insertKeys[],
                         Integer insertValues[],
                         Integer deleteKeys[],
                         Integer getKeys[]) {
        this.smap = smapInstance;
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
                this.smap.put(this.insertKeys[i], this.insertValues[i]);
                inserCount++;
            }
            
            if (deleteCount < this.totalDeleteOperations) {
                this.smap.remove(this.deleteKeys[i]);
                deleteCount++;
            }

            if (readCount < this.totalReadOperations) {
                this.smap.get(getKeys[i]);
                readCount++;
            }
        }
        countDownLatch.countDown();
        System.out.println("FINISH SM Thread " + this.threadId);
    }
}