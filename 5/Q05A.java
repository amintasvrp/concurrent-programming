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

    private static void simpleCHMTest(int readNumber, int writeNumber, int threads, 
    List<Integer[]> keys, List<Integer[]> values) {
        // Count time vars
        long startTime;
		long endTime;
        long runtime;
        CountDownLatch countDownLatch;

        // Load Maps
        ConcurrentHashMap cmap = new ConcurrentHashMap();

        // Do ConcurrentHashMap test
        countDownLatch = new CountDownLatch(threads);        
        startTime = System.nanoTime();
        for (int i = 0; i < threads; i++) {
            DoCHMOperations op = new DoCHMOperations(cmap,
                                                     countDownLatch,
                                                     i,
                                                     writeNumber,
                                                     0,
                                                     readNumber,
                                                     keys.get(i),
                                                     values.get(i),
                                                     null,
                                                     keys.get(i));
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

    private static void simpleSMTest(int readNumber, int writeNumber, int threads, 
    List<Integer[]> keys, List<Integer[]> values) {
        // Count time vars
        long startTime;
		long endTime;
        long runtime;
        CountDownLatch countDownLatch;

        // Load Maps
        Map smap = Collections.synchronizedMap(new HashMap());

        // Do SynchronizedMap test
        countDownLatch = new CountDownLatch(threads);        
        startTime = System.nanoTime();
        for (int i = 0; i < threads; i++) {
            DoSMOperations op = new DoSMOperations(smap,
                                                     countDownLatch,
                                                     i,
                                                     writeNumber,
                                                     0,
                                                     readNumber,
                                                     keys.get(i),
                                                     values.get(i),
                                                     null,
                                                     keys.get(i));
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
        int colect = Integer.parseInt(args[0]);
        int readOpNumber = Integer.parseInt(args[1]);
        int writeOpNumber = Integer.parseInt(args[2]);
        int maxThreads = Integer.parseInt(args[3]);

        List<Integer[]> keys = generateExpVectors(maxThreads, (readOpNumber + writeOpNumber));
        List<Integer[]> values = generateExpVectors(maxThreads, (readOpNumber + writeOpNumber));
        
        if (colect == 0) {
            System.out.println("START COLECTION HASHMAP TEST");
            for (int i = 1; i < maxThreads + 1; i++) {
                simpleCHMTest(readOpNumber, writeOpNumber, i, keys, values);
            }
        } else {
            System.out.println("START COLECTION SynchronizedMap TEST");
            for (int i = 1; i < maxThreads + 1; i++) {
                simpleSMTest(readOpNumber, writeOpNumber, i, keys, values);
            }
        }
    }
}

