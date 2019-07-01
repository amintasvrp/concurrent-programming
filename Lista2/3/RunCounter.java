import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.concurrent.CountDownLatch;

public class RunCounter {

    public static void main(String[] args) {
        int numberOfThreadsCounting = Integer.parseInt(args[0]);
        CountDownLatch countDownLatch = new CountDownLatch(numberOfThreadsCounting);
        
        RegMemUsage regMem = new RegMemUsage();
        Thread readMem = new Thread(regMem);
        readMem.start();

        System.out.println("Inicio dos Testes");

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

        System.out.println("Fim da execução dos testes.");
        regMem.stop();
    }
}



class RegMemUsage implements Runnable {

    private volatile boolean exit = false;
    Runtime rt = Runtime.getRuntime();

    public RegMemUsage() {}

    public void run() {

        long sumMem = 0;
        long qtSum = 0;
        long maxMem = 0;
        
        while (!exit) {
            long heapSize = rt.totalMemory() - rt.freeMemory();
            heapSize = heapSize / 1024 / 1024;

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

        System.out.println("java,"+ maxMem + "," + (sumMem/qtSum));
    }

    public void stop(){
        exit = true;
    }
}

