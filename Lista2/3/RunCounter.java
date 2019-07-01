import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.concurrent.CountDownLatch;

public class RunCounter {

    public static void main(String[] args) {
        int numberOfThreadsCounting = Integer.parseInt(args[0]);
        CountDownLatch countDownLatch = new CountDownLatch(numberOfThreadsCounting);

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
    }
}

