import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.TimeUnit;

import java.util.List;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.CountDownLatch;

public class Q05A {

    private static Integer[] makeRandomVector(int size) {
        Random rand = new Random();
        Integer[] emptyVector = new Integer[size];
		
		for (int i = 0 ; i < size ; i++) {
			emptyVector[i] = rand.nextInt(100);
        }
        
        return emptyVector;
    }

    private static List<Integer[]> generateExpVectors(int nthreads, int sizebythread) {

        List<Integer[]> vectors = new ArrayList<Integer[]>();
        for (int i = 0; i < nthreads; i++) {
            Integer[] partition = makeRandomVector(sizebythread);
            vectors.add(partition);
        }

        return vectors;
    }

    private static void simpleCHMInsertTest(int testSize, int threads, List<Integer[]> keys, 
                                            List<Integer[]> values) {
        // Count time vars
        long startTime;
		long endTime;
        long runtime;
        CountDownLatch countDownLatch;

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
                                                     keys.get(i),
                                                     values.get(i),
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

    private static void simpleSMInsertTest(int testSize, int threads, List<Integer[]> keys, 
                                           List<Integer[]> values) {
        // Count time vars
        long startTime;
		long endTime;
        long runtime;
        CountDownLatch countDownLatch;

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
                                                     keys.get(i),
                                                     values.get(i),
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
        List<Integer[]> keys = generateExpVectors(12, 5000000);
        List<Integer[]> values = generateExpVectors(12, 5000000);
        System.out.println("INSERT SM TEST WITH 12 T");
        simpleSMInsertTest(5000000, 12, keys, values);
        System.out.println("INSERT CHM TEST WITH 12 T");
        simpleCHMInsertTest(5000000, 12, keys, values);
    }
}

