import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.CountDownLatch;

public class Q05B {

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

    private static void simpleCOWALTest(int readNumber, int writeNumber, int threads, 
    Integer index[], List<Integer[]> values) {
        // Count time vars
        long startTime;
		long endTime;
        long runtime;
        CountDownLatch countDownLatch;

        // Load Maps
        CopyOnWriteArrayList<Integer> clist = new CopyOnWriteArrayList<Integer>();

        // Do ConcurrentHashMap test
        countDownLatch = new CountDownLatch(threads);        
        startTime = System.nanoTime();
        for (int i = 0; i < threads; i++) {
            DoCOWALOperations op = new DoCOWALOperations(clist,
                                                     countDownLatch,
                                                     i,
                                                     writeNumber,
                                                     0,
                                                     readNumber,
                                                     values.get(i),
                                                     null,
                                                     index);
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

        System.out.println("CopyOnWriteArrayList," + runtime + "," + threads + "," + readNumber +
            "," + writeNumber + "," + (readNumber+writeNumber));
    }

    private static void simpleSLTest(int readNumber, int writeNumber, int threads, 
    Integer index[], List<Integer[]> values) {
        // Count time vars
        long startTime;
		long endTime;
        long runtime;
        CountDownLatch countDownLatch;

        // Load Maps
        List<Integer> slist = Collections.synchronizedList(new ArrayList<Integer>()); 

        // Do ConcurrentHashMap test
        countDownLatch = new CountDownLatch(threads);        
        startTime = System.nanoTime();
        for (int i = 0; i < threads; i++) {
            DoSLOperations op = new DoSLOperations(slist,
                                                     countDownLatch,
                                                     i,
                                                     writeNumber,
                                                     0,
                                                     readNumber,
                                                     values.get(i),
                                                     null,
                                                     index);
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

        System.out.println("SynchronizedList," + runtime + "," + threads + "," + readNumber +
            "," + writeNumber + "," + (readNumber+writeNumber));
    }

    public static void main(String[] args) {
        int colect = Integer.parseInt(args[0]);
        int readOpNumber = Integer.parseInt(args[1]);
        int writeOpNumber = Integer.parseInt(args[2]);
        int maxThreads = Integer.parseInt(args[3]);

        int maxOps = (readOpNumber + writeOpNumber);
        List<Integer[]> values = generateExpVectors(maxThreads, maxOps);
        Integer index[] = new Integer[maxOps];

        for (int i = 0; i < maxOps; i++) {
            index[i] = i;
        }
        
        if (colect == 0) {
            System.out.println("START COLECTION CopyOnWriteArrayList TEST");
            for (int i = 1; i < maxThreads + 1; i++) {
                simpleCOWALTest(readOpNumber, writeOpNumber, i, index, values);
            }
        } else {
            System.out.println("START COLECTION SynchronizedList TEST");
            for (int i = 1; i < maxThreads + 1; i++) {
                simpleSLTest(readOpNumber, writeOpNumber, i, index, values);
            }
        }
    }
}

