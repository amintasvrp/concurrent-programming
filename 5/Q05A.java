import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.TimeUnit;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;


class DoCHMOperations implements Runnable {
    ConcurrentHashMap cmap;
    int totalInsertOperations;
    int totalDeleteOperations;
    int totalReadOperations;
    
    Integer insertKeys[];
    Integer insertValues[];
    Integer deleteKeys[];
    Integer getKeys[];


    public DoCHMOperations(ConcurrentHashMap cmapInstance,
                         int totalInsertOperations,
                         int totalDeleteOperations,
                         int totalReadOperations,
                         Integer insertKeys[],
                         Integer insertValues[],
                         Integer deleteKeys[],
                         Integer getKeys[]) {
        this.cmap = cmapInstance;
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
            }
            
            if (deleteCount < this.totalDeleteOperations) {
                this.cmap.remove(this.deleteKeys[i]);
            }

            if (readCount < this.totalReadOperations) {
                this.cmap.get(getKeys[i]);
            }
        }
    }
}

public class Q05A {

    private static void makeRandomVector(Integer[] emptyVector) {
		Random rand = new Random();
		
		for (int i = 0 ; i < emptyVector.length ; i++) {
			emptyVector[i] = rand.nextInt(100);
		}
    }

    private static void simpleInsertTestWithoutConcurrency(int testSize) {
        // Count time vars
        long startTime;
		long endTime;
        long runtime;

        // Load keys and values
        Integer keys[] = new Integer[testSize];
        Integer values[] = new Integer[testSize];

        makeRandomVector(keys);
        makeRandomVector(values);

        // Load Maps
        ConcurrentHashMap cmap = new ConcurrentHashMap();
        Map smap = Collections.synchronizedMap(new HashMap());

        //Do ConcurrentHashMap test
        startTime = System.nanoTime();
        for (int i = 0; i < testSize; i++) {
            cmap.put(keys[i], values[i]);
        }

        endTime = System.nanoTime();
        runtime = endTime - startTime;

        System.out.println("ConcurrentHashMap runtime - " + runtime);

        //Do synchronizedhMap test
        startTime = System.nanoTime();
        for (int i = 0; i < testSize; i++) {
            smap.put(keys[i], values[i]);
        }

        endTime = System.nanoTime();
        runtime = endTime - startTime;

        System.out.println("synchronizedhMap runtime - " + runtime);
    }

    public static void main(String[] args) {
        System.out.println("INSERT TEST WITH SIZE 500");
        simpleInsertTestWithoutConcurrency(500);
        System.out.println("INSERT TEST WITH SIZE 5000");
        simpleInsertTestWithoutConcurrency(5000);
        System.out.println("INSERT TEST WITH SIZE 50000");
        simpleInsertTestWithoutConcurrency(50000);
        System.out.println("INSERT TEST WITH SIZE 500000");
        simpleInsertTestWithoutConcurrency(500000);
    }
}

