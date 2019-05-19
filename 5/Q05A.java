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

