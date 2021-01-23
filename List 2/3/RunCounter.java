import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.concurrent.CountDownLatch;

public class RunCounter {

    public static void main(String[] args) {
        int numberOfThreadsCounting = Integer.parseInt(args[0]);
        CountDownLatch countDownLatch = new CountDownLatch(numberOfThreadsCounting);
        
        RegMemUsage regMem = new RegMemUsage(numberOfThreadsCounting);
        Thread readMem = new Thread(regMem);
        readMem.start();

        for (int i = 0; i < numberOfThreadsCounting; i++) {
            MultiCounter mc = new MultiCounter(countDownLatch, i + 1);
            Thread t = new Thread(mc);
            t.start();
        }

        try {
            countDownLatch.await();
        } catch(InterruptedException e) {
            System.out.println("Erro ao executar teste: " + e.getMessage());
        }

        regMem.stop();
    }
}



class RegMemUsage implements Runnable {

    private volatile boolean exit = false;
    Runtime rt = Runtime.getRuntime();
    int numberOfThreadsCounting;

    public RegMemUsage(int numberOfThreadsCounting) {
        this.numberOfThreadsCounting = numberOfThreadsCounting;
    }

    public void run() {

        long sumMem = 0L;
        long qtSum = 0L;
        long maxMem = 0L;
        
        while (!exit) {
            long heapSize = (rt.totalMemory() - rt.freeMemory()) / 1024;

            sumMem += heapSize;
            qtSum +=1;
            
            if (heapSize > maxMem)
                maxMem = heapSize;

            try {
                Thread.currentThread().sleep(1000);
            } catch(InterruptedException e) {
                System.out.println("Erro ao executar teste: " + e.getMessage());
            }
            
        }

        System.out.println("java,"+ maxMem + "," + (sumMem/qtSum) + "," + this.numberOfThreadsCounting);
    }

    public void stop(){
        exit = true;
    }
}

