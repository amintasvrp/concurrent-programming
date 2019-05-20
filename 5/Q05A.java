import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.TimeUnit;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class Q05A {

    private static void makeRandomVector(Integer[] emptyVector) {
		Random rand = new Random();
		
		for (int i = 0 ; i < emptyVector.length ; i++) {
			emptyVector[i] = rand.nextInt(100);
		}
    }

    private static void simpleInsertTest(int testSize, int threads) {
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

        // Do ConcurrentHashMap test
        startTime = System.nanoTime();
        for (int i = 0; i < threads; i++) {
            DoCHMOperations op = new DoCHMOperations(cmap,
                                                     i,
                                                     testSize,
                                                     0,
                                                     0,
                                                     keys,
                                                     values,
                                                     null,
                                                     null);
            op.run();
        }

        endTime = System.nanoTime();
        runtime = endTime - startTime;

        System.out.println("ConcurrentHashMap runtime - " + runtime);

        //Do synchronizedhMap test
        startTime = System.nanoTime();
        for (int i = 0; i < threads; i++) {
            DoSMOperations op = new DoSMOperations(smap,
                                                   i,
                                                   testSize,
                                                   0,
                                                   0,
                                                   keys,
                                                   values,
                                                   null,
                                                   null);
            op.run();
        }

        endTime = System.nanoTime();
        runtime = endTime - startTime;

        System.out.println("synchronizedhMap runtime - " + runtime);
    }

    public static void main(String[] args) {
        System.out.println("INSERT TEST WITH 2 T");
        simpleInsertTest(50000, 2);
        System.out.println("INSERT TEST WITH 3 T");
        simpleInsertTest(50000, 3);
        System.out.println("INSERT TEST WITH 4 T");
        simpleInsertTest(50000, 4);
        System.out.println("INSERT TEST WITH 5 T");
        simpleInsertTest(50000, 5);
    }
}

