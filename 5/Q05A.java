import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.TimeUnit;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.CountDownLatch;

public class Q05A {

    private static void makeRandomVector(Integer[] emptyVector) {
		Random rand = new Random();
		
		for (int i = 0 ; i < emptyVector.length ; i++) {
			emptyVector[i] = rand.nextInt(100);
		}
    }

    private static void simpleCHMInsertTest(int testSize, int threads) {
        // Count time vars
        long startTime;
		long endTime;
        long runtime;
        CountDownLatch countDownLatch;

        // Load keys and values
        Integer keys[] = new Integer[testSize];
        Integer values[] = new Integer[testSize];

        makeRandomVector(keys);
        makeRandomVector(values);

        // Load Maps
        ConcurrentHashMap cmap = new ConcurrentHashMap();
        Map smap = Collections.synchronizedMap(new HashMap());

        // Do ConcurrentHashMap test
        countDownLatch = new CountDownLatch(threads);        
        startTime = System.nanoTime();
        for (int i = 0; i < threads; i++) {
            DoCHMOperations op = new DoCHMOperations(cmap,
                                                     countDownLatch,
                                                     i,
                                                     testSize,
                                                     0,
                                                     0,
                                                     keys,
                                                     values,
                                                     null,
                                                     null);
            Thread t = new Thread(op);
            t.start();
        }

        try {
            countDownLatch.await();
        } catch(InterruptedException e) {
            System.out.println("Erro ao executar teste: " + e.getMessage());
        }
        
        endTime = System.nanoTime();
        runtime = endTime - startTime;

        System.out.println("ConcurrentHashMap runtime - " + runtime);
    }

    private static void simpleSMInsertTest(int testSize, int threads) {
        // Count time vars
        long startTime;
		long endTime;
        long runtime;
        CountDownLatch countDownLatch;

        // Load keys and values
        Integer keys[] = new Integer[testSize];
        Integer values[] = new Integer[testSize];

        makeRandomVector(keys);
        makeRandomVector(values);

        // Load Maps
        ConcurrentHashMap cmap = new ConcurrentHashMap();
        Map smap = Collections.synchronizedMap(new HashMap());

        // Do SynchronizedMap test
        countDownLatch = new CountDownLatch(threads);        
        startTime = System.nanoTime();
        for (int i = 0; i < threads; i++) {
            DoSMOperations op = new DoSMOperations(cmap,
                                                     countDownLatch,
                                                     i,
                                                     testSize,
                                                     0,
                                                     0,
                                                     keys,
                                                     values,
                                                     null,
                                                     null);
            Thread t = new Thread(op);
            t.start();
        }

        try {
            countDownLatch.await();
        } catch(InterruptedException e) {
            System.out.println("Erro ao executar teste: " + e.getMessage());
        }
        
        endTime = System.nanoTime();
        runtime = endTime - startTime;

        System.out.println("SynchronizedMap runtime - " + runtime);
    }

    public static void main(String[] args) {
        System.out.println("INSERT CHM TEST WITH 12 T");
        simpleCHMInsertTest(50000000, 12);
        System.out.println("INSERT SM TEST WITH 12 T");
        simpleSMInsertTest(50000000, 12);
    }
}

