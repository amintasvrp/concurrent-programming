package Q1;

import java.util.ArrayList;

public class Carro extends Thread {
    public int capacidade;
    public ArrayList<Passageiro> passageiros;

    public boolean estaCarregando;
    public boolean estaDescarregando;

    public Carro(int C) {
        capacidade = C;
        passageiros = new ArrayList<Passageiro>(C);
        estaCarregando = true;
    }

    public void correr() {
        System.out.println("Carro atingiu capacidade, corrida irá durar 3seg");
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("Carro chegou ao destino, irá descarregar passageiros");
    }

    /**
     * Irá aceitar um passageiro dentro do carro, caso:
     *   - o carro tenha capacidade
     *   - está recebendo passageiros
     *   - passageiro não tentou embarcar anteriormente
     * @param t Possível passageiro do carro.
     */
    public synchronized void embarcar(Passageiro t) {
        if (estaCarregando && passageiros.size() < capacidade && !passageiros.contains(t)) {
            System.out.println("Carro sendo embarcado por Thread " + t.getName());
            passageiros.add(t);
        }
    }

    /**
     * Irá desembarcar um passageiro do carro caso:
     *   - passageiro está dentro do carro
     *   - está desembarcando passageiros
     *   - existem passageiros a desembarcar
     * @param t Possível passageiro do carro.
     */
    public synchronized void desembarcar(Passageiro t) {
        if (estaDescarregando && passageiros.size() > 0 && passageiros.contains(t)) {
            System.out.println("Carro sendo desembarcado por Thread " + t.getName());
            passageiros.remove(t);
        }
    }


    @Override
    public void run() {
        while (true) {
            synchronized (passageiros) {
                if (this.passageiros.size() < this.capacidade && !estaDescarregando) {
                    System.out.println("Esperando mais passageiros");
                } else if (this.passageiros.size() == this.capacidade) {
                    correr();
                    descarregar();
                } else if (this.passageiros.size() == 0) {
                    System.out.println("Todos passageiros entregues, carregando novos passageiros");
                    carregar();
                }
            }

            // Carro espera 1seg para próxima ação
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private synchronized void carregar() {
        estaCarregando = true;
        estaDescarregando = false;
    }

    private synchronized void descarregar() {
        estaDescarregando = true;
        estaCarregando = false;
    }
}
